import app.jdbi
import domain.ShortenedUrl
import domain.ShortenedUrlDTO
import domain.ShortenedUrls
import domain.toShortenedUrl
import java.sql.SQLException

private val INSERT_SHORTENED_URL_QUERY = """
    INSERT INTO shortened_url(
      uuid, shortened_url_id, original_url, shortened_url_name, created_date
      ) VALUES (?, ?, ?, ?, ?);
    """.trimIndent()

private val UPDATE_SHORTENED_URL_QUERY = """
  UPDATE shortened_url
  SET original_url = ?, shortened_url_name = ? where shortened_url_id = ?;
  """.trimIndent()

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

  fun addUrl(shortenedUrl: ShortenedUrl) {
    try {
      jdbi.withHandle<Unit, Exception> {
        with(shortenedUrl) {
          it.execute(
            INSERT_SHORTENED_URL_QUERY, uuid, id, url, shortened, createdDate
          )
        }
      }
    } catch (ex: SQLException) {
      throw ex
    }
  }

  fun urlIdExists(identifier: String): Boolean {
    return getUrls().shortenedUrls.map { it.id }.contains(identifier)
  }

  fun deleteUrl(identifier: String) {
    jdbi.useHandle<Exception> { it.execute("DELETE FROM shortened_url WHERE id = ?", identifier) }
  }

  fun updateById(identifier: String, shortenedUrlDTO: ShortenedUrlDTO) {
    jdbi.useHandle<Exception> {
      with(shortenedUrlDTO) {
        it.execute(
          UPDATE_SHORTENED_URL_QUERY,
          url, shortenedUrl, identifier
        )
      }
    }
  }
}
