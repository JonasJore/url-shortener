import com.fasterxml.jackson.annotation.JsonProperty

data class UrlShortenDTO(
    @JsonProperty("url")
    val url: String,
    @JsonProperty("shortenedUrl")
    val shortenedUrl: String
)