package domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ShortenedUrls(@JsonProperty val shortenedUrls: List<ShortenedUrl>)