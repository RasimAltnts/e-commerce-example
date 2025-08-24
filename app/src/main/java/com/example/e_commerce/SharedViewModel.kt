package com.example.e_commerce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.data.local.entity.ProductEntity
import com.example.e_commerce.domain.usecase.AddProductUseCase
import com.example.e_commerce.domain.usecase.GetAllProductUseCase
import com.example.e_commerce.utils.extension.toProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase,
    private val addProductUseCase: AddProductUseCase
): ViewModel() {

    val productList: StateFlow<List<ProductEntity>> =
        getAllProductUseCase()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun addToCard(uiModel: ProductComponentUIModel) {
        viewModelScope.launch {
            try {
                addProductUseCase.invoke(uiModel.toProductEntity())
            }catch (e: Exception) {
            }
        }
    }
}