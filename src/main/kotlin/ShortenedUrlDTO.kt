import com.fasterxml.jackson.annotation.JsonProperty

data class ShortenedUrlDTO(
    @JsonProperty("url")
    val url: String,
    @JsonProperty("shortenedUrl")
    val shortenedUrl: String
)