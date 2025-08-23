package com.example.e_commerce.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.domain.model.ProductModel
import com.example.e_commerce.domain.usecase.GetProductUseCase
import com.example.e_commerce.utils.extension.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
): ViewModel() {

    private val _products = MutableStateFlow<List<ProductComponentUIModel>?>(emptyList())
    val products: StateFlow<List<ProductComponentUIModel>?> = _products

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _products.emit(getProductUseCase.invoke()?.toUIModel())
            }catch (e: Exception) {

            }
        }
    }
}