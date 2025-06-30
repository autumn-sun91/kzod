package schema.format

import schema.Schema
import java.util.regex.Pattern

class EmailFormatSchema(
    private var pattern: Pattern =
        Regex(
            """^(?!\.)(?!.*\.\.)([a-z0-9_'+\-\.]*)[a-z0-9_+\-]@([a-z0-9][a-z0-9\-]*\.)+[a-z]{2,}$""",
            RegexOption.IGNORE_CASE,
        ).toPattern(),
) : Schema<String> {
    override fun validate(value: Any?) {
        if (value !is String) {
            throw IllegalArgumentException("Expect string")
        }

        if (!Pattern.matches(this.pattern.pattern(), value)) {
            throw java.lang.IllegalArgumentException("format type not match(email), ")
        }
    }
}
