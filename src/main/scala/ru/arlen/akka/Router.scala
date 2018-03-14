package ru.arlen.akka

import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.BalancingPool

import scalafx.application
import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import scalafx.scene.image.{ImageView, PixelWriter, WritableImage}
import scalafx.scene.paint.Color

object Router extends JFXApp {
  val MaxCount = 10000
  val ImageSize = 600
  val Xmin = -1.5
  val Ymin = -1.0
  val Xmax = 0.5
  val Ymax = 1.0

  case class Complex(real: Double, imag: Double) {
    def +(that: Complex) = Complex(real + that.real, imag + that.imag)

    def *(that: Complex) = Complex(real * that.real - imag * that.imag,
      real * that.real + imag * that.imag)

    def mag = math.sqrt(real * real + imag * imag)
  }

  def RouterCount(c: Complex): Int = {
    var cnt = 0
    var z = Complex(0, 0)
    while (cnt < MaxCount && z.mag < 4) {
      z = z * z + c
      cnt += 1
    }
    cnt
  }

  case class Line(row: Int, y: Double)

  class LineActor(pw: PixelWriter) extends Actor {
    override def receive: Receive = {
      case Line(row, y) =>
        for (j <- 0 until ImageSize) {
          val x = Xmin + j * (Xmax - Xmin) / ImageSize
          val cnt = RouterCount(Complex(x, y))
          Platform.runLater {
            pw.setColor(j, row, if (cnt == MaxCount) Color.Black else {
              val scale = 10 * math.sqrt(cnt.toDouble / MaxCount) min 1.0
              Color(scale, 0, 0, 1)
            })
          }
        }
    }
  }

  val system = ActorSystem("system")

  stage = new application.JFXApp.PrimaryStage {
    title = "Actor router"
    scene = new Scene(ImageSize, ImageSize) {
      val image = new WritableImage(ImageSize, ImageSize)
      content = new ImageView(image)
      val router = system.actorOf(BalancingPool(4).props(Props(new LineActor(image.pixelWriter))), "Pool")
      for (i <- 0 until ImageSize) {
        val y = Ymin + i * (Ymax - Ymin) / ImageSize
        router ! Line(i, y)
      }
    }
  }

}
