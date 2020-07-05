package endpoints

import exceptions.RedirectionException
import org.apache.log4j.Logger
import service.RedirectionService
import service.UrlShortenerService
import isUrlValid
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

private val logger: Logger = Logger.getLogger(RedirectResource::class.java)

@Path("/")
class RedirectResource {
  private val redirectionService = RedirectionService()
  private val urlShortenerService = UrlShortenerService()

  @Path("/{uniqueurl}")
  @GET
  fun redirect(@PathParam("uniqueurl") uniqueUrl: String): Response {
    try {
      logger.info("redirecting to url: $uniqueUrl")
      logger.info("${isUrlValid(uniqueUrl)}")
      if (isUrlValid(uniqueUrl)) {
        return urlShortenerService.getByIdOrShortened(uniqueUrl).url
            .let {
              redirectionService.redirectToUrl(it)
            }
      }

      throw RedirectionException("Failed to redirect to url: $uniqueUrl")
    } catch (ex: RedirectionException) {
      logger.info("Redirection failed due to: ${ex.stackTrace}")
      Response.status(
          Response.Status.INTERNAL_SERVER_ERROR
      ).build()
      throw RedirectionException("failed to redirect to $uniqueUrl")
    }
  }
}