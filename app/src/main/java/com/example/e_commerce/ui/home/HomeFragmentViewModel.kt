package com.example.e_commerce.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.domain.usecase.AddProductUseCase
import com.example.e_commerce.domain.usecase.GetProductUseCase
import com.example.e_commerce.domain.usecase.UpdateFavoriteUseCase
import com.example.e_commerce.utils.extension.toFavoriteEntity
import com.example.e_commerce.utils.extension.toProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _saveProductStatus = MutableSharedFlow<Boolean>()
    val saveProductStatus: SharedFlow<Boolean> = _saveProductStatus

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _products.emit(getProductUseCase.invoke())
            }catch (e: Exception) {

            }
        }
    }

    fun updateFavoriteProduct(uiModel: ProductComponentUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                saveFavoriteProductUseCase.invoke(uiModel.toFavoriteEntity(),uiModel.isFavorite)
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
}