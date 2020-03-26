package app.Jetty

import org.apache.log4j.Logger
import org.eclipse.jetty.server.Server
import org.glassfish.jersey.jetty.JettyHttpContainerFactory
import org.glassfish.jersey.server.ResourceConfig
import javax.ws.rs.core.UriBuilder

class JettyServer {
  private val logger: Logger = Logger.getLogger(JettyServer::class.java)

  fun startServer() {
    logger.info("Firing up the server")
    JettyHttpContainerFactory.createServer(
        UriBuilder
            .fromUri("http://localhost/")
            .port(8080)
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