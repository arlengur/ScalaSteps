package ru.arlen.sort

import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * @author satovritti
  */
class SortingTest {
  var sorting: Sorting = _

  @Before def makeSorting() {
    sorting = new Sorting()
  }

  @Test def bubbleSort(): Unit = {
    val array = Array.fill(10)(util.Random.nextInt(20))
    //    println(array.mkString("[", ", ", "]"))
    sorting.bubbleSort(array)
    //    println(array.mkString("[", ", ", "]"))
    assertTrue(sorting.isSorted(array))
  }

  @Test def selectionSort(): Unit = {
    val array = Array.fill(10)(util.Random.nextInt(20))
    sorting.minSort(array)
    assertTrue(sorting.isSorted(array))
  }

  @Test def insertionSort(): Unit = {
    val array = Array.fill(10)(util.Random.nextInt(20))
    sorting.minSort(array)
    assertTrue(sorting.isSorted(array))
  }

  @Test def bucketSort(): Unit = {
    val array = Array.fill(10)(util.Random.nextInt(20))
    sorting.bucketSort(array)
    assertTrue(sorting.isSorted(array))
  }

  @Test def insertionSortList(): Unit = {
    val array = List.fill(10)(util.Random.nextInt(20))
    assertTrue(sorting.isSortedList(sorting.insertionSortList(array)))
  }

  @Test def shellSort(): Unit = {
    val array = Array.fill(10)(util.Random.nextInt(20))
    sorting.shellSort(array)
    assertTrue(sorting.isSorted(array))
  }

  @Test def mergeSort(): Unit = {
    val lst = List.fill(10)(util.Random.nextInt(20))
    assertTrue(sorting.isSortedList(sorting.mergeSort(lst)))
  }

  @Test def printPerfomanceSortTest(): Unit = {
    val array = Array.fill(1000000)(util.Random.nextInt(30000))
    val lst = List.fill(1000000)(util.Random.nextInt(30000))
//    println("Bubble sort " + sorting.timeFunc(sorting.bubbleSort, array))
//    println("Selection sort " + sorting.timeFunc(sorting.minSort, array))
//    println("Insertion sort " + sorting.timeFunc(sorting.insertionSort, array))
//    println("Bucket sort " + sorting.timeFunc(sorting.bucketSort, array))
    println("Shell sort " + sorting.timeFunc(sorting.shellSort, array))
    println("Merge sort " + sorting.timeFuncList(sorting.mergeSort, lst))
    println("Quick sort " + sorting.timeFunc(sorting.quickSort, array))
//    println("Quick sort " + sorting.timeFuncList(sorting.quickSort, lst))
  }
}
