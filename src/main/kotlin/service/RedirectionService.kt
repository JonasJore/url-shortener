package service

import org.apache.log4j.Logger
import java.awt.Desktop
import java.net.URL

private val logger: Logger = Logger.getLogger(RedirectionService::class.java)

class RedirectionService {

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
    val uniqueShortenedUrl = UrlShortenerService().getByIdOrShortened(identifier).url
    buildUrl(uniqueShortenedUrl)
    return uniqueShortenedUrl
  }

}