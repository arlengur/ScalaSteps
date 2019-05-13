package ru.arlen.typeclass.implic

import ru.arlen.typeclass.SemiGroup

object semigroup {

  implicit class SemiGroupOps[T](val x: T) extends AnyVal {
    def combine(y: T)(implicit sg: SemiGroup[T]): T = sg.combine(x, y)

    def |+|(y: T)(implicit sg: SemiGroup[T]): T = combine(y)
  }

}
