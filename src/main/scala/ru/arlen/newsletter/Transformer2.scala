package ru.arlen.newsletter

import java.io.{File, PrintWriter}

import scala.io.Source

object Transformer2 extends App {
  val TEMPLATE = "BEGIN:VCARD\nVERSION:2.1\nFN:%s\nTEL;CELL:%s\nEND:VCARD"
  val INPUT_FILE = "D:/SmsBase.txt"
  val OUTPUT_FILE = "D:/SmsBase.csv"

  var writer = new PrintWriter(new File(OUTPUT_FILE))
  for (line <- Source.fromFile(INPUT_FILE).getLines()) {
    val parts = line.split(";")
    writer.write(TEMPLATE.format(parts(1), parts(0))+"\n")
  }
  writer.close()
}
