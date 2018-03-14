package ru.arlen.akka

import akka.actor.{Actor, ActorSystem, Props}

/**
  * @author satovritti
  */
object ActorExample extends App {

  class MyActor extends Actor {
    override def receive: Receive = {
      case str: String => println("String: " + str)
      case num: Number => println("Number: " + num)
    }
  }

  val system = ActorSystem("ExSysytem")
  val actor = system.actorOf(Props[MyActor], "MyActor")

  actor ! "Hello!"
  actor ! 42

  system.terminate()

}
