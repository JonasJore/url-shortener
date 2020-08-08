package app

import exceptions.FlywayMigrationException
import org.flywaydb.core.Flyway


class FlywayMigrationConfig {
  fun flywayMigrate() {
    try {
      Flyway(Flyway.configure()
          .dataSource(connectionSource, userName, password)
      ).migrate()
    } catch (ex: Exception) {
      throw FlywayMigrationException("Migration failed, $ex")
    }
  }
}