package app
// TODO: put this in a properties file.
// Database:
const val schemaName = "url_shortener_db"
const val port = "3306"
const val connectionSource = "jdbc:mysql://db:$port/$schemaName"
const val userName = "root"
const val password = "root"