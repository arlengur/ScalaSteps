package ru.arlen.typeclass

import ru.arlen.typeclass.Ord.Compare

trait Ord[T] {
  def compare(x: T, y: T): Compare
}

object Ord {
  sealed trait Compare
  object Compare {
    case object LT extends Compare
    case object EQ extends Compare
    case object GT extends Compare
  }
}