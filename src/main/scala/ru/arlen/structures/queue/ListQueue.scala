package ru.arlen.structures.queue

/**
  * @author satovritti
  */
class ListQueue[A] extends Queue[A] {

  private class Node(val data: A, var next: Node)

  private var front: Node = null
  private var back: Node = null

  override def enqueue(a: A): Unit = {
    if (front == null) {
      front = new Node(a, null)
      back = front
    } else {
      back.next = new Node(a, null)
      back = back.next
    }
  }

  override def dequeue(): A = {
    val ret = front.data
    front = front.next
    if (front == null) back = null
    ret
  }

  override def peek: A = front.data

  override def isEmpty: Boolean = front == null
}
