package ru.arlen.sort

import javafx.scene.layout.HBox

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control.Button
import scalafx.scene.paint.Color

/**
  * @author satovritti
  */
object SortingGui extends JFXApp {
  var w = 600
  var h = 400
  var delay = 5
  stage = new JFXApp.PrimaryStage {
    title = "Sorts"
    scene = new Scene(w, h) {
      val canvas = new Canvas(w, h)

      val gc = canvas.graphicsContext2D
      val bubbleButton = new Button("Bubble") {
        onAction = { _ =>
          Future {
            bubbleSort(Array.fill(w)(util.Random.nextInt(h)), gc)
          }
        }
      }
      val minButton = new Button("Selection") {
        onAction = { _ =>
          Future {
            bubbleSort(Array.fill(w)(util.Random.nextInt(h)), gc)
          }
        }
      }
      val insertButton = new Button("Insertion") {
        Future {
          onAction = { _ =>
            Future {
              bubbleSort(Array.fill(w)(util.Random.nextInt(h)), gc)
            }
          }
        }
      }
      val shellButton = new Button("Shell") {
        Future {
          onAction = { _ =>
            Future {
              shellSort(Array.fill(w)(util.Random.nextInt(h)), gc)
            }
          }
        }
      }
      content.addAll(canvas, new HBox(5, bubbleButton, minButton, insertButton, shellButton))
    }
  }

  def renderValues(a: Array[Int], j: Int, gc: GraphicsContext): Unit = {
    gc.clearRect(0, 0, w, h)
    gc.stroke = Color.Black
    for (i <- a.indices)
      gc.strokeLine(i, h, i, h - a(i))
    gc.stroke = Color.Red
    gc.strokeLine(j, 0, j, 100)
  }

  /**
    * Bubble sort
    */
  def bubbleSort(a: Array[Int], gc: GraphicsContext): Unit = {
    for (i <- 0 until a.length - 1)
      for (j <- 0 until a.length - 1 - i) {
        if (a(j) > a(j + 1)) {
          val tmp = a(j)
          a(j) = a(j + 1)
          a(j + 1) = tmp
        }
        Platform.runLater(renderValues(a, j, gc))
        Thread.sleep(delay)
      }
  }

  /**
    * Selection sort
    */
  def minSort(a: Array[Int], gc: GraphicsContext): Unit = {
    for (i <- 0 until a.length - 1) {
      var min = i
      for (j <- i + 1 until a.length) {
        if (a(j) < a(min)) min = j
        Platform.runLater(renderValues(a, j, gc))
        Thread.sleep(delay)
      }
      val tmp = a(i)
      a(i) = a(min)
      a(min) = tmp
    }
  }

  /**
    * Insertion sort
    */
  def insertionSort(a: Array[Int], gc: GraphicsContext): Unit = {
    for (i <- 1 until a.length) {
      var j = i - 1
      val tmp = a(i)
      while (j >= 0 && tmp < a(j)) {
        a(j + 1) = a(j)
        j -= 1
        Platform.runLater(renderValues(a, j, gc))
        Thread.sleep(delay)
      }
      a(j + 1) = tmp
    }
  }

  /**
    * Shell sort
    */
  def shellSort(a: Array[Int], gc: GraphicsContext): Unit = {
    var gap = a.length / 2
    while (gap >= 1) {
      for (i <- gap until a.length) {
        var j = i - gap
        val tmp = a(i)
        while (j >= 0 && tmp < a(j)) {
          a(j + gap) = a(j)
          j -= gap
          Platform.runLater(renderValues(a, j, gc))
          Thread.sleep(delay)
        }
        a(j + gap) = tmp
      }
      gap = (gap / 2.2).round.toInt
    }
  }
}
