package schema.string

import java.util.regex.Pattern

class RegexRule(
    private val pattern: Pattern,
) : StringRule {
    override fun checkRule(value: String) {
        if (!Pattern.matches(pattern.pattern(), value)) {
            throw IllegalArgumentException("regex not match ${pattern.pattern()}")
        }
    }
}
