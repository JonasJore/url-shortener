package service

import org.apache.http.client.methods.RequestBuilder
import org.apache.http.impl.client.DefaultRedirectStrategy
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
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
    val client = HttpClientBuilder
        .create()
        .setRedirectStrategy(DefaultRedirectStrategy())
        .build()

    val request = RequestBuilder.get()
        .setUri(redirectUri)
        .setHeader("Location", identifier)
        .build()

//    return EntityUtils.toString(client.execute(request).entity)
    return redirectHtml(URL(identifier).toURI())

  }

  fun redirectToUrl(identifier: String) =
      buildUrl(urlShortenerService.getByIdOrShortened(identifier).url)


}