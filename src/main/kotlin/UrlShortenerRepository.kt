import app.jdbi
import domain.ShortenedUrl
import domain.ShortenedUrlDTO
import domain.ShortenedUrls
import domain.toShortenedUrl
import org.apache.log4j.Logger
import java.time.LocalDateTime

private val logger: Logger = Logger.getLogger(UrlShortenerRepository::class.java)

class UrlShortenerRepository {
  private val jdbi = jdbi()

  private fun getUrls(): ShortenedUrls =
      ShortenedUrls(
          shortenedUrls = jdbi.open()
              .createQuery("SELECT * FROM shortened_url")
              .mapToMap()
              .list()
              .toShortenedUrl()
      )

  fun getAllUrls(): ShortenedUrls =
    getUrls()

  fun getUrlByIdOrShortened(identifier: String) =
      getUrls().shortenedUrls.first { it.id == identifier || it.shortened == identifier }

  // TODO: this is for unshortening, make that more obvious...
  fun getOriginalUrlById(identifier: String) =
      getUrls().shortenedUrls.first { it.id == identifier }.url

  // TODO: localdatetime is defined directly here for convenience fix later..
  fun addUrl(shortenedUrl: ShortenedUrl) {
    jdbi.withHandle<Unit, Exception> { it.execute(
        "INSERT INTO shortened_url(id, url, shortened, created_date) VALUES (?, ?, ?, ?);",
        shortenedUrl.id, shortenedUrl.url, shortenedUrl.shortened, LocalDateTime.now()
    ) }
  }

  fun urlIdExists(identifier: String): Boolean {
    return getUrls().shortenedUrls.map { it.id }.contains(identifier)
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