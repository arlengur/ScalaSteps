package ru.arlen.structures.linkedlist

import scala.collection.mutable

/**
  * @author satovritti
  */
class MutableDLList[A] extends mutable.Buffer[A] {

  private class Node(var data: A, var prev: Node, var next: Node)

  private var default: A = _
  private val end = new Node(default, null, null)
  end.prev = end
  end.next = end
  private var numElems = 0

  override def apply(n: Int): A = {
    require(n >= 0 && n < numElems)
    var rover = end.next
    for (_ <- 1 to n) rover = rover.next
    rover.data
  }

  override def update(n: Int, newelem: A): Unit = {
    require(n >= 0 && n < numElems)
    var rover = end.next
    for (_ <- 1 to n) rover = rover.next
    rover.data = newelem
  }

  override def length: Int = numElems

  override def +=(elem: A): MutableDLList.this.type = {
    val newNode = new Node(elem, end.prev, end)
    end.prev.next = newNode
    end.prev = newNode
    numElems += 1
    this
  }

  override def clear(): Unit = {
    end.prev = end
    end.next = end
    numElems = 0
  }

  override def +=:(elem: A): MutableDLList.this.type = {
    val newNode = new Node(elem, end, end.next)
    end.next.prev = newNode
    end.next = newNode
    numElems += 1
    this
  }

  override def insertAll(n: Int, elems: Traversable[A]): Unit = {
    require(n >= 0 && n < numElems)
    if (elems.nonEmpty) {
      var rover = end.next
      for (_ <- 1 to n) rover = rover.next
      for (e <- elems) {
        val newElem = new Node(e, rover.prev, rover)
        rover.prev.next = newElem
        rover.prev = newElem
      }
      numElems += elems.size
    }
  }

  override def remove(n: Int): A = {
    require(n >= 0 && n < numElems)
    var rover = end.next
    for (_ <- 1 to n) rover = rover.next
    val ret = rover.data
    rover.prev.next = rover.next
    rover.next.prev = rover.prev
    numElems -= 1
    ret
  }

  override def iterator: Iterator[A] = new Iterator[A] {
    var rover = end.next

    override def hasNext: Boolean = rover != end

    override def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
  }
}
