package endpoints

import exceptions.RedirectionException
import service.RedirectionService
import service.UrlShortenerService
import isUrlValid
import org.slf4j.LoggerFactory
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

@Path("/")
class RedirectResource {
  val logger = LoggerFactory.getLogger(RedirectResource::class.java)

  private val redirectionService = RedirectionService()
  private val urlShortenerService = UrlShortenerService()

  @Path("/{uniqueurl}")
  @GET
  fun redirect(@PathParam("uniqueurl") uniqueUrl: String): Response {
    logger.info("redirecting to {}", uniqueUrl)
    try {
      if (isUrlValid(uniqueUrl)) {
        return urlShortenerService.getByIdOrShortened(uniqueUrl).url
            .let {
              redirectionService.redirectToUrl(it)
            }
      }

      throw RedirectionException("Failed to redirect to url: $uniqueUrl")
    } catch (ex: RedirectionException) {
      logger.info("Failed to redirect to url: $uniqueUrl")
      return Response.status(
          Response.Status.INTERNAL_SERVER_ERROR
      ).build()
    }
  }
}