package schema.string

class EndWithRule(
    private val endWith: String,
) : StringRule {
    override fun checkRule(value: String) {
        if (endWith.length > value.length) {
            throw IllegalArgumentException("Expected the value to end with $endWith, but got $value")
        }

        val offset = value.length - endWith.length

        for (i in endWith.indices) {
            if (value[offset + i] != endWith[i]) {
                throw IllegalArgumentException("Expected the value to end with $endWith, but got $value")
            }
        }
    }
}
