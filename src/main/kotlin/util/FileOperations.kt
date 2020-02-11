package util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import domain.ShortenedUrl
import java.io.File

class FileOperations {
  private val jsonfileName: String = "shortened-urls.json"

  fun readUrlsFromFile(): String =
      File(jsonfileName).readText(Charsets.UTF_8)

  // the goal is to deserialise the json into the ShortenedUrl::class not a List.
  fun deserialiseJsonFile(): List<ShortenedUrl> {
    val json: String = FileOperations().readUrlsFromFile()
    return ObjectMapper().readValue(json, object : TypeReference<List<ShortenedUrl>>() {})
  }

}