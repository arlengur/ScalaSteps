package ru.arlen.algorithms

import scala.annotation.tailrec

/**
  * @author satovritti
  */
object Fibonacci extends App {
  def fibonacci(n: Int): BigInt = {
    @tailrec
    def fibHelper(x: Int, prev: BigInt = 0, next: BigInt = 1): BigInt = x match {
      case 0 => prev
      case _ => fibHelper(x - 1, next, prev + next)
    }
    fibHelper(n)
  }

  def fibonacciLastNum(n: Int): Int = {
    @tailrec
    def fibHelper(x: Int, prev: Int = 0, next: Int = 1): Int = x match {
      case 0 => prev
      case _ => fibHelper(x - 1, next, (prev + next) % 10)
    }
    fibHelper(n)
  }

  def pisanoPeriod(m: Long): Long = {
    if (m == 1) return 1
    @tailrec
    def pisanoHelper(x: Long, iter: Int = 0, prev: Long = 0, next: Long = 1): Long = x match {
      case 0 => if (m == 0) 0 else prev % m
      case _ =>
        if (next == 0 && ((prev + next) % m == 1)) iter + 1
        else pisanoHelper(x - 1, iter + 1, next, (prev + next) % m)
    }
    pisanoHelper(m * m)
  }

  /**
    * Returns F(n) mod m
    *
    * @param n -th Fibonacci number
    * @param m divisor
    * @return remainder of dividing F(n) by m
    */
  def fibonacciHuge(n: Long, m: Long): Long = {
    val remainder = n % pisanoPeriod(m)

    @tailrec
    def hugeHelper(x: Long, prev: Long = 0, next: Long = 1): Long = x match {
      case 0 => prev % m
      case _ => hugeHelper(x - 1, next, (prev + next) % m)
    }
    hugeHelper(remainder)
  }


}
