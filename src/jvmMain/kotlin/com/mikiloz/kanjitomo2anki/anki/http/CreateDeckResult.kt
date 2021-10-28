package com.mikiloz.kanjitomo2anki.anki.http

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateDeckResult(@SerialName("result") val deckId: Long?, val error: String?)
