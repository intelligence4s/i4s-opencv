import sbt.url
import Dependencies._

val scala212 = "2.12.16" // up to 2.12.16
val scala213 = "2.13.8"  // up to 2.13.8
val scala30  = "3.0.2"   // up to 3.0.2
val scala31  = "3.1.3"   // up to 3.1.3

ThisBuild / crossScalaVersions := Seq(scala213,scala212)
ThisBuild / scalaVersion := crossScalaVersions.value.head
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.intelligence4s"
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

