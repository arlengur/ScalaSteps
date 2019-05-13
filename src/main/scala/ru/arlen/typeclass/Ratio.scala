package ru.arlen.typeclass

import ru.arlen.typeclass.Ord.Compare.{EQ, GT, LT}

final case class Ratio(num: Int, den: Int)

object Ratio {
  // инстанс тайпкласса
  implicit val eqRatio: Eq[Ratio] = (x: Ratio, y: Ratio) =>
    x.num.toLong * y.den == x.den.toLong * y.num
  implicit val ordRatio: Ord[Ratio] = (x: Ratio, y: Ratio) =>
    (x.num.toLong * y.den, x.den.toLong * y.num) match {
      case (a, b) if a == b => EQ
      case (a, b) if a > b => GT
      case (a, b) if a < b => LT
    }
  implicit val sgRatio: SemiGroup[Ratio] = (x: Ratio, y: Ratio) => sum(x, y)

  def sum(x: Ratio, y: Ratio) = Ratio(x.num * y.den + x.den * y.num, x.den * y.den)

  def multiply(x: Ratio, y: Ratio) = Ratio(x.num * y.num, x.den * y.den)

  implicit val sumSgRatio: SemiGroup[Sum[Ratio]] = (x: Sum[Ratio], y: Sum[Ratio]) => Sum(sum(x.x, y.x))
  implicit val prodSgRatio: SemiGroup[Prod[Ratio]] = (x: Prod[Ratio], y: Prod[Ratio]) => Prod(multiply(x.x, y.x))
}