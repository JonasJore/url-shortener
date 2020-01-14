package resource

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/")
class FirstResource {
  @Path("/hello")
  @GET
  fun hello(): String {
    return "hello"
  }
}