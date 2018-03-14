package ru.arlen.algorithms

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.arlen.algorithms.GCD._

/**
  * @author satovritti
  */
class GCDTest {
  @Test def testFibonacciHuge(): Unit = {
    assertEquals("Wrong result", 1, gcd(18, 35))
    assertEquals("Wrong result", 4, gcd(14159572, 63967072))
  }

}
