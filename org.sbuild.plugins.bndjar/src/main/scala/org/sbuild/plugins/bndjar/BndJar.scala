package org.sbuild.plugins.bndjar

import java.io.File
import org.sbuild._

/**
 * Configuration for the SBuild BndJar Plugin.
 *
 * Based on it's configuration, this plugin will register various targets.
 *
 * @param jarFile The JAR file which will be created.
 */
case class BndJar(
    jarFile: File,
    bndLib: TargetRefs = TargetRefs(),
    props: Map[String, String],
    classesDirs: Seq[File] = Seq(),
    classpath: TargetRefs = TargetRefs(),
    dependsOn: TargetRefs = TargetRefs(),
    phonyTarget: Option[String] = None) {

  def jarFile(jarFile: File): BndJar = copy(jarFile = jarFile)
  def bndLib(bndLib: TargetRefs): BndJar = copy(bndLib = bndLib)
  def addBndlib(bndLib: TargetRefs): BndJar = copy(bndLib = this.bndLib ~ bndLib)
  def props(props: Map[String, String]): BndJar = copy(props = props)
  def addProps(props: Map[String, String]): BndJar = copy(props = this.props ++ props)
  def addClasspath(classpath: TargetRefs): BndJar = copy(classpath = this.classpath ~ classpath)
  def classesDirs(classesDirs: File*): BndJar = copy(classesDirs = classesDirs)
  def addClassesDirs(classesDirs: File*): BndJar = copy(classesDirs = this.classesDirs ++ classesDirs)
  def classpath(classpath: TargetRefs): BndJar = copy(classpath = classpath)
  def dependsOn(dependsOn: TargetRefs): BndJar = copy(dependsOn = dependsOn)
  def addDependsOn(dependsOn: TargetRefs): BndJar = copy(dependsOn = this.dependsOn ~ dependsOn)
  def phonyTarget(phonyTarget: Option[String]): BndJar = copy(phonyTarget = phonyTarget)
  def phonyTarget(phonyTarget: String): BndJar = copy(phonyTarget = Some(phonyTarget))

  override def toString() = getClass().getSimpleName() +
    "(jarFile=" + jarFile +
    ",bndLib=" + bndLib +
    ",props=" + props +
    ",classesDirs=" + classesDirs +
    ",classpath=" + classpath +
    ",dependsOn=" + dependsOn +
    ",phonyTarget=" + phonyTarget +
    ")"

}

