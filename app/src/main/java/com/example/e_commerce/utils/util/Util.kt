package com.example.e_commerce.utils.util

import android.content.Context

fun dpToPx(dp: Int, context: Context): Int {
    return (dp * context.resources.displayMetrics.density).toInt()
}
