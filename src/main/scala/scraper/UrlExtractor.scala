package scraper

import scala.util.matching.Regex

object UrlExtractor {
  val bashUrl: String = "http://bash.org.pl"

  def getNLatestUrls(n: Long, htmlResult: String): List[String] = {
    val allUrls: List[String] = findAllBashPages(htmlResult)
    allUrls.take(n.toInt)
  }

  private[scraper] def findAllBashPages(v: String): List[String] =
    new Regex(s"$bashUrl/[0-9]+/").findAllMatchIn(v).toList.map(_.toString())
}

