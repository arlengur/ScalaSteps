package ru.arlen.structures.linkedlist

/**
  * @author satovritti
  */
class SinglyLinkedList[A] extends ListADT[A] {

  private class Node(var data: A, var next: Node)

  private var head: Node = _

  override def apply(index: Int): A = {
    require(index >= 0)
    var rover = head
    for (_ <- 0 until index) rover = rover.next
    rover.data
  }

  override def update(index: Int, data: A): Unit = {
    require(index >= 0)
    var rover = head
    for (_ <- 0 until index) rover = rover.next
    rover.data = data
  }

  override def insert(index: Int, data: A): Unit = {
    require(index >= 0)
    if (index == 0){
      head = new Node(data, head)
    } else {
      var rover = head
      for (_ <- 0 until index - 1) rover = rover.next
      rover.next = new Node(data, rover.next)
    }
  }

  override def remove(index: Int): A = {
    require(index >= 0)
    if (index == 0){
      val ret = head
      head = head.next
      ret.data
    } else {
      var rover = head
      for (_ <- 0 until index - 1) rover = rover.next
      val ret = rover.next.data
      rover.next = rover.next.next
      ret
    }
  }
}
