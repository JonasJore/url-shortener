package service

import util.containsMatchIn
import java.net.URL
import javax.ws.rs.core.Response

private const val MATCH_ON_PROTOCOL = "^(?:(http)(s?)://)"

class RedirectionService {
  fun hasProtocol(url: String): Boolean =
      MATCH_ON_PROTOCOL.toRegex()
          .let { it containsMatchIn url }

  private fun buildUrl(redirectUrlString: String): Response =
      URL(redirectUrlString)
          .toURI()
          .let { uri ->
            Response.status(
                Response.Status.MOVED_PERMANENTLY
            ).location(uri).build()
          }

  fun redirectToUrl(identifier: String): Response =
      when {
        !hasProtocol(identifier) -> buildUrl("https://$identifier")
        else -> buildUrl(identifier)
      }
}