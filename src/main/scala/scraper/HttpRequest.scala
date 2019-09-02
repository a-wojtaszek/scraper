package scraper

import dispatch.Defaults._
import dispatch._
import org.asynchttpclient.filter.ThrottleRequestFilter

import scala.concurrent.Future

object HttpRequest {

  private val client = Http.withConfiguration(
    _.addRequestFilter(new ThrottleRequestFilter(100))
  )

  def requestForAllPages(urls: List[String]): Future[List[String]] = {
    val result = urls.map(urll => HttpRequest.httpConnectionAndThenWaitForResult(url(urll)))
    Future.sequence(result)
  }

  def httpConnectionAndThenWaitForResult(urll: Req): Future[String] = {
    client(urll OK as.String)
  }
}