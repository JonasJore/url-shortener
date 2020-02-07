package endpoints

import domain.TestDTO
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
fun test(): TestDTO {
  return TestDTO("celebrimbor", 1L, LocalDate.now())
}

}