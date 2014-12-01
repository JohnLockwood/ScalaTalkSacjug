scalaVersion := "2.11.2"

libraryDependencies ++= Seq (
    "com.datastax.cassandra" % "cassandra-driver-core" % "latest.release",
    "org.skife.com.typesafe.config" % "typesafe-config" % "latest.release",
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)
