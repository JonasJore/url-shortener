val repository = UrlShortenerRepository()

fun isUrlValid(identifier: String): Boolean =
    repository.urlIdExists(identifier) && assertIdLength(identifier)

fun assertIdLength(identifier: String): Boolean =
    identifier.length == 10