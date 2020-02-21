package endpoints

import org.apache.http.client.methods.RequestBuilder
import org.apache.http.impl.client.DefaultRedirectStrategy
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.log4j.Logger
import service.RedirectionService
import java.net.http.HttpResponse
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
    redirectionService.redirectToUrl(uniqueUrl)

    return Response.status(302).build()
  }
//  } catch (ex: Exception) {
//    logger.error("something wrong while redirecting", ex)
//  }
}