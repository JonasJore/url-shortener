package endpoints

import domain.ShortenedUrlDTO
import domain.ShortenedUrl
import domain.ShortenedUrls
import domain.UnshortenedUrlResponse
import org.apache.log4j.Logger
import service.UrlShortenerService
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

private val logger: Logger = Logger.getLogger(ShortenedUrlResource::class.java)

@Path("/api")
class ShortenedUrlResource {
  private val shortenUrlService = UrlShortenerService()

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
    Response.status(
        Response.Status.INTERNAL_SERVER_ERROR
    ).build()
  }

  @Path("/unshorten-url/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun unshortenUrl(@PathParam("id") id: String): UnshortenedUrlResponse =
      UnshortenedUrlResponse(shortenUrlService.getOriginalUrlById(id))

  @Path("/url/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getUrlById(@PathParam("id") id: String): ShortenedUrl {
    return shortenUrlService.getByIdOrShortened(id)
  }

  @Path("/urls")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getAllUrls(): ShortenedUrls {
    logger.info("Fetching all urls")
    return shortenUrlService.getAllUrls()
  }

  @Path("/url/{id}")
  @DELETE
  fun deleteUrl(@PathParam("id") id: String): Response = try {
    shortenUrlService.deleteUrlById(id)
    Response.status(Response.Status.ACCEPTED).build()
  } catch (ex: Exception) {
    logger.error("something wrong while deleting url", ex)
    Response.status(
        Response.Status.INTERNAL_SERVER_ERROR
    ).build()
  }

  @Path("/url/{id}")
  @PUT
  @Consumes("application/json")
  fun changeUrlById(@PathParam("id") id: String, shortenedUrlDTO: ShortenedUrlDTO): Response = try {
    shortenUrlService.changeById(id, shortenedUrlDTO)
    Response.ok().build()
  } catch (ex: Exception) {
    logger.error("Something wrong happened while changing url", ex)
    Response.status(
        Response.Status.INTERNAL_SERVER_ERROR
    ).build()
  }
}