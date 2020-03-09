package service

import domain.ShortenedUrlDTO
import ShortenedUrlMapper
import ShortenedUrlSingleton
import domain.ShortenedUrl
import domain.ShortenedUrls
import domain.UrlChangeRequest
import org.apache.log4j.Logger
import toShortenedUrl
import util.AlphanumericHashGenerator
import util.FileOperations
import java.io.IOException

private val logger: Logger = Logger.getLogger(UrlShortenerService::class.java)

class UrlShortenerService {
  private fun generateHash() = AlphanumericHashGenerator().generateHash()

  fun findUniqueUrl(uniqueUrl: String): ShortenedUrl? =
      FileOperations()
          .readFileContent()
          .find { it.id == uniqueUrl }

  fun getByIdOrShortened(identifier: String): ShortenedUrl =
      FileOperations()
          .readFileContent()
          .filter { it.id == identifier || it.shortened == identifier }[0]

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

  fun changeUrlById(id: String, shortenedUrlDTO: ShortenedUrlDTO) {
    val urlList = FileOperations().readFileContent()
    val indexForChange: Int = urlList
        .indexOf(urlList.find { it.id == id })
    val mappedShortenedUrl = prepareShortenedUrl(shortenedUrlDTO)

    val urlChangeRequest = UrlChangeRequest(
        id,
        indexForChange,
        urlList,
        mappedShortenedUrl
    )

    FileOperations().changeById(urlChangeRequest, id)
  }

}

