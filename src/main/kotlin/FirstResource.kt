import org.apache.log4j.Logger
import java.lang.Exception
import java.time.LocalDate
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

data class Example(val name: String, val id: Long, val date: LocalDate)

@Path("/api")
class FirstResource {

  private val logger: Logger = Logger.getLogger(FirstResource::class.java)

  @Path("/hello")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun hello(): Example {
    return Example("Jonas", 23423L, LocalDate.now())
  }

  @Path("/shorten-url")
  @POST
  @Consumes("application/json")
  @Produces(MediaType.APPLICATION_JSON)
  fun shorten(urlShortenDTO: UrlShortenDTO): Response {
    try {
      logger.info(urlShortenDTO)
      val shortenedUrl = ShortenedUrlMapper(urlShortenDTO.url, urlShortenDTO.shortenedUrl).toShortenedUrl()
      val shortenUrlService = ShortenUrlService(urlShortenDTO)
      logger.info(shortenedUrl)
      shortenUrlService.addnewShortenedUrl(shortenedUrl)

      return Response.ok().build()

    } catch (ex: Exception) {
      logger.error("internal server error", ex)
      return Response.status(500).build()
    }

  }
}