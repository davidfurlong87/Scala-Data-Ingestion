package destination

import java.io._

object JSONDestination extends Destination {
  override def save(ls: List[List[String]], filterMode: String): Unit = {
    val filepath: String = "src/main/scala/destination/output/"
    val file = new File(s"$filepath${java.time.LocalDate.now} - $filterMode.txt")
    val bw: BufferedWriter = new BufferedWriter(new FileWriter(file))
    //code for converting lsit into s
    for (list: List[String] <- ls){
      bw.write(list.mkString)
    }
    bw.close()
  }


  override def transform(ls: List[String], filterMode: String): (List[List[String]], String) = {
    filterMode match {
      case "HighVolume" => (ls.map(s => s.split(",").toList).filter(l => l(5).toInt >= 100000), filterMode)
      case "MidVolume" => (ls.map(s => s.split(",").toList).filter(l => l(5).toInt >= 50000 && l(5).toInt >= 50000), filterMode)

      case _ => (ls.map(s  => s.split(",").toList), "Unfiltered")
    }

  }
}
