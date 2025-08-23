package com.example.e_commerce.ui.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.domain.usecase.GetAllOrdersUseCase
import com.example.e_commerce.ui.model.OrdersUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getAllOrdersUseCase: GetAllOrdersUseCase
) : ViewModel() {

    private val _orders = MutableStateFlow<List<OrdersUiModel>>(emptyList())
    val orders = _orders.asStateFlow()

    fun getAllOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _orders.emit(getAllOrdersUseCase())
            }catch (e: Exception) {

            }
        }
    }
}