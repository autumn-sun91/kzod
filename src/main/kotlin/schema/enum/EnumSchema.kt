package schema.enum

import schema.Schema
import kotlin.reflect.KClass

class EnumSchema(
    private val enum: KClass<out Enum<*>>,
) : Schema<KClass<Enum<*>>> {
    override fun validate(value: Any?) {
        val constants = enum.java.enumConstants
        val constantNames = enum.java.enumConstants.map { it.name }

        when (value) {
            is Enum<*> -> {
                val contains = constants.contains(value)
                if (contains.not()) {
                    throw IllegalArgumentException("value is not exist in enum(${enum.simpleName}::$constantNames)")
                }
            }
            is String -> {
                val contains = constantNames.contains(value)
                if (contains.not()) {
                    throw IllegalArgumentException("value is not exist in enum(${enum.simpleName}::$constantNames)")
                }
            }
            else -> throw IllegalArgumentException("Expect Enum or String")
        }
    }
}
