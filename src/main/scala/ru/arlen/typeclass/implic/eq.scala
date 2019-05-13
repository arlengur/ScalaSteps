package ru.arlen.typeclass.implic

import ru.arlen.typeclass.Eq

object eq {
  // позволяет удобно использовать тайпкласс
  implicit class EqOperations[T](val x: T) extends AnyVal {
    def ===(y: T)(implicit eq: Eq[T]): Boolean = eq === (x,y)
  }
}
