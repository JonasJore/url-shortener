package util

import java.io.File

class ShortenedUrlResource {
  private val jsonfileName: String = "shortened-urls.json"

  fun readUrlsFromFile(): String =
      File(jsonfileName).readText(Charsets.UTF_8)

}