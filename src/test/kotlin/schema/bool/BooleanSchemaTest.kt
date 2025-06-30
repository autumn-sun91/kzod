package schema.bool

import kzodObj
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BooleanSchemaTest {
    @Test
    @DisplayName("boolean 이 아닌 경우에는 예외가 발생해야된다.")
    fun booleanValidate() {
        val obj =
            kzodObj {
                "flag" `is` boolean()
            }

        assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("flag" to "true"))
        }
    }
}
