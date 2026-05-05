import domain.ShortenedUrl

data class ShortenedUrlMapper(
  val uuid: String,
  val identifier: String,
  val originalUrl: String,
  val shortenedUrl: String,
  val createdDate: String
)

fun ShortenedUrlMapper.toShortenedUrl(): ShortenedUrl = ShortenedUrl(
  uuid = this.uuid,
  id = this.identifier,
  url = this.originalUrl,
  shortened = this.shortenedUrl,
  createdDate = this.createdDate
)
