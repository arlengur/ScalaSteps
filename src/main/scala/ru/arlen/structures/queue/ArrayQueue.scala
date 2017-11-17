package ru.arlen.structures.queue

import scala.reflect.ClassTag

/**
  * @author satovritti
  */
class ArrayQueue[A: ClassTag] extends Queue[A] {
  private var data = new Array[A](10)
  var front = 0
  var back = 0

  override def enqueue(a: A): Unit = {
    if ((back + 1) % data.length == front) {
      val tmp = new Array[A](data.length * 2)
      for (i <- 0 until data.length - 1)
        tmp(i) = data((front + i) % data.length)
      front = 0
      back = data.length - 1
      data = tmp
    }
    data(back) = a
    back = (back + 1) % data.length
  }

  override def dequeue(): A = {
    var ret = data(front)
    front = (front + 1) % data.length
    ret
  }

  override def peek: A = data(front)

  override def isEmpty: Boolean = front == back
}
