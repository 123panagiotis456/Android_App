package com.example.supermarketmanager.data

import com.example.supermarketmanager.data.models.ShoppingCartItem
import java.math.BigDecimal
import java.math.RoundingMode

object CartPricing {
    private val percentage = Regex("""(\d{1,3})\s*%\s*(?:off)?""", RegexOption.IGNORE_CASE)

    fun lineCents(unitPrice: Double, quantity: Int, offer: String?): Long {
        val promotion = offer?.trim().orEmpty()
        val twoForOne = promotion.replace(" ", "") == "1+1"
        val paidQuantity = if (twoForOne) quantity - quantity / 2 else quantity
        val discount = percentage.matchEntire(promotion)?.groupValues?.get(1)
            ?.toIntOrNull()?.takeIf { it in 0..100 } ?: 0
        return BigDecimal.valueOf(unitPrice)
            .multiply(BigDecimal.valueOf(paidQuantity.toLong()))
            .multiply(BigDecimal.valueOf((100 - discount).toLong()))
            .setScale(0, RoundingMode.HALF_UP)
            .longValueExact()
    }

    fun total(items: List<ShoppingCartItem>): Double =
        items.sumOf { lineCents(it.pricePerUnit, it.quantity, it.offer) } / 100.0
}
