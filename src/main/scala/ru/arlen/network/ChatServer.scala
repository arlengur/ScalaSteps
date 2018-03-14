package ru.arlen.network

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.{ServerSocket, Socket}
import java.util.concurrent.ConcurrentHashMap

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @author satovritti
  */
object ChatServer extends App {

  case class User(name: String, socket: Socket, in: BufferedReader, out: PrintStream)

  val users = new ConcurrentHashMap[String, User]().asScala

  Future {
    checkConnections()
  }

  while (true) {
    for ((name, user) <- users)
      doChat(user)
    Thread.sleep(100)
  }

  def nonBlockingRead(in: BufferedReader): Option[String] = {
    if (in.ready()) Some(in.readLine()) else None
  }

  def doChat(user: User): Unit = {
    nonBlockingRead(user.in).foreach { input =>
      if (input == "/quit") {
        user.socket.close()
        users -= user.name
      } else {
        for ((n, u) <- users) {
          u.out.println(user.name + " : " + input)
        }
      }
    }
  }

  def checkConnections(): Unit = {
    val ss = new ServerSocket(4000)
    while (true) {
      val socket = ss.accept()
      val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
      val out = new PrintStream(socket.getOutputStream)
      Future {
        out.println("What is your name?")
        val name = in.readLine()
        val user = User(name, socket, in, out)
        users += name -> user
      }
    }
  }
}
