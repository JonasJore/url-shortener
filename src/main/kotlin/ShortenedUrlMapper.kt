import domain.ShortenedUrl

data class ShortenedUrlMapper(val originalUrl: String, val shortenedUrl: String)

fun ShortenedUrlMapper.toShortenedUrl(): ShortenedUrl = ShortenedUrl(
    url = this.originalUrl,
    shortened = this.shortenedUrl
)