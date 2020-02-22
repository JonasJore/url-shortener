package service

import org.apache.log4j.Logger
import java.net.URL
import javax.ws.rs.core.Response

private val logger: Logger = Logger.getLogger(RedirectionService::class.java)

class RedirectionService {
  private val urlShortenerService = UrlShortenerService()

  // TODO: legg try/catch i endepunktet istedenfor her
  private fun buildUrl(identifier: String): Response {
    val redirectUri = URL("https://$identifier").toURI()
    logger.info("redirecting to $redirectUri")

    return Response.status(
        Response.Status.TEMPORARY_REDIRECT
    ).location(redirectUri).build()
  }

  fun redirectToUrl(identifier: String) =
      buildUrl(urlShortenerService.getByIdOrShortened(identifier).url)

}