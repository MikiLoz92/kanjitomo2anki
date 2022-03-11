package com.mikiloz.kanjitomo2anki.anki.http

import com.mikiloz.kanjitomo2anki.anki.AnkiModel
import com.mikiloz.kanjitomo2anki.anki.AnkiNote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreateModelAction(val params: AnkiModel) {
    val action: String = "createModel"
    val version: Int = 6
}
