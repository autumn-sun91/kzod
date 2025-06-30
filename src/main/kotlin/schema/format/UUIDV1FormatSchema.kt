package schema.format

import schema.Schema
import java.util.regex.Pattern

class UUIDV1FormatSchema : Schema<String> {
    private val pattern: Pattern = "^[0-9a-f]{8}-[0-9a-f]{4}-1[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}${'$'}".toPattern()

    override fun validate(value: Any?) {
        if (value !is String) {
            throw IllegalArgumentException("Expect string")
        }

        if (!Pattern.matches(this.pattern.pattern()!!, value)) {
            throw java.lang.IllegalArgumentException("format type not match(uuid_v1), ")
        }
    }
}
