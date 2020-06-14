package util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import domain.ShortenedUrl
import domain.ShortenedUrls
import domain.UrlChangeRequest
import java.io.File
import java.nio.file.Paths

private const val JSON_FILE: String = "shortened-urls.json"

class FileOperations {

  fun readUrlsFromFile(): String =
      File(JSON_FILE).readText(Charsets.UTF_8)

  // TODO: deserialize into ShortenedUrls instead of MutableList<ShortenedUrl>
  fun readFileContent(): MutableList<ShortenedUrl> {
    val json: String = FileOperations().readUrlsFromFile()
    return ObjectMapper().readValue(json, object : TypeReference<MutableList<ShortenedUrl>>() {})
  }

  fun writeToFile(shortenedUrls: ShortenedUrls): Unit =
      ObjectMapper()
          .writeValue(
              Paths.get(JSON_FILE)
                  .toFile(),
              shortenedUrls.shortenedUrls
          )

  fun deleteById(id: String) {
    val theList = this.readFileContent()
    val indexToDelete = theList.indexOf(theList.find { it.id == id })
    theList.removeAt(indexToDelete)
    writeToFile(ShortenedUrls(theList))
  }

  fun changeById(urlChangeRequest: UrlChangeRequest, idToChange: String) {
    urlChangeRequest.listOfUrls
        .filter { it.id == idToChange }
        .map {
          it.url = urlChangeRequest.newUrl.url
          it.shortened = urlChangeRequest.newUrl.shortened
        }

    writeToFile(ShortenedUrls(urlChangeRequest.listOfUrls))
  }
}