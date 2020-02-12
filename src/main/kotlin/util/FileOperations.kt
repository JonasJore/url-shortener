package util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import domain.ShortenedUrl
import domain.ShortenedUrls
import service.UrlShortenerService
import java.io.File
import java.nio.file.Paths

class FileOperations {
  private val jsonfileName: String = "shortened-urls.json"

  fun readUrlsFromFile(): String =
      File(jsonfileName).readText(Charsets.UTF_8)

  // the goal is to deserialise the json into the ShortenedUrls::class not a List.
  fun deserialiseJsonFile(): MutableList<ShortenedUrl> {
    val json: String = FileOperations().readUrlsFromFile()
    return ObjectMapper().readValue(json, object : TypeReference<MutableList<ShortenedUrl>>() {})
  }

  fun writeUrlsToStorageFile(shortenedUrls: ShortenedUrls): Unit =
      ObjectMapper()
          .writeValue(
              Paths.get(jsonfileName)
                  .toFile(),
              shortenedUrls.shortenedUrls
          )

  fun deleteById(id: String) {
    val theList = this.deserialiseJsonFile()
    val indexToDelete = theList.indexOf(theList.find { it.id == id })

    theList.removeAt(indexToDelete)

    writeUrlsToStorageFile(ShortenedUrls(theList))

  }

}