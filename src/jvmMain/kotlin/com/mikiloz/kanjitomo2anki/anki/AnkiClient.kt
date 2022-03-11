package com.mikiloz.kanjitomo2anki.anki

import com.mikiloz.kanjitomo2anki.anki.exception.AnkiException
import com.mikiloz.kanjitomo2anki.anki.http.AddNoteAction
import com.mikiloz.kanjitomo2anki.anki.http.AddNoteResult
import com.mikiloz.kanjitomo2anki.anki.http.CreateDeckAction
import com.mikiloz.kanjitomo2anki.anki.http.CreateDeckResult
import com.mikiloz.kanjitomo2anki.anki.http.CreateModelAction
import com.mikiloz.kanjitomo2anki.anki.http.CreateModelResult
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class AnkiClient(private val host: String, private val port: Int) {

    private val httpClient = HttpClient {
        /*install(Logging) {
            level = LogLevel.ALL
            developmentMode = true
        }*/
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json { encodeDefaults = true; ignoreUnknownKeys = true })
            acceptContentTypes = acceptContentTypes + ContentType("text", "json")
        }
    }

    suspend fun createDeck(deckName: String) = try {
        httpClient.post<CreateDeckResult>("http://$host:$port") {
            body = CreateDeckAction(CreateDeckAction.Params(deckName))
            contentType(ContentType.Application.Json)
        }
    } catch (e: ClientRequestException) {
        throw AnkiException("Error performing API call to AnkiConnect server", e)
    }

    suspend fun createNote(note: AnkiNote) = try {
        httpClient.post<AddNoteResult>("http://$host:$port") {
            body = AddNoteAction(AddNoteAction.Params(note))
            contentType(ContentType.Application.Json)
        }
    } catch (e: ClientRequestException) {
        throw AnkiException("Error performing API call to AnkiConnect server", e)
    }

    suspend fun createModel(model: AnkiModel) = try {
        httpClient.post<CreateModelResult>("http://$host:$port") {
            body = CreateModelAction(model)
            contentType(ContentType.Application.Json)
        }
    } catch (e: ClientRequestException) {
        throw AnkiException("Error performing API call to AnkiConnect server", e)
    }

}
