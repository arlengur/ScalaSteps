package ru.arlen.scalacheck

import org.scalacheck.Prop._
import org.scalacheck.{Properties, _}

object TestCheck extends Properties("") {
  property("concat") = forAll { (a: List[Int], b: List[Int]) => a.size + b.size == (a ::: b).size }

  property("abs") = forAll { (x: Int) =>
    x != Int.MinValue && x != Int.MaxValue && x.abs >= 0
  }

  property("str length") = forAll { s: String =>
    val len = s.length
    (s + s).length == len + len
  }

  property("index out") = forAll { xs: List[Int] => Prop.throws(classOf[IndexOutOfBoundsException])(xs(xs.length + 1)) }
}

object Testgen extends App {
  val binaryDigit = Gen.choose(0, 1)
  println(binaryDigit.sample)
  val octDigit    = Gen.choose(0, 7)
  println(octDigit.sample)
  val vowel = Gen.oneOf('a', 'e', 'i', 'o', 'u')
  println(vowel.sample)
  println(Gen.alphaLowerChar.sample)
  println(Arbitrary.arbitrary[String].sample map (_ take 10))
  println(Arbitrary.arbitrary[Double].sample)

}
