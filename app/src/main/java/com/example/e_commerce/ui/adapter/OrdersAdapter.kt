package com.example.e_commerce.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.databinding.OrdersItemViewBinding
import com.example.e_commerce.ui.model.OrdersUiModel

class OrdersAdapter(
    private var orders: List<OrdersUiModel>,
    private val onClickMinus: (OrdersUiModel) -> Unit,
    private val onClickPlus: (OrdersUiModel) -> Unit
) :RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
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
        return holder.bind(orders[position],onClickMinus, onClickPlus)
    }

    override fun getItemCount(): Int = orders.size

    class ViewHolder(private val binding: OrdersItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            model: OrdersUiModel,
            onClickMinus: (OrdersUiModel) -> Unit,
            onClickPlus: (OrdersUiModel) -> Unit
        ) {
            binding.productNameTextView.text = model.name
            binding.productPriceTextView.text = model.price

            binding.orderComponent.setData(
                model.count,
                { onClickMinus(model) },
                { onClickPlus(model) }
            )
        }
    }
}