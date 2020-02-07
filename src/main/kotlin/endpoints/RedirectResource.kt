package endpoints

import java.awt.PageAttributes
import java.time.LocalDate
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class RedirectResource {

@Path("/test")
@GET
@Produces(MediaType.APPLICATION_JSON)
fun test(): Example {
  return Example("celebrimbor", 1L, LocalDate.now())
}

}