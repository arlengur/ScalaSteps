lazy val commonSettings = Defaults.coreDefaultSettings ++ Seq(
  organization := "ru.arlen",
  version := "0.0.1",
  scalaVersion := "2.12.5",
  scalacOptions ++= List("-unchecked", "-deprecation", "-encoding", "UTF8", "-feature")
)

lazy val root = {
  project in file(".")
}.settings(commonSettings).settings(
  name := "ScalaSteps",
  libraryDependencies ++= dependencies ++ testDependencies
)

val akkaVersion = "2.5.12"

val dependencies = Seq(
  // organization %% library %% version
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % "10.1.1",
  "org.scalafx" %% "scalafx" % "8.0.102-R11",
)
val testDependencies = Seq(
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "org.scalatest" %% "scalatest" % "3.2.0-SNAP10" % Test,
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.1" % Test
)