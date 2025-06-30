package schema.string

class StartWithRule(
    private val startWith: String,
) : StringRule {
    override fun checkRule(value: String) {
        if (startWith.length > value.length) {
            throw IllegalArgumentException("Expected the value to start with $startWith, but got $value")
        }
        for (i in startWith.indices) {
            if (value[i] != startWith[i]) {
                throw IllegalArgumentException("Expected the value to start with $startWith, but got $value")
            }
        }
    }
}
