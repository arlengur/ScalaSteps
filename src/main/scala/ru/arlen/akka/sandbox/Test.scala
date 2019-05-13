package ru.arlen.akka.sandbox

import akka.actor.ActorSystem

object Test extends App {

  // Создана акторная система, теперь это процесс
  // Управляет runtime как создаются и уничтожаются акторы, отправляют друг другу сообщения
  val system = ActorSystem("system")

}
