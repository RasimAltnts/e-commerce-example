package com.example.e_commerce.ui.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.domain.usecase.GetAllOrdersUseCase
import com.example.e_commerce.domain.usecase.UpdateOrdersUseCase
import com.example.e_commerce.ui.model.OrdersUiModel
import com.example.e_commerce.utils.enums.OrdersProcessEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getAllOrdersUseCase: GetAllOrdersUseCase,
    private val updateOrdersUseCase: UpdateOrdersUseCase
) : ViewModel() {

    private val _orders = MutableStateFlow<List<OrdersUiModel>>(emptyList())
    val orders = _orders.asStateFlow()

    private val _totalPrice = MutableStateFlow<Double>(0.0)
    val totalPrice = _totalPrice.asStateFlow()

    fun getAllOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _orders.emit(getAllOrdersUseCase())
            }catch (e: Exception) {

            }
        }
    }

    fun updateOrders(model: OrdersUiModel, type: OrdersProcessEnum) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateOrdersUseCase(model,type)
                getAllOrders()
            }catch (e: Exception) {

            }
        }
    }

    fun calculateTotalPrice(list: List<OrdersUiModel>) {
        viewModelScope.launch {
            try {
                var totalPrice: Double = 0.0
                var productTotalPrice: Double = 0.0
                list.forEach { item ->
                    productTotalPrice = item.price.toDouble() * item.count
                    totalPrice = totalPrice + productTotalPrice
                }
                _totalPrice.emit(totalPrice)
            }catch (e: Exception) {

            }

        }
    }
}