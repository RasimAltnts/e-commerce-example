package com.example.e_commerce.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.e_commerce.databinding.ProductComponentBinding

class ProductComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): CardView(context, attrs, defStyleAttr) {

    private val binding: ProductComponentBinding
    init {
        val inflater = LayoutInflater.from(context)
        binding = ProductComponentBinding.inflate(inflater, this, true)
    }

    fun setData(
        model: ProductComponentUIModel,
        onFavoriteClickListener: (() -> Unit),
        onAddToCardClickListener: (() -> Unit)) {

        binding.productNameTextView.text = model.name
        binding.priceTextView.text = model.price
        binding.favoriteIcon.isSelected = model.isFavorite

        binding.favoriteIcon.setOnClickListener {
            onFavoriteClickListener.invoke()
        }

        binding.addCardButton.setOnClickListener {
            onAddToCardClickListener.invoke()
        }
    }
}