package com.example.e_commerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.components.ProductComponent
import com.example.e_commerce.components.ProductComponentUIModel

class ProductAdapter(
    products: List<ProductComponentUIModel>,
    private val onAddToCardListener: (ProductComponentUIModel) -> Unit,
    private val onFavoriteClickListener: (ProductComponentUIModel) -> Unit,
    private val onItemClickListener: (ProductComponentUIModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var items: List<ProductComponentUIModel> = products

        class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val productComponent: ProductComponent = itemView as ProductComponent
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.homepage_product_item_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = items[position]

        holder.productComponent.setData(
            product,
            onFavoriteClickListener = { onFavoriteClickListener(product) },
            onAddToCardClickListener = { onAddToCardListener(product) }
        )

        holder.itemView.setOnClickListener {
            onItemClickListener(product)
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<ProductComponentUIModel>) {
        val diffCallback = ProductDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }
}

