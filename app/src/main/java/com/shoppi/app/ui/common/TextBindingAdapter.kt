package com.shoppi.app.ui.common

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shoppi.app.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

@BindingAdapter("priceAmount")
fun applyPriceFormat(view: TextView, price: Int) {
    val decimalFormat = DecimalFormat("#,###")
    view.text = view.context.getString(R.string.unit_discount_currency, decimalFormat.format(price))
}

// priceAmount와 discountRate가 동시에 불릴때 호출
@BindingAdapter("priceAmount", "discountRate")
fun applyPriceDiscountRate(view: TextView, price: Int, discountRate: Int) {
    val discountPrice = (((100 - discountRate) / 100.0) * price).roundToInt()
    applyPriceFormat(view, discountPrice)
}

@BindingAdapter("priceAmountCenterStroke")
fun applyCenterStroke(view: TextView, price: Int) {
    view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

    val decimalFormat = DecimalFormat("#,###")
    view.text = view.context.getString(R.string.unit_discount_currency, decimalFormat.format(price))
}