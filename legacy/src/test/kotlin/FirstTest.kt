package src

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FirstTest {
  fun sayHello(): String = "Hello"

  @Test
  fun isHelloSayingHello() {
    val hello: String = sayHello()
    assertThat(hello).isEqualTo("Hello")
  }
}