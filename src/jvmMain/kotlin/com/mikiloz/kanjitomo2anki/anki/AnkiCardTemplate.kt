package com.mikiloz.kanjitomo2anki.anki

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnkiCardTemplate(
    @SerialName("Name") val name : String = "Default",
    @SerialName("Front") val front : String = "<div class=big>{{Kanji}}</div>",
    @SerialName("Back") val back : String = "<div class=big>{{Kanji}}</div><br><div class=left>{{Kana}}</div><br><div class=left>{{Meaning}}</div>",
)
