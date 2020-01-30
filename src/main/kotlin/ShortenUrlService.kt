import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import domain.ShortenedUrl
import domain.ShortenedUrls
import org.apache.log4j.Logger
import java.io.File
import java.io.IOException
import java.nio.file.Paths

class ShortenUrlService(val shortenedUrl: UrlShortenDTO) {
  private val logger: Logger = Logger.getLogger(ShortenUrlService::class.java)
  private val jsonfileName = "shortened-urls.json"

  fun prepareShortenedUrl(shortenedUrl: UrlShortenDTO): ShortenedUrl =
      ShortenedUrlMapper(shortenedUrl.url, shortenedUrl.shortenedUrl)
          .toShortenedUrl()

  private fun writeUrlsToStorageFile(shortenedUrls: ShortenedUrls): Unit =
      ObjectMapper()
          .writer(DefaultPrettyPrinter())
          .writeValue(
              Paths.get(jsonfileName)
                  .toFile(),
              shortenedUrls
                  .shortenedUrls
          )

  //TODO: find better solution for this later
  private fun readUrlsFromFile(): List<String> =
      File(jsonfileName).useLines { it.toList() }

  fun addnewShortenedUrl(shortenedUrl: ShortenedUrl) {
    logger.info(readUrlsFromFile())
    val shortenedUrls = ShortenedUrlSingleton.addToShortenedUrls(shortenedUrl)
    try {
      writeUrlsToStorageFile(shortenedUrls)
      logger.info("written to file")
    } catch (ex: IOException) {
      logger.error("could not write to file", ex)
    }
  }
}

// TODO: refactor later
// TODO: consider turning mutableListOf insto ShortenedUrls class :)
object ShortenedUrlSingleton {
  // wat even is this init??
  val urls = ShortenedUrls(mutableListOf())

  fun addToShortenedUrls(shortenedUrl: ShortenedUrl): ShortenedUrls {
    urls.shortenedUrls.add(shortenedUrl)
    return urls
  }
}