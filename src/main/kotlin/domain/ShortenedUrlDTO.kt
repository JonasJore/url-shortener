package domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ShortenedUrlDTO(
    @JsonProperty("url")
    val url: String,
    @JsonProperty("shortenedUrl")
    val shortenedUrl: String,
    @JsonProperty("created_date")
    val createdDate: LocalDateTime
)