package schema

import schema.format.FormatSchema
import schema.string.StringSchema

class SchemaBuilder {
    private val fields = mutableMapOf<String, Schema<*>>()

    infix fun String.`is`(schema: Schema<*>) {
        fields[this] = schema
    }

    fun string() = StringSchema()

    fun format() = FormatSchema()

    fun build() = ObjectSchema(fields)
}
