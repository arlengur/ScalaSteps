package ru.arlen.akka

import akka.actor.{Actor, ActorSystem, Props}
import scala.concurrent.duration._

/**
  * @author satovritti
  */
object Scheduler extends App {

  case object Count

  class ScheduleActor extends Actor {
    var n = 0

    override def receive: Receive = {
      case Count =>
        n += 1
        println(n)
    }
  }

  val system = ActorSystem("ExSysytem")
  val actor = system.actorOf(Props[ScheduleActor], "MyActor")
  implicit val ec = system.dispatcher

  actor ! Count

  system.scheduler.scheduleOnce(1.second)(actor ! Count)
  private val canc = system.scheduler.schedule(0.seconds, 100.millis, actor, Count)

  Thread.sleep(2000)
  canc.cancel
  system.terminate

}
