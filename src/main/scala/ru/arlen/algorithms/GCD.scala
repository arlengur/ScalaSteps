package ru.arlen.algorithms

/**
  * @author satovritti
  */
object GCD extends App {
  /**
    * Greatest common divisor
    */
  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
