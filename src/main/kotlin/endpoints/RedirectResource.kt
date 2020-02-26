package endpoints

import RedirectionException
import org.apache.log4j.Logger
import service.RedirectionService
import service.UrlShortenerService
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
  fun redirect(@PathParam("uniqueurl") uniqueUrl: String): Response = try {
    logger.info("redirecting to url: $uniqueUrl")
    urlShortenerService.getByIdOrShortened(uniqueUrl).url
        .let {
          redirectionService.redirectToUrl(it)
        }
  } catch (ex: RedirectionException) {
    logger.info("Redirection failed due to: ${ex.stackTrace}")
    Response.status(
        Response.Status.INTERNAL_SERVER_ERROR
    ).build()
  }
}