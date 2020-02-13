package service

import domain.ShortenedUrl
import org.apache.log4j.Logger
import util.FileOperations
import java.awt.Desktop
import java.lang.Exception
import java.net.URL

private val logger: Logger = Logger.getLogger(RedirectionService::class.java)

class RedirectionService {
  private fun findUniqueUrl(uniqueUrl: String): ShortenedUrl =
    FileOperations()
        .readFileContent()
        .filter { it.shortened == uniqueUrl || it.id == uniqueUrl }[0]

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
    val uniqueShortenedUrl = findUniqueUrl(identifier).url
    buildUrl(uniqueShortenedUrl)
    return uniqueShortenedUrl
  }

}