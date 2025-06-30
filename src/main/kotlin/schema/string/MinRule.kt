package schema.string

class MinRule(
    private val min: Int,
) : StringRule {
    override fun checkRule(value: String) {
        if (value.length < min) {
            throw IllegalArgumentException("min size is ${this.min}")
        }
    }
}
