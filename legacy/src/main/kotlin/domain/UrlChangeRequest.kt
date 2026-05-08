package domain

data class UrlChangeRequest(
    val id: String,
    val indexToChange: Int,
    val listOfUrls: MutableList<ShortenedUrl>,
    val newUrl: ShortenedUrl
)