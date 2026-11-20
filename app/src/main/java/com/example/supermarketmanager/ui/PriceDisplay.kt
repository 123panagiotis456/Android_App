package com.example.supermarketmanager.ui

import android.content.Context
import com.example.supermarketmanager.R
import java.text.NumberFormat
import java.util.Currency

internal fun Context.formatEuro(amount: Double): String =
    NumberFormat.getCurrencyInstance(resources.configuration.locales[0]).apply {
        currency = Currency.getInstance("EUR")
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }.format(amount)

internal fun Context.priceUnitLabel(unit: String): String = when (unit) {
    "€/kg" -> getString(R.string.price_per_kg)
    "€/item" -> getString(R.string.price_per_item)
    else -> unit
}
