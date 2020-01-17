package resource

import org.apache.log4j.Logger
import java.time.LocalDate
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

data class Example(val name: String, val id: Long, val date: LocalDate)

@Path("/")
class FirstResource {

  private val logger: Logger = Logger.getLogger(FirstResource::class.java)

  @Path("/hello")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun hello(): Example {
    return Example("Jonas", 23423L, LocalDate.now())
  }
}