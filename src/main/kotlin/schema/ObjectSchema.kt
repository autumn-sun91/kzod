package schema

import kotlin.IllegalArgumentException

class ObjectSchema(
    private val fields: Map<String, Schema<*>>,
) : Schema<Map<String, Any?>> {
    override fun validate(value: Any?) {
        if (value !is Map<*, *>) {
            throw IllegalArgumentException("Expect object")
        }

        for ((key, schema) in fields) {
            val fieldValue = value[key] ?: throw IllegalArgumentException("field($key) is empty")

            schema.validate(fieldValue)
        }
    }
}
