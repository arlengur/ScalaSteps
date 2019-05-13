package ru.arlen.akka.sandbox

import akka.actor.{Actor, ActorLogging, Props}

import scala.collection.mutable

class RegisterActor extends Actor with ActorLogging {
  val users = mutable.Map.empty[String, String]

  import RegisterActor._

  override def receive: Receive = {
    case Register(name, pass) =>
      log.debug(s"received a message Register($name, $pass)")
      val exist = users.exists { case (n, _) => n == name }
      if (exist) sender ! RegisterError(s"User with $name already exist!")
      else {
        users += (name -> pass)
        log.debug(s"registered a user: $name")
        sender ! RegisterSuccess
      }
  }
}

object RegisterActor {

  val props = Props(new RegisterActor)

  sealed trait RegisterMessage

  case class Register(name: String, pass: String) extends RegisterMessage

  case class RegisterError(error: String) extends RegisterMessage

  case object RegisterSuccess extends RegisterMessage

}