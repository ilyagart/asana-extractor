ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.15"

lazy val root = (project in file("."))
  .settings(
    name := "asana-extractor"
  )

libraryDependencies += "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M20"
libraryDependencies += "com.softwaremill.sttp.client4" %% "upickle" % "4.0.0-M20"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.19"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test"