package app

import org.jdbi.v3.core.Jdbi

fun jdbi(): Jdbi =
   Jdbi.create(connectionSource, userName, password)