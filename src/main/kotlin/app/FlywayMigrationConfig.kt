package app

import org.apache.log4j.Logger
import org.flywaydb.core.Flyway

private val logger: Logger = Logger.getLogger(FlywayMigrationConfig::class.java)

// TODO: put this in a properties file.
private const val schemaName = "url_shortener_db"
private const val port = "3306"
private const val connectionSource = "jdbc:mysql://localhost:$port/$schemaName"
private const val userName = "root"

class FlywayMigrationConfig {
  fun flywayMigrate() {
    logger.info("Performing migration")
    Flyway(Flyway.configure()
        .dataSource(connectionSource, userName, "")
    ).migrate()
  }
}