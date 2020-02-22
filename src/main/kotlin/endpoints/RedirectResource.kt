package endpoints

import org.apache.log4j.Logger
import service.RedirectionService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

private val logger: Logger = Logger.getLogger(RedirectResource::class.java)

@Path("/")
class RedirectResource {

  private val redirectionService = RedirectionService()

  @Path("/{uniqueurl}")
  @GET
  fun gotoUrl(@PathParam("uniqueurl") uniqueUrl: String): Response {
    return redirectionService.redirectToUrl(uniqueUrl)
  }

}