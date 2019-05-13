package ru.arlen.typeclass.implic

import ru.arlen.typeclass.Ord
import ru.arlen.typeclass.Ord.Compare

object ord {
  // позволяет удобно использовать тайпкласс
  implicit class OrdOperations[T](val x: T) extends AnyVal {
    def compare(y: T)(implicit ord: Ord[T]): Compare = ord compare (x,y)
  }
}
