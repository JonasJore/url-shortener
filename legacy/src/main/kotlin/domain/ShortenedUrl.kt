package domain

import java.time.LocalDateTime

data class ShortenedUrl(
  val uuid: String,
  val id: String,
  var url: String,
  var shortened: String,
  var createdDate: String
)

// TODO: this needs to be cleaned up after db and UrlShortenerRepository overhaul
fun <T> List<Map<String, T>>.toShortenedUrl(): List<ShortenedUrl> =
  this.map { shortenedUrl ->
    ShortenedUrl(
      uuid = shortenedUrl["uuid"].toString(),
      id = shortenedUrl["shortened_url_id"].toString(),
      url = shortenedUrl["original_url"].toString(),
      shortened = shortenedUrl["shortened_url_name"].toString(),
      createdDate = LocalDateTime.now().toString()
    )
  }
