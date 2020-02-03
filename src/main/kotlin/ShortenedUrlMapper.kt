import domain.ShortenedUrl

data class ShortenedUrlMapper(val identifier: String, val originalUrl: String, val shortenedUrl: String)

fun ShortenedUrlMapper.toShortenedUrl(): ShortenedUrl = ShortenedUrl(
    id = this.identifier,
    url = this.originalUrl,
    shortened = this.shortenedUrl
)