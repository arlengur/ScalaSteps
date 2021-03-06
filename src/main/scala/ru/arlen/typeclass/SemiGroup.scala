package ru.arlen.typeclass

import implic.semigroup._

// бинарная операция соединяющая 2 элемента в один
trait SemiGroup[T] {
  def combine(x: T, y: T): T
}

object SemiGroup {

  object laws {
    def associativity[T](x: T, y: T, z: T)(implicit sg: SemiGroup[T]): Boolean = {
      //      import sg.combine
      //      combine(combine(x, y), z) == combine(x, combine(y, z))
      ((x |+| y) |+| z) == (x |+| (y |+| z))
    }
  }

  implicit val strSemiGroup: SemiGroup[String] = (x: String, y: String) => x + y

  def combineList[T: SemiGroup](list: List[T]): Option[T] =
    list.reduceOption((r1, r2) => r1 |+| r2)

  //  def combineListVia[U: SemiGroup, T](list: List[T])(implicit iso: Iso[T, U]): Option[T] =
  //    list.reduceOption((x, y) => iso.unwrap(iso.wrap(x) |+| iso.wrap(y)))
  //  def combineListVia[U[_], T](list: List[T])(implicit iso: Iso[T, U[T]]): Option[T] =
  //    list.reduceOption((x, y) => iso.unwrap(iso.wrap(x) |+| iso.wrap(y)))
  def combineListVia[U[_]] = new CombineListVia[U]

  class CombineListVia[U[_]] {
    def apply[T](list: List[T])(implicit iso: Iso[T, U[T]], sg: SemiGroup[U[T]]): Option[T] =
      list.reduceOption((x, y) => iso.unwrap(iso.wrap(x) |+| iso.wrap(y)))
  }

}

final case class Sum[T](x: T) extends AnyVal

final case class Prod[T](x: T) extends AnyVal

object Sum {
  implicit def sumIso[T]: Iso[T, Sum[T]] = new Iso[T, Sum[T]] {
    override def wrap(x: T): Sum[T] = Sum(x)

    override def unwrap(x: Sum[T]): T = x.x
  }

  implicit val sumRatio: SemiGroup[Sum[Int]] = (x: Sum[Int], y: Sum[Int]) => Sum(x.x + y.x)
}

object Prod {
  implicit def prodIso[T]: Iso[T, Prod[T]] = new Iso[T, Prod[T]] {
    override def wrap(x: T): Prod[T] = Prod(x)

    override def unwrap(x: Prod[T]): T = x.x
  }

  implicit val prodRatio: SemiGroup[Prod[Int]] = (x: Prod[Int], y: Prod[Int]) => Prod(x.x + y.x)
}