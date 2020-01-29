import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import domain.ShortenedUrl
import domain.ShortenedUrls
import org.apache.log4j.Logger
import java.io.File
import java.io.FileOutputStream

class ShortenUrlService(val shortenedUrl: UrlShortenDTO) {
  private val logger: Logger = Logger.getLogger(ShortenUrlService::class.java)

  fun prepareShortenedUrl(shortenedUrl: UrlShortenDTO): ShortenedUrl =
      ShortenedUrlMapper(shortenedUrl.url, shortenedUrl.shortenedUrl)
          .toShortenedUrl()

  private fun createShortenedUrlFile(): File {
    val file = File("./shortened-urls.json")
    if (file.createNewFile()) {
      logger.info("${file.name} created")
    } else {
      logger.info("${file.name} already exists")
    }
    return file
  }

  private fun writeUrlsToStorageFile(shortenedUrl: String): Boolean {
    FileOutputStream(createShortenedUrlFile(), true).bufferedWriter().use { writer ->
      // TODO: stuff must be written as string here
      writer.write(shortenedUrl)
    }

    logger.info("written to file")

    return true
  }

  fun addnewShortenedUrl(shortenedUrl: ShortenedUrl) {
    val shortenedUrls = ShortenedUrlSingleton.addToShortenedUrls(shortenedUrl)
    logger.info(shortenedUrls.shortenedUrls.size)
    val objectWriter = ObjectMapper().writer(DefaultPrettyPrinter())
    val jsonShortenedUrl = objectWriter.writeValueAsString(shortenedUrls)
    writeUrlsToStorageFile(jsonShortenedUrl)
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