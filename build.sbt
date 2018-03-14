name := "ScalaSteps"

version := "1.0"

scalaVersion := "2.12.4"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.102-R11"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.11"

// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems
fork := true
        