package ru.arlen.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout

//import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * @author satovritti
  */
object AskPatternExample extends App {

  case object AskName

  case class NameResponse(name: String)

  case class AskNameOf(actor: ActorRef)

  class AskActor(val name: String) extends Actor {
    implicit val ec = context.system.dispatcher
    override def receive: Receive = {
      case AskName =>
        sender ! NameResponse(name)
      case AskNameOf(other) =>
        implicit val timeout = Timeout(1.seconds)
        val f = other ? AskName
        f.onComplete {
          case Success(NameResponse(n)) =>
            println("They said their name was " + n)
          case Success(n) =>
            println("They didn't tell us their name")
          case Failure(ex)=>
            println("Asking their name failed.")
        }
    }
  }

  val system = ActorSystem("ExSystem")
  val actor1 = system.actorOf(Props(new AskActor("Pat")), "AskActor1")
  val actor2 = system.actorOf(Props(new AskActor("Val")), "AskActor2")
  implicit val ec = system.dispatcher
  implicit val timeout = Timeout(1.seconds)
  val answer = actor1 ? AskName

  answer.foreach(println)

  actor2 ! AskNameOf(actor2)

  system.terminate
}
