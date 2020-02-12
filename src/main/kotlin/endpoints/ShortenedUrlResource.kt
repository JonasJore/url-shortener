package endpoints

import service.UrlShortenerService
import ShortenedUrlDTO
import domain.ShortenedUrl
import domain.TestDTO
import org.apache.log4j.Logger
import util.FileOperations
import java.lang.Exception
import java.time.LocalDate
import javax.print.attribute.standard.Media
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
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
  private val shortenUrlService = UrlShortenerService()
  private val fileOperations = FileOperations()

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
  fun addShortenedUrl(shortenedUrlDTO: ShortenedUrlDTO): Response = try {
    logger.info(shortenedUrlDTO)
    shortenUrlService.addnewShortenedUrl(shortenedUrlDTO)
    Response.ok().build()
  } catch (ex: Exception) {
    logger.error("internal server error", ex)
    Response.status(500).build()
  }

  @Path("/url/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getUrl(@PathParam("id") id: String): ShortenedUrl =
      fileOperations.deserialiseJsonFile()
          .filter { it.id == id }[0]

  @Path("/urls")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getUrls(): List<ShortenedUrl> =
      fileOperations.deserialiseJsonFile()

  @Path("/url/{id}")
  @DELETE
  fun deleteUrl(@PathParam("id") id: String): Response = try {
    fileOperations.deleteById(id)
    logger.info(fileOperations.deserialiseJsonFile())
    Response.status(200).build()
  } catch (ex: Exception) {
    logger.error("something wrong while deleting url", ex)
    Response.status(500).build()
  }

}
