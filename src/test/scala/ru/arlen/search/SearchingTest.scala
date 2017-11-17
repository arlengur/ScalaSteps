package ru.arlen.search

import org.junit.Assert._
import org.junit.{Before, Test}
/**
  * @author satovritti
  */
class SearchingTest {
  var searching: Searching = _

  @Before def makeSearching(): Unit = {
    searching = new Searching
  }

  @Test def indexOfTest(): Unit = {
    val a = Array(2, 3, 7, 8, 3, 5)
    assertEquals(1, searching.indexOf(a, 3))
    assertEquals(-1, searching.indexOf(a, 4))
  }

  @Test def findTest(): Unit = {
    val a = Array(2, 8, 3, 7, 3, 5)
    assertEquals(Some(8), searching.find(a, _ % 4 == 0))
    assertEquals(None, searching.find(a, _ % 9 == 0))
  }

  @Test def binarySearchTest(): Unit = {
    val a = Array(1, 2, 3, 4, 5, 6)
    assertEquals(3, searching.binarySearch(a, 4))
    assertEquals(-1, searching.binarySearch(a, 9))
  }

  @Test def binarySearchRecTest(): Unit = {
    val a = Array(1, 2, 3, 4, 5, 6)
    assertEquals(3, searching.binarySearchRec(a, 4))
    assertEquals(-1, searching.binarySearchRec(a, 9))
  }

}
