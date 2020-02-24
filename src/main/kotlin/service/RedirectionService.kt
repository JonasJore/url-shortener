package service

import org.apache.log4j.Logger
import java.net.URL
import javax.ws.rs.core.Response

private val logger: Logger = Logger.getLogger(RedirectionService::class.java)

const val MATCH_ON_PROTOCOL = "^(?:(http)(s?)://)"

class RedirectionService {
  private val urlShortenerService = UrlShortenerService()

  private fun hasProtocol(url: String): Boolean {

    return Regex(MATCH_ON_PROTOCOL)
        .let { it containsMatchIn  url }
  }

  // TODO: legg try/catch i endepunktet istedenfor her
  private fun buildUrl(identifier: String): Response {
    val redirectUri = URL(identifier).toURI()
    logger.info("redirecting to $redirectUri")

    logger.info(hasProtocol("================"))
    logger.info(hasProtocol(identifier))

    return Response.status(
        Response.Status.MOVED_PERMANENTLY
    ).location(redirectUri).build()
  }

  fun redirectToUrl(identifier: String) =
      buildUrl(urlShortenerService.getByIdOrShortened(identifier).url)

}

infix fun Regex.containsMatchIn(s: String): Boolean =
    this.containsMatchIn(s)