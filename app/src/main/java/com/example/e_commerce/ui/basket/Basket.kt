package com.example.e_commerce.ui.basket

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.databinding.FragmentBasketBinding
import com.example.e_commerce.ui.adapter.OrdersAdapter
import com.example.e_commerce.ui.model.OrdersUiModel
import com.example.e_commerce.utils.enums.OrdersProcessEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Basket : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private val viewModel: BasketViewModel by viewModels()

    private lateinit var adapter: OrdersAdapter

    private val onClickMinus: (OrdersUiModel) -> Unit = { uiModel->
        viewModel.updateOrders(uiModel, OrdersProcessEnum.MINUS)
    }

    private val onClickPlus: (OrdersUiModel) -> Unit = { uiModel->
        viewModel.updateOrders(uiModel, OrdersProcessEnum.PLUS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initFlows()
        viewModel.getAllOrders()
    }

    private fun initAdapter() {
        adapter = OrdersAdapter(orders = emptyList(), onClickMinus = onClickMinus, onClickPlus = onClickPlus)
        binding.ordersRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.ordersRecycleView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orders.collect { orders ->
                    adapter.submitList(orders)
                    viewModel.calculateTotalPrice(orders)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.totalPrice.collect { totalPrice ->
                    binding.totalPriceTextView.text = "$totalPrice ₺"
                }
            }
        }
    }
}