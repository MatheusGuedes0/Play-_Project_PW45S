name := "playApp"
ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "3.5.2"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  evolutions,
  javaJpa,
  "org.hibernate" % "hibernate-core" % "5.4.33.Final", // Core do Hibernate
  "org.hibernate" % "hibernate-entitymanager" % "5.6.15.Final", // EntityManager API
  "com.typesafe.play" %% "play-java-jpa" % play.core.PlayVersion.current,
  "com.h2database" % "h2" % "2.2.224"
)


// Configurações adicionais
fork := true // Necessário para "sbt run" usar javaOptions
javaOptions += "-Dplay.editor=http://localhost:63342/api/file/?file=%s&line=%s"

// Excluir resources do META-INF
PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"
