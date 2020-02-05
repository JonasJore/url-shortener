import com.fasterxml.jackson.core.type.TypeReference
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
          .writeValue(
              Paths.get(jsonfileName)
                  .toFile(),
              shortenedUrls.shortenedUrls
          )

  private fun readUrlsFromFile(): String =
      File(jsonfileName).readText(Charsets.UTF_8)

  fun addnewShortenedUrl(shortenUrl: ShortenUrlDTO) {
    readUrlsFromFile()
    val shortenedUrl = ShortenedUrlMapper(
        makeNewIdentifier(),
        shortenUrl.url,
        shortenUrl.shortenedUrl
    ).toShortenedUrl()

    logger.info("======================")
    logger.info(readUrlsFromFile())
    logger.info(deserialiseJsonFile())
    val shortenedUrls: ShortenedUrls = ShortenedUrlSingleton.addToShortenedUrls(shortenedUrl)
    try {
      writeUrlsToStorageFile(shortenedUrls)
      logger.info("written to file")
    } catch (ex: IOException) {
      logger.error("could not write to file", ex)
    }
  }

  private fun deserialiseJsonFile(): List<ShortenedUrl> {
    val mapper = ObjectMapper()
    val jsonString: String = readUrlsFromFile()
    val urlCollection: List<ShortenedUrl> = mapper.readValue(jsonString, object : TypeReference<List<ShortenedUrl>>() {})
    logger.info(urlCollection.size)

    return urlCollection
  }
}

