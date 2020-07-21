package service

import ShortenedUrlMapper
import UrlShortenerRepository
import app.jdbi
import domain.toShortenedUrl
import domain.ShortenedUrl
import domain.ShortenedUrlDTO
import domain.ShortenedUrls
import org.apache.log4j.Logger
import toShortenedUrl
import util.AlphanumericHashGenerator
import java.lang.Exception
import java.time.LocalDateTime

private val logger: Logger = Logger.getLogger(UrlShortenerService::class.java)

class UrlShortenerService {
  private val urlShortenerRepository = UrlShortenerRepository()
  private val generateHash = AlphanumericHashGenerator().generateHash()

  private fun mapToShortenedUrl(shortenedUrl: ShortenedUrlDTO): ShortenedUrl =
      ShortenedUrlMapper(generateHash, shortenedUrl.url, shortenedUrl.shortenedUrl, LocalDateTime.now())
          .toShortenedUrl()

  fun getOriginalUrlById(identifier: String): String =
      urlShortenerRepository.getOriginalUrlById(identifier)

  fun getAllUrls(): ShortenedUrls =
      urlShortenerRepository.getAllUrls()

  fun getByIdOrShortened(identifier: String): ShortenedUrl =
      urlShortenerRepository.getUrlByIdOrShortened(identifier)

  fun addnewShortenedUrl(shortenedUrlDTO: ShortenedUrlDTO) {
    val shortenedUrl = mapToShortenedUrl(shortenedUrlDTO)
    urlShortenerRepository.addUrl(shortenedUrl)
    logger.info("New url successfully added: $shortenedUrl, with id: ${shortenedUrl.id}")
  }

  fun deleteUrlById(identifier: String) {
    urlShortenerRepository.deleteUrl(identifier)
  }

  fun changeById(id: String, shortenedUrlDTO: ShortenedUrlDTO) {
    urlShortenerRepository.updateById(id, shortenedUrlDTO)
    logger.info("Updated url with id: $id")

  }
}

