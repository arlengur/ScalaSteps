package ru.arlen.structures.linkedlist

import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

/**
  * @author satovritti
  */
class MutableDLListTest {
  var lst: MutableDLList[Int] = null

  @Before def makeList = {
    lst = new MutableDLList[Int]()
  }

  @Test def addOne: Unit = {
    lst += 93
    assertEquals(93, lst(0))
  }

  @Test def addTwoAtFront: Unit = {
    93 +=: lst
    83 +=: lst
    assertEquals(83, lst(0))
    assertEquals(93, lst(1))
  }

  @Test def addTwoToEnd: Unit = {
    lst += 93
    lst += 83
    assertEquals(93, lst(0))
    assertEquals(83, lst(1))
  }

  @Test def clear: Unit = {
    lst += 93
    lst += 83
    lst.clear()
    assertEquals(0, lst.length)
  }

  @Test def insertAll: Unit = {
    lst += 93
    lst += 83
    lst.insertAll(1, List(6,7,8))
    assertEquals(List(93,6,7,8,83), lst)

  }

}
