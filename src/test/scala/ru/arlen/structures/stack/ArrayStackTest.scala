package ru.arlen.structures.stack

import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * @author satovritti
  */
class ArrayStackTest {
  private var stack: Stack[Int] = _

  @Before def makeStack(): Unit = {
    stack = new ArrayStack[Int]()
  }

  @Test def emptyOnCreate(): Unit = {
    assertTrue(stack.isEmpty)
  }

  @Test def notEmptyOnPush(): Unit = {
    stack.push(1)
    assertFalse(stack.isEmpty)
  }

  @Test def pushPop(): Unit = {
    stack.push(1)
    assertEquals(1, stack.peek)
    assertEquals(1, stack.pop())
  }

  @Test def pushPoppushPop(): Unit = {
    stack.push(5)
    assertFalse(stack.isEmpty)
    assertEquals(5, stack.peek)
    assertEquals(5, stack.pop())
    assertTrue(stack.isEmpty)
    stack.push(55)
    assertFalse(stack.isEmpty)
    assertEquals(55, stack.peek)
    assertEquals(55, stack.pop())
    assertTrue(stack.isEmpty)
  }

  @Test def pushPushPopPop(): Unit = {
    stack.push(5)
    stack.push(55)
    assertFalse(stack.isEmpty)
    assertEquals(55, stack.peek)
    assertEquals(55, stack.pop())
    assertFalse(stack.isEmpty)
    assertEquals(5, stack.peek)
    assertEquals(5, stack.pop())
    assertTrue(stack.isEmpty)
  }

  @Test def pushPop100(): Unit = {
    val nums = Array.fill(100)(util.Random.nextInt())
    nums.foreach(stack.push)
    nums.reverse.foreach{n=>
      assertEquals(n, stack.peek)
      assertEquals(n, stack.pop())
    }
  }
}
