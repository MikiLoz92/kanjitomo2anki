package com.mikiloz.kanjitomo2anki.anki.http

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreateDeckAction(val params: Params) {

    @Serializable data class Params(@SerialName("deck") val deckName: String)

    val action: String = "createDeck"
    val version: Int = 6
}
