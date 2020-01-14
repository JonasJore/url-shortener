package server

import org.eclipse.jetty.server.Connector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector

class JettyServer {
  fun startServer() {
    val server = Server()
    val connector = ServerConnector(server)
    connector.port = 8090
    
  }
}