package service

import org.apache.log4j.Logger
import java.net.URL
import javax.ws.rs.core.Response

private val logger: Logger = Logger.getLogger(RedirectionService::class.java)

private const val MATCH_ON_PROTOCOL = "^(?:(http)(s?)://)"

class RedirectionService {

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

  fun redirectToUrl(identifier: String): Response =
      when {
        !hasProtocol(identifier) -> buildUrl("https://$identifier")
        else -> buildUrl(identifier)
      }
}

infix fun Regex.containsMatchIn(s: String): Boolean =
    this.containsMatchIn(s)