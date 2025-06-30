package schema.string

import kzodObj
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class StringSchemaTest {
    @Test
    @DisplayName("String 이 아닌 경우에는 예외가 발생해야된다.")
    fun stringValidate() {
        val obj =
            kzodObj {
                "name" `is` string()
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("name" to 1))
        }
    }

    @Test
    @DisplayName("min 호출 시 최소 길이보다 작은 경우 예외가 발생되어야된다.")
    fun stringMinValidate() {
        val obj =
            kzodObj {
                "name" `is` string().min(5)
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("name" to "test"))
        }
    }

    @Test
    @DisplayName("max 호출 시 최대 길이보다 큰 경우 예외가 발생되어야된다.")
    fun stringMaxValidate() {
        val obj =
            kzodObj {
                "name" `is` string().max(5)
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("name" to "testtest"))
        }
    }

    @Test
    @DisplayName("min,max 동시 호출 시 길이가 범위 밖이라면 예외가 발생되어야된다.")
    fun stringMinMaxValidate() {
        val obj =
            kzodObj {
                "name" `is` string().min(1).max(3)
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("name" to "testtest"))
        }
    }

    @Test
    @DisplayName("정규식 표현이 일치하지않으면 예외가 발생되어야된다.")
    fun stringRegExValidate() {
        val obj =
            kzodObj {
                "name" `is` string().regex(Pattern.compile("^[0-9]*$"))
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("name" to "abc"))
        }
    }

    @Test
    @DisplayName("prefix 가 일치 하지않으면 예외가 발생되어야된다.")
    fun stringStartWithValidate() {
        val obj =
            kzodObj {
                "name" `is` string().startWith("abcd")
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("name" to "abc"))
        }
    }

    @Test
    @DisplayName("suffix 가 일치 하지않으면 예외가 발생되어야된다.")
    fun stringEndWithValidate() {
        val obj =
            kzodObj {
                "name" `is` string().endWith("abcd")
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("name" to "abc"))
        }
    }

    @Test
    @DisplayName("abc 가 포함되어있지않으면 예외가 발생되어야된다.")
    fun stringIncludeValidate() {
        val obj =
            kzodObj {
                "name" `is` string().include("abc")
            }

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            obj.validate(mapOf("name" to "efg"))
        }
    }
}
