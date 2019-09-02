package scraper

import org.jsoup.nodes.Document
import org.scalatest.FunSuite
import scraper.HtmlExtractor._

class HttpExtractorTest extends FunSuite {

  val html: String = readHtmlFile
  val doc: Document = convertToDocument(html)
  val pointsFromHtml: Int = 8426
  val idFromHtml: Long = 441201.toLong
  val contentFromHtml: String =
    "<Moniś> słowo \"wegetarianin\" pochodzi z jednego z narzeczy indiańskich i oznacza \"za głupi, by polować\""

  test("method should return json") {
    val values: List[Registration] = Registration(1l, 65l, "a") :: Registration(2l, -1l, "b") :: Nil
    val actual: String = toJsonValues(values)
    val expected: String = """{"bash":[{"id":1,"points":65,"content":"a"},{"id":2,"points":-1,"content":"b"}]}"""
    assert(actual == expected)
  }

  test("test should return points value from html") {
    val actual: Long = getPoints(doc)
    val expected: Int = pointsFromHtml
    assert(actual === expected)
  }

  test("test should return content value from html") {
    val actual: String = getContent(doc)
    val expected: String = contentFromHtml
    assert(actual === expected)
  }

  test("test should return proper id from html") {
    val actual: Long = getId(doc)
    val expected: Long = idFromHtml
    assert(actual === expected)
  }

  test("test should return case class registration with proper values") {
    val actual: Registration = extractFields(html)
    val expected = Registration(idFromHtml, pointsFromHtml, contentFromHtml)
    assert(actual === expected)
  }

  private def readHtmlFile: String = {
    scala.io.Source.fromFile("example/bash441201.html").mkString
  }
}
