package schema.string

import java.lang.IllegalArgumentException

class IncludeRule(
    private val include: String,
) : StringRule {
    override fun checkRule(value: String) {
        if (include.isEmpty()) {
            throw IllegalArgumentException("include string is empty")
        } else if (include.length > value.length) {
            throw IllegalArgumentException("include length size less than value length")
        }

        for (i in 0..value.length - include.length) {
            var found = true
            for (j in include.indices) {
                if (value[i + j] != include[j]) {
                    found = false
                    break
                }
            }

            if (found) return
        }

        throw IllegalArgumentException("$include found in $value")
    }
}
