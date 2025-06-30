package schema.format

import schema.Schema
import java.util.regex.Pattern

class ISODateFormatSchema : Schema<String> {
    private val pattern: Pattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(?:\\.\\d+)?(?:Z|[+-]\\d{2}:\\d{2})$".toPattern()

    override fun validate(value: Any?) {
        if (value !is String) {
            throw IllegalArgumentException("Expect string")
        }

        if (!Pattern.matches(this.pattern.pattern()!!, value)) {
            throw java.lang.IllegalArgumentException("format type not match(iso_date), ")
        }
    }
}
