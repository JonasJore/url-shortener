import domain.ShortenedUrl
import java.time.LocalDateTime

data class ShortenedUrlMapper(
    val identifier: String,
    val originalUrl: String,
    val shortenedUrl: String,
    val createdDate: String
)

fun ShortenedUrlMapper.toShortenedUrl(): ShortenedUrl = ShortenedUrl(
    id = this.identifier,
    url = this.originalUrl,
    shortened = this.shortenedUrl,
    createdDate = this.createdDate
)