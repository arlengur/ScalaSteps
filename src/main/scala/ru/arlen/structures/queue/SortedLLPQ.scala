package ru.arlen.structures.queue

/**
  * @author satovritti
  */
class SortedLLPQ[A](higherP: (A, A) => Boolean) extends Queue[A] {
  private var default: A = _

  private class Node(val data: A, var prev: Node, var next: Node)

  private val end = new Node(default, null, null)
  end.prev = end
  end.next = end

  override def enqueue(a: A): Unit = {
    var rover = end.prev
    while (rover != end && higherP(a, rover.data)) rover = rover.prev
    rover.next.prev = new Node(a, rover, rover.next)
    rover.next = rover.next.prev
  }

  override def dequeue(): A = {
    val ret = end.next.data
    end.next.next.prev = end
    end.next = end.next.next
    ret
  }

  override def peek: A = end.next.data

  override def isEmpty: Boolean = end.next == end
}