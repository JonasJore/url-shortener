package service

import RedirectionException
import org.apache.log4j.Logger
import java.net.URI
import java.net.URL

private val logger: Logger = Logger.getLogger(RedirectionService::class.java)

class RedirectionService {
  private val urlShortenerService = UrlShortenerService()
  private fun redirectHtml(uri: URI): String = "<meta http-equiv=\"refresh\" content=\"0; URL='${uri}'\" />"

  private fun buildUrl(identifier: String): String {
    val redirectUri = URL(identifier).toURI()
    logger.info("redirecting to $redirectUri")
    return try {
      redirectHtml(redirectUri)
    } catch (ex: RedirectionException) {
      logger.info("could not open url", ex)
      "${RedirectionException::class.java}"
    }
  }

  fun redirectToUrl(identifier: String): String =
    buildUrl(urlShortenerService.getByIdOrShortened(identifier).url)


}