package schema

import schema.format.DateFormatSchema
import schema.format.EmailFormatSchema
import schema.format.ISODateFormatSchema
import schema.format.PhoneNumberFormatSchema
import schema.format.UUIDV1FormatSchema
import schema.format.UUIDV4FormatSchema
import schema.string.StringSchema

class SchemaBuilder {
    private val fields = mutableMapOf<String, Schema<*>>()

    infix fun String.`is`(schema: Schema<*>) {
        fields[this] = schema
    }

    fun string() = StringSchema()

    fun email() = EmailFormatSchema()

    fun email(regex: Regex) = EmailFormatSchema(regex.toPattern())

    fun email(regex: String) = EmailFormatSchema(regex.toPattern())

    fun uuidV1() = UUIDV1FormatSchema()

    fun uuidV4() = UUIDV4FormatSchema()

    fun isoDate() = ISODateFormatSchema()

    fun date() = DateFormatSchema()

    fun date(regex: String) = DateFormatSchema(regex.toPattern())

    fun date(regex: Regex) = DateFormatSchema(regex.toPattern())

    fun phoneNumber() = PhoneNumberFormatSchema()

    fun phoneNumber(regex: String) = PhoneNumberFormatSchema(regex.toPattern())

    fun phoneNumber(regex: Regex) = PhoneNumberFormatSchema(regex.toPattern())

    fun build() = ObjectSchema(fields)
}
