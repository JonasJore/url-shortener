package endpoints

import CreateShortenedUrlService
import ShortenedUrlDTO
import domain.ShortenedUrl
import domain.TestDTO
import org.apache.log4j.Logger
import java.lang.Exception
import java.time.LocalDate
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api")
class ShortenedUrlResource {
  private val logger: Logger = Logger.getLogger(ShortenedUrlResource::class.java)
  private val shortenUrlService = CreateShortenedUrlService()

  @Path("/hello")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun hello(): TestDTO {
    return TestDTO("Jonas", 23423L, LocalDate.now())
  }

  @Path("/shorten-url")
  @POST
  @Consumes("application/json")
  @Produces(MediaType.APPLICATION_JSON)
  fun shorten(shortenedUrlDTO: ShortenedUrlDTO): Response = try {
    logger.info(shortenedUrlDTO)
    shortenUrlService.addnewShortenedUrl(shortenedUrlDTO)
    Response.ok().build()
  } catch (ex: Exception) {
    logger.error("internal server error", ex)
    Response.status(500).build()
  }

  // TODO: this is supposed to return a ShortenedUrl not List<ShortenedUrl>
  @Path("/url/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun findUrl(@PathParam("id") id: String): List<ShortenedUrl> =
      shortenUrlService.deserialiseJsonFile()
          .filter { it.id == id }

  @Path("/urls")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getUrls(): List<ShortenedUrl> =
      shortenUrlService.deserialiseJsonFile()

}
