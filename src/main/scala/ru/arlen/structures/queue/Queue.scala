package ru.arlen.structures.queue

/**
  * @author satovritti
  */
trait Queue[A] {
  def enqueue(a: A): Unit

  def dequeue(): A

  def peek: A

  def isEmpty: Boolean

}
