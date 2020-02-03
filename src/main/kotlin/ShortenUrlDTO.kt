import com.fasterxml.jackson.annotation.JsonProperty

data class ShortenUrlDTO(
    @JsonProperty("url")
    val url: String,
    @JsonProperty("shortenedUrl")
    val shortenedUrl: String
)