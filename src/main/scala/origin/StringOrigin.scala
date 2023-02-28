package origin

import java.io.FileNotFoundException
import scala.io.Source

case class StringOrigin(str: String) extends Origin {
  override def extract: List[String] = {
    try{
      Source.fromFile(str).getLines().toList
    }
    catch {
      case _: FileNotFoundException => throw new FileNotFoundException("Not Found")
    }
  }

  override def clean(ls: List[String]): List[String] = {
    ls.filter(s => !s.contains("Date")).map(s => s.toList.filter(c => !"£$\"".contains(c)).mkString(""))
  }
}
