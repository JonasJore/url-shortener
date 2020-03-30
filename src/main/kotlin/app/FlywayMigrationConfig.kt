package app

import exceptions.FlywayMigrationException
import org.apache.log4j.Logger
import org.flywaydb.core.Flyway

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