package com.example.e_commerce.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.domain.usecase.AddProductUseCase
import com.example.e_commerce.domain.usecase.GetProductUseCase
import com.example.e_commerce.domain.usecase.UpdateFavoriteUseCase
import com.example.e_commerce.utils.extension.toFavoriteLocalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val saveFavoriteProductUseCase: UpdateFavoriteUseCase,
    private val addProductUseCase: AddProductUseCase
): ViewModel() {

    private val _products = MutableStateFlow<List<ProductComponentUIModel>?>(emptyList())
    val products: StateFlow<List<ProductComponentUIModel>?> = _products

    private val _searchQuery = MutableStateFlow("")
    /**
     * Burda yapilan islem iki adet state flow u combine yapip uzerinde degisiklik
     * yapildiginda tetiklenip islem yapilmasi. Search Query degistiginde otomaik olarak
     * burayi tetikleyip filtreleme islemi yapar
     */
    val filteredProductList: StateFlow<List<ProductComponentUIModel>?> =
        combine(_products, _searchQuery) { list, query ->
            if (query.isEmpty()) list
            else list?.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    private val _saveProductStatus = MutableSharedFlow<Boolean>()
    val saveProductStatus: SharedFlow<Boolean> = _saveProductStatus

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val productList = getProductUseCase.invoke()
                val uiModel = productList?.map {
                    ProductComponentUIModel(
                        id = it.id,
                        name = it.name,
                        price = it.price,
                        model = it.model,
                        brand = it.brand,
                        desc = it.desc,
                        isFavorite = it.isFavorite
                    )
                }

                _products.emit(uiModel)
            }catch (e: Exception) {

            }
        }
    }

    fun updateFavoriteProduct(uiModel: ProductComponentUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                saveFavoriteProductUseCase.invoke(uiModel.toFavoriteLocalModel(),uiModel.isFavorite)
                _products.update { list ->
                    list?.map {
                        if (it.id == uiModel.id)
                            it.copy(isFavorite = !uiModel.isFavorite) else it
                    }
                }
            }catch (e: Exception) {
                _saveProductStatus.emit(false)
            }
        }
    }

    fun setSearchQuery(query: String) {
        println("setSearchQuery::$query")
        _searchQuery.value = query
    }
}