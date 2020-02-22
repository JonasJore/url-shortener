package service

import org.apache.log4j.Logger
import java.net.URL
import RedirectionException
import javax.ws.rs.core.Response

private val logger: Logger = Logger.getLogger(RedirectionService::class.java)

class RedirectionService {
  private val urlShortenerService = UrlShortenerService()

  // TODO: rydd opp senere
  private fun buildUrl(identifier: String): Response {
    val redirectUri = URL("https://$identifier").toURI()

    logger.info("redirecting to $redirectUri")

    return try {
      Response.status(
          Response.Status.TEMPORARY_REDIRECT
      ).location(redirectUri).build()
    } catch (ex: RedirectionException) {
      logger.info(ex)
      Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
    }
  }

  fun redirectToUrl(identifier: String) =
      buildUrl(urlShortenerService.getByIdOrShortened(identifier).url)
}