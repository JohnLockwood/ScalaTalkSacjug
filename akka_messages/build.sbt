resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
    "com.typesafe.play" % "play-json_2.11" % "2.4.0-M1",
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
    "com.typesafe.akka" % "akka-actor_2.11" % "2.3.7",
    "com.typesafe.akka" % "akka-testkit_2.11" % "2.3.7"
)


