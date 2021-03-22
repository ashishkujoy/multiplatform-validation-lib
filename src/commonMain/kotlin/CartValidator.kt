@file:JsExport

import kotlin.js.JsExport
import kotlin.js.JsName

@JsName("Cart")
class Cart(val items: List<CartItem>, val totalPayableAmount: Double)

@JsName("CartItem")
class CartItem(val categoryCode: String, val totalPremium: Double)

@JsName("Validator")
class Validator(private val cart: Cart) {
    @JsName("isValid")
    fun isValid(): Boolean {
        return if (cart.totalPayableAmount < 5000) {
            cart.items.any { it.categoryCode == "abcd" }
        } else {
            false
        }
    }
}