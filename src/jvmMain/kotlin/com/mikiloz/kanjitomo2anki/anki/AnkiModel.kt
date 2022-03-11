package com.mikiloz.kanjitomo2anki.anki

import kotlinx.serialization.Serializable

@Serializable
data class AnkiModel(
    val modelName: String,
    val inOrderFields: Array<String> = arrayOf("Kanji", "Kana", "Meaning"),
    val css: String = """
        .card {
         font-size: 24px;
         text-align: center;
         --text-color: black;
         word-wrap: break-word;
        }
        .card.night_mode {
         font-size: 24px;
         text-align: center;
         --text-color: white;
         word-wrap: break-word;
        }
        div, a {
         color: var(--text-color);
        }
        .card a { text-decoration-color: #A1B2BA; }

        .big { font-size: 48px; }
        .left { text-align: left; }
        .small { font-size: 18px;}
    """.trimIndent(),
    val isCloze: Boolean = false,
    val cardTemplates: Array<AnkiCardTemplate> = arrayOf(AnkiCardTemplate())
)
