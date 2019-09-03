package scraper

import org.apache.logging.log4j.scala.Logging

object Main extends App with Logging {

  override def main(args: Array[String]): Unit = {
    if (args.length != 1 || args(0).toLong <= 0)
      throw new IllegalArgumentException("Non-negative parameter n is required.")
    val n: Long = args(0).toLong
    logger.info("Requested number: " + n)

    Computation.run(n)
  }
}
