package scraper

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}

class Configuration {

  val config: Config = ConfigFactory.parseFile(new File("src/main/resources/application.conf"))
  val configuration: Config = config.getConfig("configuration")
  val pathToSave: String = configuration.getString("path_to_save")

}
