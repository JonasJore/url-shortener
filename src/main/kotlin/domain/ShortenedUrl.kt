package domain

import java.time.LocalDateTime

data class ShortenedUrl(
        val id: String,
        var url: String,
        var shortened: String,
        var createdDate: String
)

fun <T>List<Map<String, T>>.toShortenedUrl(): List<ShortenedUrl> =
    this.map { shortenedUrl ->
      ShortenedUrl(
          id = shortenedUrl["id"].toString(),
          url = shortenedUrl["url"].toString(),
          shortened = shortenedUrl["shortened"].toString(),
          createdDate = LocalDateTime.now().toString()
      )
    }