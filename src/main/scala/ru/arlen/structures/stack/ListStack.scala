package ru.arlen.structures.stack

/**
  * @author satovritti
  */
class ListStack[A] extends Stack[A] {

  private case class Node(data: A, next: Node)

  private var top: Node = null

  override def push(a: A): Unit = {
    top = new Node(a, top)
  }

  override def pop(): A = {
    val ret = top.data
    top = top.next
    ret
  }

  override def peek: A = top.data

  override def isEmpty: Boolean = top == null
}
