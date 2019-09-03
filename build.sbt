name := "scraper"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.scala-tools.testing" % "specs" % "1.6.2" % "test",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "org.dispatchhttp" %% "dispatch-core" % "0.14.0",
  "org.jsoup" % "jsoup" % "1.8.3",
  "org.json4s" %% "json4s-native" % "3.6.0",
  "org.json4s" %% "json4s-jackson" % "3.6.0",
  "com.typesafe" % "config" % "1.2.1",
  "org.apache.logging.log4j" % "log4j-api-scala_2.12" % "11.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.12.1",
  "org.apache.logging.log4j" % "log4j-api" % "2.12.1",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.12.1"
)
resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
