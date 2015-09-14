import sbt._
import sbt.Keys._

object Send_imgpBuild extends Build {

  lazy val send_imgp = Project(
    id = "send_imgp",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "send_imgp",
      organization := "jp.kddi.maruya",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.11.7"
      // add other settings here
    )
  )
}
