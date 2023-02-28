package destination

trait Destination {
  def save(ls: List[List[String]],filterMode: String): Unit
  def transform( ls: List[String],filterMode:String): (List[List[String]], String)
}
