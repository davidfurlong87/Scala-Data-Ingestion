package origin
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.matchers.should.Matchers.not.be

import java.io.FileNotFoundException

class StringOriginSpec extends AnyFlatSpec with Matchers {
  "extract method" should "return a list equal in size to number of lines in csv" in {
    val myOrigin = new StringOrigin("src/test/resources/stocksCsv.txt")
    val extractedStringList: List[String] = myOrigin.extract

    extractedStringList.size should be (23)
  }

  "extract method" should "throw a FileNotFoundException if file doesn't exist" in {
    val myOrigin = new StringOrigin("src/test/resources/xyzstocksCsv.txt")

    assertThrows[FileNotFoundException] {
      myOrigin.extract
    }
  }

  "clean method" should "remove the first line if it contains column headings" in {
    val myOrigin = new StringOrigin("src/test/resources/stocksCsv.txt")
    val extractedStringList: List[String] = myOrigin.extract
    val cleanedStringList: List[String] = myOrigin.clean(extractedStringList)

    cleanedStringList.size should be (22)
  }

  "clean method" should "not remove the first line if it doesn\'t contain column headings" in {
    val myOrigin = new StringOrigin("src/test/resources/stocksCsv_noHeadings.txt")
    val extractedStringList: List[String] = myOrigin.extract
    val cleanedStringList: List[String] = myOrigin.clean(extractedStringList)

    cleanedStringList.size should be (20)
  }

  it should "remove any \",£ or $ characters in a string" in {
    val myOrigin = new StringOrigin("src/test/resources/stocksCsv_noHeadings.txt")
    val extractedStringList: List[String] = myOrigin.extract
    val cleanedStringList: List[String] = myOrigin.clean(extractedStringList)

    assert(cleanedStringList.mkString("").filter(c => "£$\"".contains(c)).isEmpty)
  }





}
