package com.example.e_commerce.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.example.e_commerce.databinding.SearchBarBinding

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: SearchBarBinding

    init {
        orientation = HORIZONTAL
        val inflater = LayoutInflater.from(context)
        binding = SearchBarBinding.inflate(inflater, this, true)
    }

    fun setOnSearchTextChanged(listener: (String) -> Unit) {
        binding.searchEditText.addTextChangedListener {
            listener.invoke(it.toString())
        }
    }

    fun getText(): String = binding.searchEditText.text.toString()

    fun clearText() {
        binding.searchEditText.text.clear()
    }
}
