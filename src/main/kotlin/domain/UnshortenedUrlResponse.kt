package domain

import com.fasterxml.jackson.annotation.JsonCreator

data class UnshortenedUrlResponse(
    val unshortenedUrl: String
){
  companion object {
    @JsonCreator
    @JvmStatic
    private fun creator(): UnshortenedUrlResponse {
      return UnshortenedUrlResponse(unshortenedUrl = "")
    }
  }
}