package schema

interface Schema<T> {
    fun validate(value: Any?)
}
