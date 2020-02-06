import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import domain.ShortenedUrl
import domain.ShortenedUrls
import org.apache.log4j.Logger
import util.ShortenedUrlResource
import java.io.IOException
import java.nio.file.Paths

class CreateShortenedUrlService {
  private val logger: Logger = Logger.getLogger(CreateShortenedUrlService::class.java)
  private val jsonfileName: String = "shortened-urls.json"
  private var identifier: Int = 0

  // for now this is how i uniquely identify a shortened url
  fun makeNewIdentifier(): String {
    identifier += 1
    return identifier.toString()
  }

  fun prepareShortenedUrl(shortenedUrl: ShortenedUrlDTO): ShortenedUrl =
      ShortenedUrlMapper(makeNewIdentifier(), shortenedUrl.url, shortenedUrl.shortenedUrl)
          .toShortenedUrl()

  private fun writeUrlsToStorageFile(shortenedUrls: ShortenedUrls): Unit =
      ObjectMapper()
          .writeValue(
              Paths.get(jsonfileName)
                  .toFile(),
              shortenedUrls.shortenedUrls
          )

  fun addnewShortenedUrl(shortenedUrlDTO: ShortenedUrlDTO) {
    ShortenedUrlResource().readUrlsFromFile()
    val shortenedUrl = prepareShortenedUrl(shortenedUrlDTO)

    logger.info("======================")
    logger.info(ShortenedUrlResource().readUrlsFromFile())
    logger.info(deserialiseJsonFile())
    val shortenedUrls: ShortenedUrls = ShortenedUrlSingleton.addToShortenedUrls(shortenedUrl)

    try {
      writeUrlsToStorageFile(shortenedUrls)
      logger.info("written to file")
    } catch (ex: IOException) {
      logger.error("could not write to file", ex)
    }
  }

  // the goal is to deserialise the json into the ShortenedUrl::class not a List.
  fun deserialiseJsonFile(): List<ShortenedUrl> {
    val mapper = ObjectMapper()
    val jsonString: String = ShortenedUrlResource().readUrlsFromFile()
    val urlCollection: List<ShortenedUrl> = mapper.readValue(jsonString, object : TypeReference<List<ShortenedUrl>>() {})
    logger.info(urlCollection.size)

    return urlCollection
  }
}

