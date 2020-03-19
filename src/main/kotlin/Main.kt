import Jetty.JettyServer
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlShortenerAPI

fun main(args: Array<String>) {
  runApplication<UrlShortenerAPI>(*args) {
    // custom stuff
  }
  //  JettyServer().startServer()
}
