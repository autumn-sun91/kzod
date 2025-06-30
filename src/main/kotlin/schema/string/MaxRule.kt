package schema.string

class MaxRule(
    private val max: Int,
) : StringRule {
    override fun checkRule(value: String) {
        if (value.length > max) {
            throw IllegalArgumentException("max size is ${this.max}")
        }
    }
}
