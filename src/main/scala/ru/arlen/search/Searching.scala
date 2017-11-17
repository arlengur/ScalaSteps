package ru.arlen.search

/**
  * @author satovritti
  */
class Searching {
  /**
    * Sequential search
    * O(n)
    */
  def indexOf(a: Array[Int], c: Int): Int = {
    var i = 0
    while (i < a.length && a(i) != c) i += 1
    if (i < a.length) i else -1
  }

  def find(a: Array[Int], p: Int => Boolean): Option[Int] = {
    var i = 0
    while (i < a.length && !p(a(i))) i += 1
    if (i < a.length) Some(a(i)) else None
  }

  /**
    * Binary search
    * O(log(n))
    */
  def binarySearch(a: Array[Int], c: Int): Int = {
    var start = 0
    var end = a.length
    var mid = (start + end) / 2
    while (start < end && a(mid) != c) {
      if (a(mid) > c) end = mid else start = mid + 1
      mid = (start + end) / 2
    }
    if (start < end) mid else -1
  }

  /**
    * Binary search recursion
    * O(log(n))
    */
  def binarySearchRec(a: Array[Int], c: Int): Int = {
    def binarySearchEx(a: Array[Int], c: Int, start: Int, end: Int): Int = {
      if (start >= end) -1 else {
        val mid = (start + end) / 2
        if (a(mid) == c) mid
        else if (a(mid) > c) binarySearchEx(a, c, start, mid)
        else binarySearchEx(a, c, mid + 1, end)
      }
    }
    binarySearchEx(a, c, 0, a.length)
  }

}
