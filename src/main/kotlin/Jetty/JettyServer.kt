package Jetty

import org.apache.log4j.Logger
import org.eclipse.jetty.server.Server
import org.glassfish.jersey.jetty.JettyHttpContainerFactory
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import javax.ws.rs.core.UriBuilder

const val URI = "http://localhost/"
const val PORT = 8080

//@SpringBootApplication
class JettyServer {
  private val logger: Logger = Logger.getLogger(JettyServer::class.java)
  fun startServer() {
    logger.info("Firing up the server")
    JettyHttpContainerFactory.createServer(
        UriBuilder
            .fromUri(URI)
            .port(PORT)
            .build(),
        ResourceConfig()
            .register(ObjectMapperProvider::class.java)
            .packages("endpoints")
    ).use { server: Server -> server.join() }
  }
}

inline fun Server.use(block: (Server) -> Unit) = try {
  block(this)
} finally {
  this.destroy()
}