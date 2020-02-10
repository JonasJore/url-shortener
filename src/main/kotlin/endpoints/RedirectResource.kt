package endpoints

import domain.TestDTO
import org.apache.log4j.Logger
import service.RedirectionService
import java.time.LocalDate
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class RedirectResource {
  private val logger: Logger = Logger.getLogger(RedirectResource::class.java)

  @Path("/test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun test(): TestDTO {
    return TestDTO("celebrimbor", 1L, LocalDate.now())
  }

  @Path("/{uniqueurl}")
  @GET
  fun gotoUrl(@PathParam("uniqueurl") uniqueUrl: String): Unit {
    try {
      logger.info("unique: ${uniqueUrl}")
      logger.info(RedirectionService().redirectToUrl(uniqueUrl))
    } catch (ex: Exception) {
      logger.error("something wrong while redirecting", ex)
    }
  }

}