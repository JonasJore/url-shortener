package service

import ShortenedUrlMapper
import app.jdbi
import domain.toShortenedUrl
import domain.ShortenedUrl
import domain.ShortenedUrlDTO
import domain.ShortenedUrls
import org.apache.log4j.Logger
import toShortenedUrl
import util.AlphanumericHashGenerator

private val logger: Logger = Logger.getLogger(UrlShortenerService::class.java)

class UrlShortenerService {
  private fun generateHash() = AlphanumericHashGenerator().generateHash()
  private val jdbi = jdbi()

  fun getUrls(): ShortenedUrls =
      ShortenedUrls(
         shortenedUrlList = jdbi.open()
              .createQuery("SELECT * FROM shortened_url")
              .mapToMap()
              .list()
              .toShortenedUrl()
      )

  fun getOriginalUrlById(identifier: String): String =
      getUrls().shortenedUrlList.first { it.id == identifier }.url

  fun getByIdOrShortened(identifier: String): ShortenedUrl =
      getUrls().shortenedUrlList.first { it.id == identifier || it.shortened == identifier }

  private fun prepareShortenedUrl(shortenedUrl: ShortenedUrlDTO): ShortenedUrl =
      ShortenedUrlMapper(generateHash(), shortenedUrl.url, shortenedUrl.shortenedUrl)
          .toShortenedUrl()

  fun addnewShortenedUrl(shortenedUrlDTO: ShortenedUrlDTO) {
    val shortenedUrl = prepareShortenedUrl(shortenedUrlDTO)
    jdbi.withHandle<Unit, Exception> { it.execute(
        "INSERT INTO shortened_url(id, url, shortened) VALUES (?, ?, ?);",
        shortenedUrl.id, shortenedUrl.url, shortenedUrl.shortened
    ) }
    logger.info("New url successfully added: $shortenedUrl")
  }

  fun deleteUrlById(id: String) {
    jdbi.useHandle<Exception> { it.execute("DELETE FROM shortened_url WHERE id = ?", id) }
    logger.info("Deleted url with id: $id")
  }

  fun changeById(id: String, shortenedUrlDTO: ShortenedUrlDTO) {
    jdbi.useHandle<Exception> { it.execute(
        "UPDATE shortened_url SET url = ?, shortened = ? where id = ?",
        shortenedUrlDTO.url, shortenedUrlDTO.shortenedUrl, id
    ) }
    logger.info("Updated url with id: $id")
  }
}

