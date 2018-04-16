
name := """hilti-order-entry"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")
libraryDependencies += guice

libraryDependencies += guice
libraryDependencies += evolutions
libraryDependencies += javaWs


// DB Dependencies
libraryDependencies += javaJdbc
libraryDependencies += jdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.6"

// GraphQL
libraryDependencies += "com.graphql-java" % "graphql-java-tools" % "4.3.0"

// Access Token management
libraryDependencies += "io.jsonwebtoken" % "jjwt" % "0.9.0"


// Test Database
libraryDependencies += "com.h2database" % "h2" % "1.4.196"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
