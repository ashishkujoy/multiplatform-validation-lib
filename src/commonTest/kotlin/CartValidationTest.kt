import kotlin.test.Test
import kotlin.test.assertTrue

class CartValidationTest {
    @Test
    fun shouldDoCartValidation() {
        assertTrue {
            Validator(Cart(
                items = listOf(
                    CartItem("c1", 12.23),
                    CartItem("abcd", 12.77)
                ),
                totalPayableAmount = 25.00
            )).isValid()
        }
    }
}