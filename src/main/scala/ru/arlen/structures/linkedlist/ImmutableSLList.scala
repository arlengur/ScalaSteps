package ru.arlen.structures.linkedlist

import scala.collection.immutable.LinearSeq

/**
  * @author satovritti
  */
sealed trait ImmutableSLList[+A] extends LinearSeq[A] {
  def ::[B >: A](elem: B): ImmutableSLList[B] = new Cons(elem, this)
}

final class Cons[A](override val head: A, override val tail: ImmutableSLList[A]) extends ImmutableSLList[A] {
  override def length: Int = 1 + tail.length

  override def apply(idx: Int): A = if (idx == 0) head else tail(idx - 1)

  override def isEmpty = false
}

object MyNil extends ImmutableSLList[Nothing] {
  override def length: Int = 0

  override def apply(idx: Int) = throw new IllegalArgumentException("Can't index Nil")

  override def isEmpty = true

  override def head = throw new IllegalArgumentException("Can't get the head of Nil")

  override def tail = throw new IllegalArgumentException("Can't get the tail of Nil")
}