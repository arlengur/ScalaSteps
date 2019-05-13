package ru.arlen.typeclass

final class Ratio2 private(val num: Int, val den: Int) {
    def ===(that: Ratio2): Boolean =
      this.num == that.num && this.den == that.den
}

object Ratio2 {
  def apply(num: Int, den: Int): Ratio2 = {
    val gcd = BigInt(num).gcd(den).toInt
    new Ratio2(num / gcd, den / gcd)
  }
}
