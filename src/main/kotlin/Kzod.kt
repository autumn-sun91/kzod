import schema.ObjectSchema
import schema.SchemaBuilder

fun kzodObj(schemaBuilder: SchemaBuilder.() -> Unit): ObjectSchema {
    val builder = SchemaBuilder()

    builder.schemaBuilder()

    return builder.build()
}
