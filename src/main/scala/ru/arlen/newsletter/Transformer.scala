package ru.arlen.newsletter

import java.io.{File, PrintWriter}

import scala.io.Source

object Transformer extends App{
  val TEMPLATE = "BEGIN:VCARD\nVERSION:2.1\nFN:%s\nTEL;CELL:%s\nEND:VCARD"
  val INPUT_FILE = "D:/SmsBase.txt"
  val OUTPUT_FILE = "D:/SmsBase.csv"

  var writer = new PrintWriter(new File(OUTPUT_FILE))
  for (line <- Source.fromFile(INPUT_FILE).getLines()) {
    val parts = line.split(";")
    try {
      val name =
        if (parts(1).contains("д.д.")) parts(1).replace("д.д.", "даси")
        else if (parts(1).contains("д.")) parts(1).replace("д.", "дас")
        else {
         parts(1)
      }
//      println(s"${parts(0)};$name")
      writer.write(s"${parts(0)};$name\n")
    } catch {
      case e: ArrayIndexOutOfBoundsException => println("Error name:"+parts(1))
    }

  }
  writer.close()

}

