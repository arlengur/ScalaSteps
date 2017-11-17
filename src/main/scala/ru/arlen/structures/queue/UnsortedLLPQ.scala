package ru.arlen.structures.queue

/**
  * @author satovritti
  */
class UnsortedLLPQ[A](higherP: (A, A) => Boolean) extends Queue[A] {
  private var default: A = _

  private class Node(val data: A, var prev: Node, var next: Node)

  private val end = new Node(default, null, null)
  end.prev = end
  end.next = end

  override def enqueue(a: A): Unit = {
    val newNode = new Node(a, end.prev, end)
    end.prev.next = newNode
    end.prev = newNode
  }

  override def dequeue(): A = {
    var hpn = findHighestPriorityNode()
    hpn.prev.next = hpn.next
    hpn.next.prev = hpn.prev
    hpn.data
  }

  override def peek: A = findHighestPriorityNode().data

  override def isEmpty: Boolean = end.next == end

  private def findHighestPriorityNode(): Node = {
    var ret = end.next
    var rover = ret.next
    while (rover != end) {
      if (higherP(rover.data, ret.data)) ret = rover
      rover = rover.next
    }
    ret
  }
}
