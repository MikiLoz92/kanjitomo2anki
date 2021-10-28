package com.mikiloz.kanjitomo2anki.parse

import okio.ExperimentalFileSystem
import okio.FileSystem
import okio.Path.Companion.toPath

class VocabularyFileParser {

    @OptIn(ExperimentalFileSystem::class)
    operator fun invoke(path: String): Sequence<Word> {

        val fileContents = FileSystem.SYSTEM.read(path.toPath()) {
            readUtf8()
        }

        val regex = """
            (^[\uFF01-\uFF5E\u3041-\u3096\u30A0-\u30FF\u3400-\u4DB5\u4E00-\u9FCB\uF900-\uFA6A]+)\t([\uFF01-\uFF5E\u3041-\u3096\u30A0-\u30FF\u3400-\u4DB5\u4E00-\u9FCB\uF900-\uFA6A]+)\t([\s\S]+?)(?=([\uFF01-\uFF5E\u3041-\u3096\u30A0-\u30FF\u3400-\u4DB5\u4E00-\u9FCB\uF900-\uFA6A]+\t))
        """.trimIndent().toRegex(RegexOption.MULTILINE)

        val words = regex.findAll(fileContents).map { m ->
            Word(m.groupValues[1], m.groupValues[2], m.groupValues[3].removeSuffix("\n"))
        }

        return words
    }
}
