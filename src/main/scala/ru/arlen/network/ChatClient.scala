package ru.arlen.network

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.Socket

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.StdIn.readLine

/**
  * @author satovritti
  */
object ChatClient extends App {
  println("Making socket")
  val socket = new Socket("localhost", 4000)
  println("Socket made")

  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
  val out = new PrintStream(socket.getOutputStream)
  Future {
    while (true) {
      val serverRespond = in.readLine()
      println(serverRespond)
    }
  }
  var input = ""
  while (input != "/quit") {
    input = readLine()
    out.println(input)
  }
  socket.close()
}
