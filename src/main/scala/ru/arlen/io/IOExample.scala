package ru.arlen.io

import cats.Monad
import cats.effect.IO
import cats.effect.Sync
import cats.implicits._

//////////////////////////////
// Classic DI example
//////////////////////////////
trait HelloPrinter[F[_]] {
  def print(s: String): F[Unit]
}

class HelloPrinterImpl[F[_]: Sync] extends HelloPrinter[F] {
  override def print(s: String): F[Unit] = Sync[F].delay(println(s))
}

//////////////////////////////
// Monad definition
//////////////////////////////

class Init[A](f: => A) {
  lazy val value: A = mock.getOrElse(f)

  var mock: Option[A] = None
}

object Init {
  def apply[A](f: => A): Init[A] = new Init(f)

  implicit val monad: Monad[Init] = new Monad[Init] {
    override def flatMap[A, B](fa: Init[A])(f: A => Init[B]): Init[B] = Init {
      f(fa.value).value
    }

    override def tailRecM[A, B](a: A)(f: A => Init[Either[A, B]]): Init[B] = ???

    override def pure[A](x: A): Init[A] = Init(x)
  }
}

//////////////////////////////
// Module definition
//////////////////////////////

class PrinterModule[F[_]: Sync] {
  val printer: Init[HelloPrinter[F]] = Init {
    new HelloPrinterImpl[F]
  }
}

//////////////////////////////
// Example program
//////////////////////////////

object ModuleMain {
  // create the app
  val app = new PrinterModule[IO]

  def main(args: Array[String]): Unit = {
    // run application
    app.printer.value
      .print("Arlen")
      .unsafeRunSync()

    // create another application instance
    val app2 = new PrinterModule[IO]

    // mock HelloPrinter - helloService depends on it
    app2.printer.mock = Some({
      new HelloPrinter[IO] {
        override def print(s: String): IO[Unit] = IO(println(s"== $s == "))
      }
    })

    // run application
    app2.printer.value
      .print("Arlen")
      .unsafeRunSync()
  }
}