import app.jdbi
import domain.ShortenedUrl
import domain.ShortenedUrlDTO
import domain.ShortenedUrls
import domain.toShortenedUrl
import org.apache.log4j.Logger

private val logger: Logger = Logger.getLogger(UrlShortenerRepository::class.java)

class UrlShortenerRepository {
  private val jdbi = jdbi()

  private fun getUrls(): ShortenedUrls =
      ShortenedUrls(
          shortenedUrlList = jdbi.open()
              .createQuery("SELECT * FROM shortened_url")
              .mapToMap()
              .list()
              .toShortenedUrl()
      )

  fun getAllUrls(): ShortenedUrls =
    getUrls()

  fun getUrlByIdOrShortened(identifier: String) =
      getUrls().shortenedUrlList.first { it.id == identifier || it.shortened == identifier }

  // TODO: this is for unshortening, make that more obvious...
  fun getOriginalUrlById(identifier: String) =
      getUrls().shortenedUrlList.first { it.id == identifier }.url

  fun addUrl(shortenedUrl: ShortenedUrl) {
    jdbi.withHandle<Unit, Exception> { it.execute(
        "INSERT INTO shortened_url(id, url, shortened) VALUES (?, ?, ?);",
        shortenedUrl.id, shortenedUrl.url, shortenedUrl.shortened
    ) }
  }

  fun deleteUrl(identifier: String) {
    jdbi.useHandle<Exception> { it.execute("DELETE FROM shortened_url WHERE id = ?", identifier) }
    logger.info("Deleted url with id: $identifier")
  }

  fun updateById(identifier: String, shortenedUrlDTO: ShortenedUrlDTO) {
    jdbi.useHandle<Exception> { it.execute(
        "UPDATE shortened_url SET url = ?, shortened = ? where id = ?",
        shortenedUrlDTO.url, shortenedUrlDTO.shortenedUrl, identifier
    ) }
  }
}