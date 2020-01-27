import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import domain.ShortenedUrl
import org.apache.log4j.Logger
import java.io.File

class ShortenUrlService(val shortenedUrl: UrlShortenDTO) {
  private val logger: Logger = Logger.getLogger(ShortenUrlService::class.java)

  fun prepareShortenedUrl(shortenedUrl: UrlShortenDTO): ShortenedUrl =
      ShortenedUrlMapper(shortenedUrl.url, shortenedUrl.shortenedUrl)
          .toShortenedUrl()

  private fun createShortenedUrlFile(): File {
    val file = File("./shortened-urls.json")
    if(!file.exists()) {
      file.createNewFile()
      logger.info("${file.name} created")
    }
    return file
  }

  fun addnewShortenedUrl(shortenedUrl: ShortenedUrl) {
    val objectWriter: ObjectWriter = ObjectMapper().writer(DefaultPrettyPrinter())
    val list = listOf(shortenedUrl)
    objectWriter.writeValue(createShortenedUrlFile(), list)
    logger.info("done")
  }
}