package com.example.e_commerce.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.e_commerce.components.ProductComponentUIModel

class ProductDiffCallback(
    private val oldList: List<ProductComponentUIModel>,
    private val newList: List<ProductComponentUIModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Eğer item’ları unique id ile tanımlıyorsan
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Item içeriği aynı mı kontrol et
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
