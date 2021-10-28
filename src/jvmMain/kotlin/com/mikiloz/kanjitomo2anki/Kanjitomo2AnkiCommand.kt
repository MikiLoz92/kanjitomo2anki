package com.mikiloz.kanjitomo2anki

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.mikiloz.kanjitomo2anki.anki.AnkiClient
import com.mikiloz.kanjitomo2anki.anki.AnkiNote
import com.mikiloz.kanjitomo2anki.anki.exception.AnkiException
import com.mikiloz.kanjitomo2anki.parse.VocabularyFileParser
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Kanjitomo2AnkiCommand : CliktCommand() {

    override val commandHelp = """
        This utility parses a text file with vocabulary, straight from Kanjitomo (in its default format), and tries to
        interface with the AnkiConnect plugin of Anki (you'll need both installed and configured on your machine), in
        order to create a new deck with all the words that you supplied.
        
        Now, it's just a matter of whether you have the will to study those words (=
    """.trimIndent()

    val userDeckName by option("-d", "--deckName", help = "The name of the deck that will be created on Anki.")
    val ankiConnectHost by option("-h", "--host", help = "The hostname where AnkiConnect plugin is listening on. 127.0.0.1 by default.")
            .default("127.0.0.1")
    val ankiConnectPort by option("-p", "--port", help = "The port number where AnkiConnect plugin is listening on. 8765 by default.", )
            .int().default(8765)

    val file by argument("file", help = "The file that contains the vocabulary to be added to Anki.")

    override fun run() = runBlocking {
        val words = VocabularyFileParser().invoke(file)
        val ankiClient = AnkiClient(ankiConnectHost, ankiConnectPort)
        //words.forEach { println(it) }
        val deckName = userDeckName ?: "kanjitomo2anki ${file.fileName()}"
        try {
            ankiClient.createDeck(deckName)
            println("Created Anki deck named \"$deckName\".")
        } catch (e: AnkiException) {
            System.err.println("Error creating Anki deck named for $deckName.")
            e.printStackTrace(System.err)
            return@runBlocking
        }
        words.forEach { word ->
            try {
                ankiClient.createNote(AnkiNote(deckName, fields = mapOf(
                    "Front" to word.kanji,
                    "Back" to "${word.kana}\n\n${word.meaning}"
                )))
                println("Created new Anki card for ${word.kanji}.")
            } catch (e: AnkiException) {
                System.err.println("Error creating card for ${word.kanji}.")
                e.printStackTrace(System.err)
            }

        }
    }

    /**
     * Strips away the preceding path and the extension of a file.
     */
    private fun String.fileName() = """^.*/|\.[^.]+$""".toRegex().replace(this, "")

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Kanjitomo2AnkiCommand::class.java)
    }
}
