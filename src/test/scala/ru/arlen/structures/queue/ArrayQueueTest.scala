package ru.arlen.structures.queue

import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * @author satovritti
  */
class ArrayQueueTest {
  private var queue: Queue[Int] = _

  @Before def makeQueue(): Unit = {
    queue = new ArrayQueue[Int]()
  }

  @Test def emptyOnCreate(): Unit = {
    assertTrue(queue.isEmpty)
  }

  @Test def notEmptyOnEnqueue(): Unit = {
    queue.enqueue(1)
    assertFalse(queue.isEmpty)
  }

  @Test def enqueueDequeue(): Unit = {
    queue.enqueue(1)
    assertEquals(1, queue.peek)
    assertEquals(1, queue.dequeue())
  }

  @Test def enqueueDequeueEnqueueDequeue(): Unit = {
    queue.enqueue(5)
    assertFalse(queue.isEmpty)
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue())
    assertTrue(queue.isEmpty)
    queue.enqueue(55)
    assertFalse(queue.isEmpty)
    assertEquals(55, queue.peek)
    assertEquals(55, queue.dequeue())
    assertTrue(queue.isEmpty)
  }

  @Test def enqueueEnqueueDequeueDequeue(): Unit = {
    queue.enqueue(5)
    queue.enqueue(55)
    assertFalse(queue.isEmpty)
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue())
    assertFalse(queue.isEmpty)
    assertEquals(55, queue.peek)
    assertEquals(55, queue.dequeue())
    assertTrue(queue.isEmpty)
  }

  @Test def enqueueDequeue100(): Unit = {
    val nums = Array.fill(10)(util.Random.nextInt())
    nums.foreach(queue.enqueue)
    nums.foreach { n =>
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
  }
}
