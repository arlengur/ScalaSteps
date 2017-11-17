package ru.arlen.structures.stack

/**
  * @author satovritti
  */
trait Stack[A] {
  def push(a: A): Unit

  def pop(): A

  def peek: A

  def isEmpty: Boolean

}
