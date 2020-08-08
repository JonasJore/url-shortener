package jetty

import app.jetty.ObjectMapperProvider
import org.eclipse.jetty.server.Server
import org.glassfish.jersey.jetty.JettyHttpContainerFactory
import org.glassfish.jersey.server.ResourceConfig
import org.slf4j.LoggerFactory
import javax.ws.rs.core.UriBuilder

val logger = LoggerFactory.getLogger(JettyServer::class.java)

class JettyServer {
  val logger = LoggerFactory.getLogger(JettyServer::class.java)

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

inline fun Server.use(jettyInstance: (Server) -> Unit) = try {
  jettyInstance(this)
} finally {
  this.destroy()
}