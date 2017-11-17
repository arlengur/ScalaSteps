package ru.arlen.sort

import scala.annotation.tailrec

/**
  * @author satovritti
  */
class Sorting {
  /**
    * Bubble sort
    * O(n*2)
    */
  def bubbleSort(a: Array[Int]): Unit = {
    for (i <- 0 until a.length - 1)
      for (j <- 0 until a.length - 1 - i)
        if (a(j) > a(j + 1)) {
          val tmp = a(j)
          a(j) = a(j + 1)
          a(j + 1) = tmp
        }
  }

  /**
    * Selection sort
    * O(n*2)
    */
  def minSort(a: Array[Int]): Unit = {
    for (i <- 0 until a.length - 1) {
      var min = i
      for (j <- i + 1 until a.length)
        if (a(j) < a(min)) min = j
      val tmp = a(i)
      a(i) = a(min)
      a(min) = tmp
    }
  }

  /**
    * Insertion sort
    * O(n*2)
    */
  def insertionSort(a: Array[Int]): Unit = {
    for (i <- 1 until a.length) {
      var j = i - 1
      val tmp = a(i)
      while (j >= 0 && tmp < a(j)) {
        a(j + 1) = a(j)
        j -= 1
      }
      a(j + 1) = tmp
    }
  }

  /**
    * Bucket sort
    */
  def bucketSort(a: Array[Int]): Unit = {
    val min = a.min
    val max = a.max
    val buckets = Array.fill(a.length)(List[Int]())
    for (x <- a) {
      val idx = (x - min) * (buckets.length - 1) / (max - min)
      buckets(idx) ::= x
    }
    var i = 0
    for (bucket <- buckets; x <- bucket) {
      a(i) = x
      i += 1
    }
    insertionSort(a)
  }

  /**
    * Shell sort
    */
  def shellSort(a: Array[Int]): Unit = {
    var gap = a.length / 2
    while (gap >= 1) {
      for (i <- gap until a.length) {
        var j = i - gap
        val tmp = a(i)
        while (j >= 0 && tmp < a(j)) {
          a(j + gap) = a(j)
          j -= gap
        }
        a(j + gap) = tmp
      }
      gap = (gap / 2.2).round.toInt
    }
  }

  def insertionSortList(l: List[Int]): List[Int] = {
    def insert(x: Int, sorted: List[Int]): List[Int] = sorted match {
      case Nil => x :: Nil
      case h :: t => if (x < h) x :: sorted else h :: insert(x, t)
    }

    def helper(sorted: List[Int], unsorted: List[Int]): List[Int] = unsorted match {
      case Nil => sorted
      case h :: t => helper(insert(h, sorted), t)
    }

    helper(Nil, l)
  }

  /**
    * Merge sort
    * O(n log n)
    */
  def mergeSort(lst: List[Int]): List[Int] = lst match {
    case Nil => lst
    case h :: Nil => lst
    case _ =>
      val (l1, l2) = lst.splitAt(lst.length / 2)
      merge(mergeSort(l1), mergeSort(l2), Nil)
  }

  @tailrec
  private def merge(l1: List[Int], l2: List[Int], lst: List[Int]): List[Int] = (l1, l2) match {
    case (Nil, l2) => lst.reverse ::: l2
    case (l1, Nil) => lst.reverse ::: l1
    case (h1 :: t1, h2 :: t2) =>
      if (h1 < h2) merge(t1, l2, h1 :: lst) else merge(l1, t2, h2 :: lst)
  }

//  def quickSort(lst: List[Int]): List[Int] = lst match {
//    case Nil => lst
//    case h :: Nil => lst
//    case pivot :: t =>
//      val (less, greater) = lst.partition(_ < pivot)
//      quickSort(less) ::: pivot :: quickSort(greater)
//  }

  /**
    * Quick sort
    * O(n log n)
    */
  def quickSort(a: Array[Int]): Array[Int] = {
    if (a.length < 2) a
    else {
      val pivot = a(0)
      quickSort(a filter (pivot > _)) ++ (a filter (pivot == _)) ++ quickSort(a filter (pivot < _))
    }
  }

  def isSorted(a: Array[Int]): Boolean = {
    //    a.zip(a.tail).forall(t => t._1 <= t._2)
    (a, a.tail).zipped.forall(_ <= _)
  }

  def isSortedList(a: List[Int]): Boolean = {
    //    a.zip(a.tail).forall(t => t._1 <= t._2)
    (a, a.tail).zipped.forall(_ <= _)
  }

  def timeFunc(sortFunc: Array[Int] => Unit, a: Array[Int]): Double = {
    val start = System.nanoTime()
    sortFunc(a.map(a => a))
    val end = System.nanoTime()
    (end - start) / 1e9
  }

  def timeFuncList(sortFunc: List[Int] => List[Int], a: List[Int]): Double = {
    val start = System.nanoTime()
    sortFunc(a.map(a => a))
    val end = System.nanoTime()
    (end - start) / 1e9
  }
}
