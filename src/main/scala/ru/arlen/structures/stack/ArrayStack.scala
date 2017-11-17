package ru.arlen.structures.stack

import scala.reflect.ClassTag

/**
  * @author satovritti
  */
class ArrayStack[A: ClassTag] extends Stack[A] {
  private var data = new Array[A](10)
  private var top = 0

  override def push(a: A): Unit = {
    if (top >= data.length) {
      val tmp = new Array[A](data.length * 2)
      Array.copy(data, 0, tmp, 0, data.length)
      data = tmp
    }
    data(top) = a
    top += 1
  }

  override def pop(): A = {
    top -= 1
    data(top)
  }

  override def peek: A = data(top - 1)

  override def isEmpty: Boolean = top == 0
}
