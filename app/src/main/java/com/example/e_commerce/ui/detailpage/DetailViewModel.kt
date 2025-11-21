package com.example.e_commerce.ui.detailpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.domain.usecase.UpdateFavoriteUseCase
import com.example.e_commerce.ui.utils.mapper.toFavoriteLocalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saveFavoriteProductUseCase: UpdateFavoriteUseCase,
) : ViewModel() {

    private val _productModel = MutableStateFlow<ProductComponentUIModel?>(null)
    val productModel: StateFlow<ProductComponentUIModel?> = _productModel

    fun updateFavoriteState(product: ProductComponentUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                saveFavoriteProductUseCase.invoke(product.toFavoriteLocalModel(),product.isFavorite)
                _productModel.update {
                    product.copy(isFavorite = !product.isFavorite)
                }
            }catch (e: Exception) {

            }
        }
    }

     fun setProduct(model: ProductComponentUIModel) {
         viewModelScope.launch {
             _productModel.emit(model)
         }
    }
}