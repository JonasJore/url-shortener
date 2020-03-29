package app.jetty

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

@Provider
class ObjectMapperProvider : ContextResolver<ObjectMapper> {
  override fun getContext(p0: Class<*>?): ObjectMapper? =
      ObjectMapper()
          .enable(SerializationFeature.INDENT_OUTPUT)
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
}