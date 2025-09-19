package com.putragandad.bookpediacmp.book.data.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

// Custom serializer for BookWorkDto
object BookWorkDtoSerializer : KSerializer<BookWorkDto> {

    // Describe the structure of BookWorkDto for kotlinx.serialization
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        BookWorkDto::class.simpleName!!   // name of the class
    ) {
        element<String?>("description")   // BookWorkDto only has one field: description
    }

    // Serialize BookWorkDto -> JSON
    override fun serialize(
        encoder: Encoder,
        value: BookWorkDto
    ) = encoder.encodeStructure(descriptor) {
        // Only encode description if it's not null
        value.description?.let {
            encodeStringElement(descriptor, 0, it)
        }
    }

    // Deserialize JSON -> BookWorkDto
    override fun deserialize(decoder: Decoder): BookWorkDto {
        // Ensure we are using a JSON decoder (not Proto, CBOR, etc.)
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This decoder only works with JSON")

        // Use decodeStructure to handle composite decoding
        return jsonDecoder.decodeStructure(descriptor) {
            var description: String? = null   // placeholder for the decoded description

            // Iterate through all elements of the JSON object
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    // Case for "description" field
                    0 -> {
                        // Decode raw JSON element (could be object or primitive)
                        val element = jsonDecoder.decodeJsonElement()

                        // Handle both possible cases:
                        description = when {
                            // Case 1: description is a JSON object -> map to DescriptionDto
                            element is JsonObject -> {
                                jsonDecoder.json
                                    .decodeFromJsonElement(DescriptionDto.serializer(), element)
                                    .value // only take the "value" which is string from the JSON object
                            }
                            // Case 2: description is just a raw string
                            element is JsonPrimitive && element.isString -> element.content

                            // Anything else (null, unexpected type) -> set null
                            else -> null
                        }
                    }

                    // No more elements -> break out of the loop
                    CompositeDecoder.DECODE_DONE -> break

                    // Unexpected index -> throw error
                    else -> throw SerializationException("Unexpected index $index")
                }
            }

            // Return the deserialized DTO
            BookWorkDto(description)
        }
    }
}
