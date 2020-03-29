package app

import exceptions.FlywayMigrationException
import org.apache.log4j.Logger
import org.flywaydb.core.Flyway

// TODO: put this in a properties file.
private const val schemaName = "url_shortener_db"
private const val port = "3306"
private const val connectionSource = "jdbc:mysql://localhost:$port/$schemaName"
private const val userName = "root"

private val logger: Logger = Logger.getLogger(FlywayMigrationConfig::class.java)

class FlywayMigrationConfig {
  fun flywayMigrate() {
    logger.info("Performing migration")
    try {
      Flyway(Flyway.configure()
          .dataSource(connectionSource, userName, "root")
      ).migrate()
    } catch (ex: Exception) {
      throw FlywayMigrationException("Migration failed, $ex")
    }
  }
}