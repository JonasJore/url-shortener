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

  private fun writeUrlsToStorageFile(shortenedUrls: ShortenedUrl): Boolean {

    FileOutputStream(createShortenedUrlFile(), true).bufferedWriter().use { writer ->
      // TODO: stuff must be written as string here

//      writer.write(urls.last().toString())
      writer.write(shortenedUrl.toString())
      logger.info("written to file")
    }

    return true
  }

  fun addnewShortenedUrl(shortenedUrl: ShortenedUrl) {
    val objectWriter = ObjectMapper().writer(DefaultPrettyPrinter())
    val listOfUrls = ShortenedUrls(listOf(shortenedUrl))
    writeUrlsToStorageFile(shortenedUrl)
    val jsonList = objectWriter.writeValueAsString(listOfUrls)
    logger.info(jsonList)
  }
}