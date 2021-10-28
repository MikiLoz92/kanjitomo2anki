package com.mikiloz.kanjitomo2anki.anki.http

import com.mikiloz.kanjitomo2anki.anki.AnkiNote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AddNoteAction(val params: Params) {

    @Serializable data class Params(
        val note: AnkiNote
    )

    val action: String = "addNote"
    val version: Int = 6
}
