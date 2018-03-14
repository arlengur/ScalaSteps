package ru.arlen.akka

import akka.actor.SupervisorStrategy.{Restart, Resume}
import akka.actor.{Actor, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}

/**
  * @author satovritti
  */
object Supervision extends App {

  case object CreateChild
  case object ErrorStuff

  case class DivideNumbers(n: Int, d: Int)

  class ParentActor extends Actor {
    private var number = 1

    override def receive: Receive = {
      case CreateChild =>
        context.actorOf(Props[ChildActor], "child" + number)
        number += 1
    }

    override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy(loggingEnabled = false) {
      case ae: ArithmeticException => Resume
      case ex: Exception => Restart
    }
  }

  class ChildActor extends Actor {
    println("Child created.")
    override def receive: Receive = {
      case DivideNumbers(n, d) => println(n / d)
      case ErrorStuff => throw new RuntimeException
    }

    override def preStart(): Unit = {
      super.preStart()
      println("preStart")
    }

    override def postStop(): Unit = {
      super.postStop()
      println("postStop")
    }

    override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
      super.preRestart(reason, message)
      println("preRestart")
    }

    override def postRestart(reason: Throwable): Unit = {
      super.postRestart(reason)
      println("postRestart")
    }
  }

  val system = ActorSystem("ExSystem")
  val actor1 = system.actorOf(Props[ParentActor], "ParentActor1")

  actor1 ! CreateChild
  val child0 = system.actorSelection("user/ParentActor1/child1")
  child0 ! DivideNumbers(4, 0)
  child0 ! DivideNumbers(4, 2)
  child0 ! ErrorStuff

  Thread.sleep(2000)
  system.terminate

}
