import domain.ShortenedUrlDTO
import service.containsMatchIn

const val VALID_URL_REGEX = "/((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)/"

val repository = UrlShortenerRepository()

fun isUrlValid(identifier: String): Boolean =
    repository.urlIdExists(identifier) && assertIdLength(identifier)

fun assertIdLength(identifier: String): Boolean =
    identifier.length.equals(10)

fun validShortenedUrlDTO(shortenedUrlDTO: ShortenedUrlDTO): Boolean {
  val validUrl = VALID_URL_REGEX.toRegex().let { it containsMatchIn shortenedUrlDTO.url }
  if (validUrl) {
    return true
  }

  return false
}