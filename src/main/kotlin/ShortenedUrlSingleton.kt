import domain.ShortenedUrl
import domain.ShortenedUrls

object ShortenedUrlSingleton {
  // TODO: denne må fikses på et punkt
  val urls = ShortenedUrls(mutableListOf())

  fun addToShortenedUrls(shortenedUrl: ShortenedUrl): ShortenedUrls {
    urls.shortenedUrls.add(shortenedUrl)
    return urls
  }
}