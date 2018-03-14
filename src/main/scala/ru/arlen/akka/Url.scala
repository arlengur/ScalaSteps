package ru.arlen.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * @author satovritti
  */
object Url extends App {

  case object CreateChild

  case class SignalChildren(order: Int)

  case class PrintSignal(order: Int)

  class ParentActor extends Actor {
    private var number = 1

    override def receive: Receive = {
      case CreateChild =>
        context.actorOf(Props[ChildActor], "child" + number)
        number += 1
      case SignalChildren(n) =>
        context.children.foreach(_ ! PrintSignal(n))
    }
  }

  class ChildActor extends Actor {
    override def receive: Receive = {
      case PrintSignal(n) => println(n + " " + self)
    }
  }

  val system = ActorSystem("ExSystem")
  val actor1 = system.actorOf(Props[ParentActor], "ParentActor1")
  val actor2 = system.actorOf(Props[ParentActor], "ParentActor2")

  actor1 ! CreateChild
  actor1 ! SignalChildren(1)
  actor1 ! CreateChild
  actor1 ! CreateChild
  actor1 ! SignalChildren(2)

  actor2 ! CreateChild
  val child0 = system.actorSelection("user/ParentActor2/child1")
  child0 ! PrintSignal(3)

  Thread.sleep(2000)
  system.terminate

}
