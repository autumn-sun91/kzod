package schema

import kzodObj
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EnumSchemaTest {
    enum class EDummy1 {
        A,
        B,
        C,
    }

    enum class EDummy2 {
        A,
        B,
        C,
    }

    @Test
    @DisplayName("enum 값이 없으면 예외를 발생 시켜야된다.")
    fun enumStringValidate() {
        val kzodObj =
            kzodObj {
                "type" `is` enum(EDummy1::class)
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            kzodObj.validate(mapOf("type" to "D"))
        }
    }

    @Test
    @DisplayName("enum 타입이 없으면 예외를 발생 시켜야된다.")
    fun enumValidate() {
        val kzodObj =
            kzodObj {
                "type" `is` enum(EDummy1::class)
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            kzodObj.validate(mapOf("type" to EDummy2.A))
        }
    }
}
