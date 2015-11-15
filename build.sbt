name := "akka-dogstatsd"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream-experimental" % "2.0-M1",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)