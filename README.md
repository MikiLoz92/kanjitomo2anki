This is a simple Kotlin application that extracts vocabulary from any file outputted by [Kanjitomo](https://www.kanjitomo.net/), in its standard format, and creates a new [Anki](https://apps.ankiweb.net/) deck with all the words contained in it, so that you can study it later.

# Prerequisites

You need installed in your machine:
* JDK 1.8
* The Anki application
* `AnkiConnect` plugin in Anki

The AnkiConnect server must be running when executing the `kanjitomo2anki` tool.

# Usage
Download the source code and execute the following on the root folder:
```shell
chmod +x kanjitomo2anki
./kanjitomo2anki ./path/to/vocabularyfile.txt
```

You can also use the `--help` CLI option to check some of the possible configurations of the tool.
