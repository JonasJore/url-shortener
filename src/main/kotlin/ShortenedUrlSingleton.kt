import domain.ShortenedUrl
import domain.ShortenedUrls

object ShortenedUrlSingleton {
  // wat even is this init??
  val urls = ShortenedUrls(mutableListOf())

  fun addToShortenedUrls(shortenedUrl: ShortenedUrl): ShortenedUrls {
    urls.shortenedUrls.add(shortenedUrl)
    return urls
  }
}