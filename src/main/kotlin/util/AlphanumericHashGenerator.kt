package util

import kotlin.random.Random

const val STRING_LENGTH: Int = 10

class AlphanumericHashGenerator {
  private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

  fun generateHash(): String = (1..STRING_LENGTH)
      .map { Random.nextInt(0, charPool.size) }
      .map { index: Int -> charPool[index] }
      .joinToString("")
}