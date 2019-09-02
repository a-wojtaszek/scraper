package scraper
object Main extends App {

  override def main(args: Array[String]): Unit = {
    if (args.length != 1 || args(0).toLong <= 0) throw new IllegalArgumentException("Parameter n is required. " +
      "This is only one parameter of the program and should be > 0.")
    val n: Long = args(0).toLong
    println("n=" + n)

    Computation.run(n)
  }
}
