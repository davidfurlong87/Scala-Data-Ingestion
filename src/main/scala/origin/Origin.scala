package origin

trait Origin {
  def extract: List[String]

  def clean(ls: List[String]): List[String]
}
