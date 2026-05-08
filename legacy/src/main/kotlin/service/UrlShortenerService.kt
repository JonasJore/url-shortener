package service

import ShortenedUrlMapper
import UrlShortenerRepository
import domain.ShortenedUrl
import domain.ShortenedUrlDTO
import domain.ShortenedUrls
import java.time.LocalDateTime
import java.util.UUID
import toShortenedUrl
import util.AlphanumericHashGenerator

class UrlShortenerService {
  private val urlShortenerRepository = UrlShortenerRepository()

  fun getOriginalUrlById(identifier: String): String =
    urlShortenerRepository.getOriginalUrlById(identifier)

  fun getAllUrls(): ShortenedUrls =
    urlShortenerRepository.getAllUrls()

  fun getByIdOrShortened(identifier: String): ShortenedUrl =
    urlShortenerRepository.getUrlByIdOrShortened(identifier)

  fun addnewShortenedUrl(shortenedUrlDTO: ShortenedUrlDTO) {
    val shortenedUrl = shortenedUrlDTO.mapToShortenedUrl()
    urlShortenerRepository.addUrl(shortenedUrl)
  }

  fun deleteUrlById(identifier: String) {
    urlShortenerRepository.deleteUrl(identifier)
  }

  fun changeById(id: String, shortenedUrlDTO: ShortenedUrlDTO) {
    urlShortenerRepository.updateById(id, shortenedUrlDTO)
  }
}

private fun ShortenedUrlDTO.mapToShortenedUrl(): ShortenedUrl {
  val uuid = UUID.randomUUID().toString()
  val id = AlphanumericHashGenerator.generateHash()
  with(this) {
    return ShortenedUrlMapper(
      uuid = uuid,
      identifier = id,
      originalUrl = url,
      shortenedUrl = shortenedUrl,
      createdDate = LocalDateTime.now().toString()
    ).toShortenedUrl()
  }
}
