package com.example.e_commerce.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.e_commerce.databinding.OrderComponentBinding

class OrderComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet ?= null,
    defStyleAttr: Int = 0
): LinearLayout(context, attributeSet, defStyleAttr) {

    private val binding: OrderComponentBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = OrderComponentBinding.inflate(inflater, this, true)
    }
}