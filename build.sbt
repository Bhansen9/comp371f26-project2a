ThisBuild / scalaVersion := "3.9.0"
ThisBuild / organization := "edu.luc"
ThisBuild / version      := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "comp371f26-project2a",
    Compile / mainClass := Some("edu.luc.cs.consoleapp.Main"),
    executableScriptName := "main",
    libraryDependencies +=
      "org.apache.commons" % "commons-collections4" % "4.4",
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked"
    )
  )
