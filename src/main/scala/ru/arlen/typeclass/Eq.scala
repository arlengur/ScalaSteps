package ru.arlen.typeclass

import implic.eq._

trait Eq[T] {
  def ===(x: T, y: T): Boolean
}

object Eq {
  def compareList[T](first: Seq[T], second: Seq[T])(implicit eq: Eq[T]): Boolean =
    first.size == second.size && first.zip(second).forall {
      case (x, y) => x === y
    }

  // инстанс тайпкласса
  implicit def compare[T](implicit eq: Eq[T]): Eq[Seq[T]] = (first: Seq[T], second: Seq[T]) =>
    first.size == second.size && first.zip(second).forall {
      case (x, y) => x === y
    }

  // законы сравнения
  object laws {
    def reflectivity[T: Eq](x: T): Boolean = x === x

    def symmetry[T: Eq](x: T, y: T): Boolean = (x === y) == (y === x)

    def transitivity[T: Eq](x: T, y: T, z: T): Boolean = !((x === y) && (y === z)) || (x === z)
  }

}