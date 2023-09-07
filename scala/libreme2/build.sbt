val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "LibreMe2",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "org.slf4j" % "slf4j-simple" % "1.7.5"
    ),
    libraryDependencies += "org.augustjune" %% "canoe" % "0.6.0",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
