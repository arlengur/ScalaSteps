package ru.arlen.structures.linkedlist

import org.junit.Assert.assertEquals
import org.junit.Test

/**
  * @author satovritti
  */
class ImmutableSLListTest {
  @Test def create(): Unit = {
    assertEquals(5 :: MyNil, List(5))
    assertEquals("hi" :: 5 :: MyNil, List("hi", 5))
  }

}
