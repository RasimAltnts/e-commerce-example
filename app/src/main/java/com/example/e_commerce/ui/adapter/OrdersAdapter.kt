package com.example.e_commerce.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.databinding.OrdersItemViewBinding
import com.example.e_commerce.ui.model.OrdersUiModel

class OrdersAdapter(
    private var orders: List<OrdersUiModel>,
) :RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    fun submitList(newOrders: List<OrdersUiModel>) {
        this.orders = newOrders
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: OrdersItemViewBinding = OrdersItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        return holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    class ViewHolder(private val binding: OrdersItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            model: OrdersUiModel
        ) {
            binding.productNameTextView.text = model.name
            binding.productPriceTextView.text = model.price
        }
    }
}