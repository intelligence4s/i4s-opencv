import sbt._

object Dependencies {

  lazy val circe = "io.circe" %% "circe-generic" % "0.14.2"

  lazy val akkaDependencies = {
    val akkaVersion = "2.6.18"
    val akkaHttpVersion = "10.2.9"

    Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
    )
  }

  lazy val akkaHttpJson = "de.heikoseeberger" %% "akka-http-circe" % "1.39.2"

  lazy val guava = "com.google.guava" % "guava" % "31.1-jre"

  lazy val javacvPlatform = "org.bytedeco" % "javacv-platform" % "1.5.8-SNAPSHOT"

  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.6"

  lazy val neo4jDependencies = Seq(
  "org.neo4j.driver" % "neo4j-java-driver" % "4.3.4",
  "io.github.neo4s" %% "neo4s-cypher-dsl" % "0.5.0-SNAPSHOT",
  "io.github.neo4s" %% "neo4s-query-support" % "0.1.0-SNAPSHOT"
  )

  lazy val nlpDependencies = {
    val procVer = "8.5.3-SNAPSHOT"
    Seq(
    "org.clulab" %% "processors-main" % procVer,
    "org.clulab" %% "processors-corenlp" % procVer,
    "edu.stanford.nlp" % "stanford-corenlp" % "4.3.1",
    "edu.stanford.nlp" % "stanford-corenlp" % "4.3.1" classifier "models"
    )
  }

  lazy val pureconfig = "com.github.pureconfig" %% "pureconfig" % "0.17.1"

  lazy val sangriaDependencies = {
    val sangriaAkkaHttpVersion = "0.0.2"

    Seq(
      "org.sangria-graphql" %% "sangria" % "2.1.3",
      "org.sangria-graphql" %% "sangria-slowlog" % "2.0.2",
      "org.sangria-graphql" %% "sangria-circe" % "1.3.2",

      "org.sangria-graphql" %% "sangria-akka-http-core" % sangriaAkkaHttpVersion,
      "org.sangria-graphql" %% "sangria-akka-http-circe" % sangriaAkkaHttpVersion,
    )
  }

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9" % Test
  lazy val scalaV = "2.13.8"

}
