package service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private const val MOVED_PERMANENTLY = 301

class RedirectionServiceTest {
  @Test
  fun `hasprotocol returns false when uri does not have a protocol`() {
    val uri = "nettsideutenprotokoll.io"
    assertThat(RedirectionService().hasProtocol(uri)).isFalse()
  }

  @Test
  fun `hasProtocol returns true when uri does have a protocol`() {
    val uri = "http://www.nettsidemedprotokoll.io"
    assertThat(RedirectionService().hasProtocol(uri)).isTrue()
  }

  @Test
  fun `adds protocol to given url when redirecting`() {
    val uri = "nettside.no"
    assertThat(RedirectionService().redirectToUrl(uri).status).isEqualTo(MOVED_PERMANENTLY)
  }

  @Test
  fun `does not add protocol to urls that already have a protocol`() {
    val uri = "https://www.denneurlharprotokollladenvareifred.no"
    assertThat(RedirectionService().redirectToUrl(uri).status).isEqualTo(MOVED_PERMANENTLY)
  }
}