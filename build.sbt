import scalariform.formatter.preferences._
import bintray.Plugin.bintrayPublishSettings
import bintray.Keys._

name := "akka-dogstatsd"

scalaVersion := "2.11.7"
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-language:_",
  "-target:jvm-1.8",
  "-encoding", "UTF-8"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream-experimental" % "2.0-M1",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

scalariformSettings
ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(PreserveDanglingCloseParenthesis, true)

bintrayPublishSettings
bintrayOrganization in bintray := Some("typesafe")
repository in bintray := "commercial-maven-releases"

releaseSettings
ReleaseKeys.versionBump := sbtrelease.Version.Bump.Minor