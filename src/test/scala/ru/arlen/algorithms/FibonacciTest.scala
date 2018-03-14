package ru.arlen.algorithms

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.arlen.algorithms.Fibonacci._

/**
  * @author satovritti
  */
class FibonacciTest {
  @Test def testFibonacci(): Unit = {
    assertEquals("Wrong result", BigInt(0), fibonacci(0))
    assertEquals("Wrong result", BigInt(1), fibonacci(1))
    assertEquals("Wrong result", BigInt(1), fibonacci(2))
    assertEquals("Wrong result", BigInt(2), fibonacci(3))
    assertEquals("Wrong result", BigInt(3), fibonacci(4))
    assertEquals("Wrong result", BigInt(5), fibonacci(5))
    assertEquals("Wrong result", BigInt(55), fibonacci(10))
    assertEquals("Wrong result", BigInt(28657), fibonacci(23))
    assertEquals("Wrong result", BigInt("354224848179261915075"), fibonacci(100))
  }

  @Test def testFibonacciLastNum(): Unit = {
    assertEquals("Wrong result", 0, fibonacciLastNum(0))
    assertEquals("Wrong result", 1, fibonacciLastNum(1))
    assertEquals("Wrong result", 1, fibonacciLastNum(2))
    assertEquals("Wrong result", 2, fibonacciLastNum(3))
    assertEquals("Wrong result", 3, fibonacciLastNum(4))
    assertEquals("Wrong result", 5, fibonacciLastNum(5))
    assertEquals("Wrong result", 5, fibonacciLastNum(10))
    assertEquals("Wrong result", 7, fibonacciLastNum(23))
    assertEquals("Wrong result", 5, fibonacciLastNum(100))
  }

  @Test def testPisanoPeriod(): Unit = {
    assertEquals("Wrong result", 0, pisanoPeriod(0))
    assertEquals("Wrong result", 1, pisanoPeriod(1))
    assertEquals("Wrong result", 3, pisanoPeriod(2))
    assertEquals("Wrong result", 8, pisanoPeriod(3))
    assertEquals("Wrong result", 6, pisanoPeriod(4))
    assertEquals("Wrong result", 20, pisanoPeriod(5))
    assertEquals("Wrong result", 60, pisanoPeriod(10))
    assertEquals("Wrong result", 48, pisanoPeriod(23))
    assertEquals("Wrong result", 300, pisanoPeriod(100))
  }

  @Test def testFibonacciHuge(): Unit = {
    assertEquals("Wrong result", 1, fibonacciHuge(10, 2))
    assertEquals("Wrong result", 675, fibonacciHuge(100, 3472))
    assertEquals("Wrong result", 3, fibonacciHuge(100, 32))
  }
}
