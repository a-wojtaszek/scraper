package scraper

import dispatch.Defaults._
import dispatch._

import scala.concurrent.Future
import scala.util.{Failure, Success}
import HttpRequest._
import scraper.HtmlExtractor.{extractFields, toJsonValues, saveValuesToFile}
import scraper.UrlExtractor.getNLatestUrls

object Computation{

  def run(n: Long): Unit = {
    val urlArg: Req = url("http://bash.org.pl/text")
    val html: Future[String] = httpConnectionAndThenWaitForResult(urlArg)
    val nPages: Future[List[String]] = html.map(v => getNLatestUrls(n, v))
    val htmlsFromNPages: Future[List[String]] = nPages.flatMap(v => requestForAllPages(v))
    val extracted: Future[List[Registration]] = htmlsFromNPages.map(htmll => htmll.map(h => extractFields(h)))
    val result: Future[String] = extracted.map(v => toJsonValues(v))

    result onComplete {
      case Success(content) => {
        saveValuesToFile(content)
        Http.default.shutdown()
        System.exit(0)
      }
      case Failure(t) => {
        println("An error has occurred: " + t.getMessage)
        Http.default.shutdown()
        System.exit(0)
      }}
  }
}