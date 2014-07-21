package org.sbuild.plugins.bndjar

import org.sbuild._
import scala.util.Success
import scala.util.Failure
import java.io.File

class BndJarPlugin(implicit project: Project) extends Plugin[BndJar] {

  override def create(name: String): BndJar = {
    val targetDir = Path("target")

    val phonyTarget = Some(s"jar-${name}")
    val jarFile = targetDir / s"${name}.jar"

    BndJar(
      jarFile = jarFile,
      bndLib = "mvn:biz.aQute.bnd:bndlib:2.3.0",
      classesDirs = Seq(targetDir / s"${name}-classes"),
      classpath = Seq(),
      props = Map(),
      phonyTarget = phonyTarget
    )
  }

  override def applyToProject(instances: Seq[(String, BndJar)]): Unit = instances foreach {
    case (name, jar) =>

      val jarTarget = Target(jar.jarFile) dependsOn project.projectFile ~ jar.dependsOn ~ jar.classpath ~ jar.bndLib ~ jar.classesDirs.map(d => TargetRef(s"scan:${d.getPath}")) exec {
        BndJarTask(
          bndClasspath = jar.bndLib.files,
          classpath = jar.classpath.files ++ jar.classesDirs,
          destFile = jar.jarFile,
          props = jar.props
        )
      }

      jar.phonyTarget.map { tName =>
        Target(s"phony:${tName}") dependsOn jarTarget
      }

  }

}