name := "free-the-monads"

version := "1.0"

ivyScala := ivyScala.value map {_.copy(overrideScalaVersion = true)}

scalaVersion := "2.11.6"

resolvers ++= Seq(
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
  "typesafe" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4",
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "org.scalaz" %% "scalaz-effect" % "7.1.3",
  "org.specs2" %% "specs2-core" % "3.4" % "test",
  "org.specs2" %% "specs2-junit" % "3.4" % "test",
  "org.specs2" %% "specs2-matcher-extra" % "3.4" % "test",
  "org.specs2" %% "specs2-html" % "3.4" % "test",
  "org.specs2" %% "specs2-scalacheck" % "3.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"
)

