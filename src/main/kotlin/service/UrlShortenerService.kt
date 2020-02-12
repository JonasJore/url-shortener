package service

import ShortenedUrlDTO
import ShortenedUrlMapper
import ShortenedUrlSingleton
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import domain.ShortenedUrl
import domain.ShortenedUrls
import org.apache.log4j.Logger
import toShortenedUrl
import util.AlphanumericHashGenerator
import util.FileOperations
import java.io.IOException
import java.nio.file.Paths

class UrlShortenerService {
  private val logger: Logger = Logger.getLogger(UrlShortenerService::class.java)
  private val jsonfileName: String = "shortened-urls.json"

  private fun generateHash() = AlphanumericHashGenerator().generateHash()

  fun prepareShortenedUrl(shortenedUrl: ShortenedUrlDTO): ShortenedUrl =
      ShortenedUrlMapper(generateHash(), shortenedUrl.url, shortenedUrl.shortenedUrl)
          .toShortenedUrl()

  fun addnewShortenedUrl(shortenedUrlDTO: ShortenedUrlDTO) {
    val shortenedUrl = prepareShortenedUrl(shortenedUrlDTO)
    logger.info(FileOperations().readUrlsFromFile())
    val shortenedUrls: ShortenedUrls = ShortenedUrlSingleton.addToShortenedUrls(shortenedUrl)

    try {
      FileOperations().writeUrlsToStorageFile(shortenedUrls)
      logger.info("written to file")
    } catch (ex: IOException) {
      logger.error("could not write to file", ex)
    }
  }
}

