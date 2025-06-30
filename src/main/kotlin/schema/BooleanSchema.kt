package schema

class BooleanSchema : Schema<Boolean> {
    override fun validate(value: Any?) {
        if (value !is Boolean) {
            throw IllegalArgumentException("Expect boolean")
        }
    }
}
