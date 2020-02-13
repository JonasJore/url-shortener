package service

import ShortenedUrlDTO
import ShortenedUrlMapper
import ShortenedUrlSingleton
import domain.ShortenedUrl
import domain.ShortenedUrls
import org.apache.log4j.Logger
import toShortenedUrl
import util.AlphanumericHashGenerator
import util.FileOperations
import java.io.IOException

private val logger: Logger = Logger.getLogger(UrlShortenerService::class.java)

class UrlShortenerService {
  private fun generateHash() = AlphanumericHashGenerator().generateHash()

  fun getById(id: String): ShortenedUrl =
      FileOperations()
          .readFileContent()
          .filter { it.id == id }[0]

  private fun prepareShortenedUrl(shortenedUrl: ShortenedUrlDTO): ShortenedUrl =
      ShortenedUrlMapper(generateHash(), shortenedUrl.url, shortenedUrl.shortenedUrl)
          .toShortenedUrl()

  fun addnewShortenedUrl(shortenedUrlDTO: ShortenedUrlDTO) {
    val shortenedUrl = prepareShortenedUrl(shortenedUrlDTO)
    logger.info(FileOperations().readUrlsFromFile())
    val shortenedUrls: ShortenedUrls = ShortenedUrlSingleton.addToShortenedUrls(shortenedUrl)

    try {
      FileOperations().writeToFile(shortenedUrls)
      logger.info("written to file")
    } catch (ex: IOException) {
      logger.error("could not write to file", ex)
    }
  }

}

