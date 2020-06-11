package app

import jetty.JettyServer

class Application {
  companion object {
    @JvmStatic
    fun start() {
      FlywayMigrationConfig().flywayMigrate()
      JettyServer().startServer()
    }
  }
}