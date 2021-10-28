package com.mikiloz.kanjitomo2anki.anki.http

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddNoteResult(@SerialName("result") val deckId: Long?, val error: String?)
