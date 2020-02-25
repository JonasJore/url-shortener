package service

import org.apache.log4j.Logger
import java.net.URL
import javax.ws.rs.core.Response

private val logger: Logger = Logger.getLogger(RedirectionService::class.java)

private const val MATCH_ON_PROTOCOL = "^(?:(http)(s?)://)"

class RedirectionService {
  private val urlShortenerService = UrlShortenerService()

  private fun hasProtocol(url: String): Boolean =
      MATCH_ON_PROTOCOL.toRegex()
          .let { it containsMatchIn url }

  private fun buildUrl(redirectUrlString: String): Response =
      URL(redirectUrlString)
          .toURI()
          .let {
            Response.status(
                Response.Status.MOVED_PERMANENTLY
            ).location(it).build()
          }


  fun redirectToUrl(identifier: String): Response {
    val url = urlShortenerService.getByIdOrShortened(identifier).url
    logger.info("redirecting to $url")
    return when {
      !hasProtocol(url) -> buildUrl("https://$url")
      else -> buildUrl(url)
    }
  }
}

infix fun Regex.containsMatchIn(s: String): Boolean =
    this.containsMatchIn(s)