package endpoints

import org.apache.log4j.Logger
import service.RedirectionService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

private val logger: Logger = Logger.getLogger(RedirectResource::class.java)

@Path("/")
class RedirectResource {

  @Path("/{uniqueurl}")
  @GET
  fun gotoUrl(@PathParam("uniqueurl") uniqueUrl: String) = try {
    RedirectionService().redirectToUrl(uniqueUrl)
  } catch (ex: Exception) {
    logger.error("something wrong while redirecting", ex)
  }
}