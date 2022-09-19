import sbt.url
import Dependencies._

ThisBuild / scalaVersion     := scalaV
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.github.intelligence4s"
ThisBuild / organizationName := "Intelligence4s"

ThisBuild / versionScheme := Some("early-semver")

lazy val root = (project in file("."))
  .settings(
    name := "i4s-opencv",
    licenses := Seq("MIT" -> url("https://www.mit.edu/~amini/LICENSE.md")),
    homepage := Some(url("https://github.com/intelligence4s/i4s-opencv")),
    pomIncludeRepository := (_ => false),
    scmInfo := Some(ScmInfo(url("https://github.com/intelligence4s/i4s-opencv"),"scm:git:git@github.com:intelligence4s/i4s-opencv.git")),
    developers := List(
      Developer(
        id = "thebrenthaines",
        name = "Brent Haines",
        email = "thebrenthaines@yahoo.com",
        url = url("https://github.com/intelligence4s")
      )
    ),
    publishMavenStyle := true,
    resolvers ++= Seq(
      "Sonatype OSS Snapshots" at "https://s01.oss.sonatype.org/content/repositories/snapshots",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    ),
    javaCppVersion := "1.5.8-SNAPSHOT",
    javaCppPresetLibs ++= Seq("opencv" -> "4.6.0", "ffmpeg" -> "5.1"),

    fork := true,

    libraryDependencies ++=
      neo4jDependencies ++
      Seq(
        guava,
        javacvPlatform,
        "org.scala-lang" % "scala-reflect" % scalaV,
        scalaTest
      ) 
  )

