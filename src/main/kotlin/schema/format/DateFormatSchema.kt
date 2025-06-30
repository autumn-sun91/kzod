package schema.format

import schema.Schema
import java.util.regex.Pattern

class DateFormatSchema(
    private var pattern: Pattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$".toPattern(),
) : Schema<String> {
    override fun validate(value: Any?) {
        if (value !is String) {
            throw IllegalArgumentException("Expect string")
        }

        if (!Pattern.matches(this.pattern.pattern()!!, value)) {
            throw java.lang.IllegalArgumentException("format type not match(date - yyyy-MM-dd), ")
        }
    }
}
