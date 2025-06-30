package schema.string

import schema.Schema
import java.util.regex.Pattern

class StringSchema : Schema<String> {
    private val rules = mutableListOf<StringRule>()

    fun min(min: Int): StringSchema {
        this.rules.add(MinRule(min))
        return this
    }

    fun max(max: Int): StringSchema {
        this.rules.add(MaxRule(max))
        return this
    }

    fun regex(pattern: Pattern): StringSchema {
        this.rules.add(RegexRule(pattern))
        return this
    }

    fun regex(regex: Regex): StringSchema {
        this.rules.add(RegexRule(regex.toPattern()))
        return this
    }

    fun startWith(startWith: String): StringSchema {
        this.rules.add(StartWithRule(startWith))
        return this
    }

    fun endWith(endWith: String): StringSchema {
        this.rules.add(EndWithRule(endWith))
        return this
    }

    fun include(include: String): StringSchema {
        this.rules.add(IncludeRule(include))
        return this
    }

    override fun validate(value: Any?) {
        if (value !is String) {
            throw IllegalArgumentException("Expect string")
        }

        for (rule in rules) {
            rule.checkRule(value)
        }
    }
}
