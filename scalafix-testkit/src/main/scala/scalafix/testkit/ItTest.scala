package scalafix.tests

import scalafix.rewrite.ProcedureSyntax
import scalafix.rewrite.Rewrite
import scalafix.rewrite.ScalafixRewrites

import ammonite.ops._
import java.io.File

import ammonite.ops.Path

case class ItTest(
    name: String,
    repo: String,
    hash: String,
    config: String = "",
    commands: Seq[Command] = Command.default,
    rewrites: Seq[String] = Nil,
    addCoursier: Boolean = true
) {
  def repoName: String = repo match {
    case Command.RepoName(x) => x
    case _ =>
      throw new IllegalArgumentException(
        s"Unable to parse repo name from repo: $repo")
  }
  def workingPath: Path = ItTest.root / repoName
  def parentDir: File = workingPath.toIO.getParentFile
}

object ItTest {
  val catsImportConfig: String =
    """|imports.organize = true
       |imports.removeUnused = false
       |imports.groupByPrefix = true""".stripMargin
  val root: Path = pwd / "target" / "it"
}
