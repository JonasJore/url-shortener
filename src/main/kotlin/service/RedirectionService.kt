package service

import domain.ShortenedUrl
import org.apache.log4j.Logger
import java.awt.Desktop
import java.lang.Exception
import java.net.URL

class RedirectionService {

  val logger: Logger = Logger.getLogger(RedirectionService::class.java)

  private fun findUniqueUrl(uniqueUrl: String): List<ShortenedUrl> =
      UrlShortenerService().deserialiseJsonFile().filter { it.shortened == uniqueUrl }

  private fun buildUrl(identifier: String) {
    val url = URL("http://$identifier").toURI()
    logger.info(url)
    val desktop = Desktop.getDesktop()
    try {
      desktop.browse(url)
    } catch (ex: Exception) {
      logger.info("could not open url", ex)
    }
  }

  fun redirectToUrl(identifier: String): String {
    val uniqueShortenedUrl = findUniqueUrl(identifier)[0].url
    buildUrl(uniqueShortenedUrl)
    return uniqueShortenedUrl
  }
}


