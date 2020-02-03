import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import domain.ShortenedUrl
import domain.ShortenedUrls
import org.apache.log4j.Logger
import java.io.File
import java.io.IOException
import java.nio.file.Paths

class ShortenUrlService() {
  private val logger: Logger = Logger.getLogger(ShortenUrlService::class.java)
  private val jsonfileName = "shortened-urls.json"
  private var identifier: Int = 0

  fun makeNewIdentifier(): String {
    identifier += 1
    return identifier.toString()
  }

  fun prepareShortenedUrl(shortenedUrl: ShortenUrlDTO): ShortenedUrl =
      ShortenedUrlMapper(makeNewIdentifier(), shortenedUrl.url, shortenedUrl.shortenedUrl)
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

  fun addnewShortenedUrl(shortenUrl: ShortenUrlDTO) {
    // todo: refactor
    val shortenedUrl = ShortenedUrlMapper(
        makeNewIdentifier(),
        shortenUrl.url,
        shortenUrl.shortenedUrl
    ).toShortenedUrl()

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

