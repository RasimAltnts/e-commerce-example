package com.example.e_commerce

import com.example.e_commerce.domain.usecase.GetAllOrdersUseCase
import com.example.e_commerce.domain.usecase.UpdateOrdersUseCase
import com.example.e_commerce.ui.basket.BasketViewModel
import com.example.e_commerce.ui.model.OrdersUiModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class BasketUnitTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: BasketViewModel

    @Mock
    private lateinit var getAllOrdersUseCase: GetAllOrdersUseCase
    @Mock
    private lateinit var updateOrderUseCase: UpdateOrdersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = BasketViewModel(getAllOrdersUseCase,updateOrderUseCase)
    }

    @Test
    fun testCalculateTotalPrice() = runTest {
        var orderList = listOf(
            OrdersUiModel(
                id = "1",
                name = "Product 1",
                price = "10.0",
                count = 1
            ),
            OrdersUiModel(
                id = "2",
                name = "Product 2",
                price = "20.0",
                count = 2
            ),
            OrdersUiModel(
                id = "3",
                name = "Product 3",
                price = "30.0",
                count = 3
            )
        )

        viewModel.calculateTotalPrice(orderList)
        advanceUntilIdle()

        assertEquals(140.0, viewModel.totalPrice.value, 0.0001)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }
}