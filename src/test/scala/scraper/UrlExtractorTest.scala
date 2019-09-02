package scraper

import org.scalatest.FunSuite
import scraper.UrlExtractor._

class UrlExtractorTest extends FunSuite {

  val regexText: String =
    """ %
      |#57 (http://bash.org.pl/57454/)
      |<kosmosik> w /mnt/magazyn/filmy
      |%
      |#51 (http://bash.org.pl/531/)
      |<neodave_> trzeba jesc banany 554
      |%
      |#49 (http://bash.org.pl/43/)
      |%
      |#43 (http://bash.org.pl/4/)
      |<^Abaddon^> hh sux 6454
      |<fellow> to nie hh
      |%""".mkString


  val resultPageIds: List[Int] = List(57454, 531, 43, 4)

  test("extract bash urls from string") {
    val actual: List[String] = findAllBashPages(regexText).sorted
    val expected: List[String] = getUrlWithId.sorted
    assert(actual === expected)
  }

  test("method should return n first elements") {

    val actual: List[String] = getNLatestUrls(3, regexText)
    val expected: List[String] = getUrlWithId.take(3)
    assert(actual == expected)
  }

  private def getUrlWithId: List[String] = resultPageIds.map(value => s"http://bash.org.pl/$value/")

}
