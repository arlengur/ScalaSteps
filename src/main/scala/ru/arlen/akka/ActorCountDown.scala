package ru.arlen.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * @author satovritti
  */
object ActorCountDown extends App {

  case class StartCounting(n: Int, other: ActorRef)

  case class CountDown(n: Int)

  class CDActor extends Actor {
    override def receive: Receive = {
      case StartCounting(n, other) =>
        println(n)
        other ! CountDown(n - 1)
      case CountDown(n) =>
        if (n > 0) {
          println(n)
          sender ! CountDown(n - 1)
        } else context.system.terminate
    }

  }

  val system = ActorSystem("ExSystem")
  val actor1 = system.actorOf(Props[CDActor], "CDActor1")
  val actor2 = system.actorOf(Props[CDActor], "CDActor2")

  actor1 ! StartCounting(10, actor2)
}
