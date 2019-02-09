enablePlugins(JavaAppPackaging, AshScriptPlugin)


name := "kreditech"

version := "1.0"

scalaVersion := "2.11.7"

val akkaHttpV = "2.4.2"

dockerBaseImage := "openjdk:8-jre-alpine"
packageName in Docker := "akkahttp-customurl"

resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core" %  akkaHttpV,
  "com.typesafe.akka" %% "akka-http-experimental" %  akkaHttpV,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaHttpV,
  "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaHttpV,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
  "org.scalatest" %% "scalatest" % "2.2.5",
  "org.apache.spark" %% "spark-sql" % "2.0.0",
  "io.jsonwebtoken"%"jjwt"%"0.6.0",
  "com.fasterxml.uuid" % "java-uuid-generator" % "3.1.4",
  "org.jsoup" % "jsoup" % "1.11.2"
)
