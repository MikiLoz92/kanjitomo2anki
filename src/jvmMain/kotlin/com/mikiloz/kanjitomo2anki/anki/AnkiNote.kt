package com.mikiloz.kanjitomo2anki.anki

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnkiNote(
    @SerialName("deckName") val deckName: String,
    val modelName: String = "Basic",
    val fields: Map<String, String>,
)
