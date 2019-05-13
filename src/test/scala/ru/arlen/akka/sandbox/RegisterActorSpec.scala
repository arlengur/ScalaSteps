package ru.arlen.akka.sandbox

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class RegisterActorSpec extends TestKit(ActorSystem("ActorSpec")) with ImplicitSender with WordSpecLike with Matchers
  with BeforeAndAfterAll {
  override def afterAll(): Unit = TestKit.shutdownActorSystem(system)

  "RegisterActor" must {
    "receive register message and send with RegisterSuccess for a new user" in {
      val registerActor = system.actorOf(RegisterActor.props)

      registerActor ! RegisterActor.Register("arlen", "pass")

      expectMsg(RegisterActor.RegisterSuccess)

      registerActor ! RegisterActor.Register("arlen", "pass")

      expectMsg(RegisterActor.RegisterError("User with arlen already exist!"))
    }
  }
}
