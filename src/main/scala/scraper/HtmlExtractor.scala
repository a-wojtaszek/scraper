package scraper

import java.io.PrintWriter

import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import scala.util.matching.Regex

object HtmlExtractor {

  val conf = new Configuration
  val pathToSave: String = conf.pathToSave

  def saveValuesToFile(jsonValues: String): Unit = {
    new PrintWriter(pathToSave) {
      write(jsonValues)
      close()
    }
  }

  def toJsonValues(registrations: List[Registration]): String = {
    val json = "bash" -> registrations.map { r =>
      ("id" -> r.id) ~
        ("points" -> r.points) ~
        ("content" -> r.content)
    }
    compact(render(json))
  }

  def extractFields(source: String): Registration = {
    val doc = convertToDocument(source)
    val content: String = getContent(doc)
    val points: Long = getPoints(doc)
    val id: Long = getId(doc)
    Registration(id, points, content)
  }

  private[scraper] def getContent(document: Document): String = {
    document.select("div[class=quote post-content post-body]").text()
  }

  private[scraper] def getPoints(document: Document): Long = document.select("span[class=points]").text().toLong

  private[scraper] def getId(document: Document): Long = {
    new Regex("[0-9]+").findFirstIn(document.select("title").text()).toList.map(_.toLong).head
  }

  private[scraper] def convertToDocument(source: String): Document = Jsoup.parse(source)

}
