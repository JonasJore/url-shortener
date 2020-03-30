package app

import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.Jdbi

class JdbiConfig {
  fun openConnection(): Handle =
     Jdbi.create(connectionSource, userName, password).open()
}