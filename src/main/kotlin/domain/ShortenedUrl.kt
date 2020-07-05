package domain

import com.fasterxml.jackson.annotation.JsonCreator

data class ShortenedUrl(
    val id: String,
    var url: String,
    var shortened: String
) {
  companion object {
    @JsonCreator
    @JvmStatic
    private fun creator(): ShortenedUrl {
      return ShortenedUrl(id = "", url = "", shortened = "")
    }
  }
}

fun <T>List<Map<String, T>>.toShortenedUrl(): List<ShortenedUrl> =
    this.map { shortenedUrl ->
      ShortenedUrl(
          id = shortenedUrl["id"].toString(),
          url = shortenedUrl["url"].toString(),
          shortened = shortenedUrl["shortened"].toString()
      )
    }