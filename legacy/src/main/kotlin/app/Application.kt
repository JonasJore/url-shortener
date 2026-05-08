package app

class Application {
  companion object {
    @JvmStatic
    fun start() {
      FlywayMigrationConfig().flywayMigrate()
      JettyServer().startServer()
    }
  }
}
