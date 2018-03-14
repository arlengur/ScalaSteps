package ru.arlen.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * @author satovritti
  */
object Hierarchy extends App {

  case object CreateChild

  case object SignalChildren

  case object PrintSignal

  class ParentActor extends Actor {
    private var number = 1
    private val children = collection.mutable.Buffer[ActorRef]()

    override def receive: Receive = {
      case CreateChild =>
        children += context.actorOf(Props[ChildActor], "child" + number)
        number += 1
      case SignalChildren =>
        children.foreach(_ ! PrintSignal)
    }
  }

  class ChildActor extends Actor {
    override def receive: Receive = {
      case PrintSignal => println(self)
    }
  }

  val system = ActorSystem("ExSystem")
  val actor = system.actorOf(Props[ParentActor], "ParentActor")

  actor ! CreateChild
  actor ! SignalChildren
  actor ! CreateChild
  actor ! CreateChild
  actor ! SignalChildren

  system.terminate

}
