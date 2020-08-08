package endpoints

import domain.ShortenedUrlDTO
import domain.ShortenedUrl
import domain.ShortenedUrls
import domain.UnshortenedUrlResponse
import org.slf4j.LoggerFactory
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

@Path("/api")
class ShortenedUrlResource {
    val logger = LoggerFactory.getLogger(ShortenedUrlResource::class.java)
    private val shortenUrlService = UrlShortenerService()

    @Path("/shorten-url")
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    fun addShortenedUrl(shortenedUrlDTO: ShortenedUrlDTO): Response {
        return try {
            logger.info("Shortening url: ${shortenedUrlDTO.url}")
            shortenUrlService.addnewShortenedUrl(shortenedUrlDTO)
            Response.status(
                    Response.Status.ACCEPTED
            ).build()
        } catch (ex: Exception) {
            Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).build()
        }
    }

    @Path("/unshorten-url/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun unshortenUrl(@PathParam("id") id: String): UnshortenedUrlResponse {
        logger.info("Unshortening url with id: $id")
        return UnshortenedUrlResponse(shortenUrlService.getOriginalUrlById(id))
    }

    @Path("/url/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getUrlById(@PathParam("id") id: String): ShortenedUrl {
        logger.info("Getting url with id: $id")
        return shortenUrlService.getByIdOrShortened(id)
    }

    @Path("/urls")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllUrls(): ShortenedUrls {
        logger.info("Looking up all urls")
        return shortenUrlService.getAllUrls()
    }

    @Path("/url/{id}")
    @DELETE
    fun deleteUrl(@PathParam("id") id: String): Response {
        return try {
            logger.info("Deleting url with id: $id")
            shortenUrlService.deleteUrlById(id)
            Response.status(
                    Response.Status.ACCEPTED
            ).build()
        } catch (ex: Exception) {
            Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).build()
        }
    }

    @Path("/url/{id}")
    @PUT
    @Consumes("application/json")
    fun changeUrlById(@PathParam("id") id: String, shortenedUrlDTO: ShortenedUrlDTO): Response {
        return try {
            shortenUrlService.changeById(id, shortenedUrlDTO)
            Response.status(
                    Response.Status.ACCEPTED
            ).build()
        } catch (ex: Exception) {
            Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).build()
        }
    }
}