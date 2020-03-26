package app

import app.Jetty.JettyServer

class Application {
  companion object {
    @JvmStatic
    fun start() {
      FlywayMigrationConfig().flywayMigrate()
      JettyServer().startServer()
    }
  }
}