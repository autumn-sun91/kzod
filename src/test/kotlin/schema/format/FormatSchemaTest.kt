package schema.format

import kzodObj
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FormatSchemaTest {
    @Test
    @DisplayName("이메일 형식 이 아닌 경우에는 예외가 발생해야된다.")
    fun emailValidate() {
        val obj =
            kzodObj {
                "email" `is` email()
            }

        assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("email" to "double..dot@example.com"))
        }
    }

    @Test
    @DisplayName("uuid v1 형식 이 아닌 경우에는 예외가 발생해야된다.")
    fun uuidV1Validate() {
        val obj =
            kzodObj {
                "uuid" `is` uuidV1()
            }

        assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("uuid" to "6ba7b810-9dad-41d1-80b4-00c04fd430c8"))
        }
    }

    @Test
    @DisplayName("uuid v4 형식 이 아닌 경우에는 예외가 발생해야된다.")
    fun uuidV4Validate() {
        val obj =
            kzodObj {
                "uuid" `is` uuidV4()
            }

        assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("uuid" to "6ba7b810-9dad-11d1-80b4-00c04fd430c8"))
        }
    }

    @Test
    @DisplayName("iso date 형식 이 아닌 경우에는 예외가 발생해야된다.")
    fun isoDateValidate() {
        val obj =
            kzodObj {
                "date" `is` isoDate()
            }

        assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("date" to "2023-07-30 14:30:00Z"))
        }
    }

    @Test
    @DisplayName("date(yyyy-MM-dd) 형식 이 아닌 경우에는 예외가 발생해야된다.")
    fun dateValidate() {
        val obj =
            kzodObj {
                "date" `is` date()
            }

        assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("date" to "2023-13-01"))
        }
    }

    @Test
    @DisplayName("phoneNumber(국제 표준 형식, 한국 포함) 형식 이 아닌 경우에는 예외가 발생해야된다.")
    fun phoneNumberValidate() {
        val obj =
            kzodObj {
                "phoneNumber" `is` phoneNumber()
            }

        assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("phoneNumber" to "+999999999999999"))
        }
    }
}
