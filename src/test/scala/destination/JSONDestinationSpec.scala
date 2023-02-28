package destination

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import origin.StringOrigin

import java.io.{File, FileNotFoundException}
import scala.reflect.io.Directory
import scala.sys.process.stringSeqToProcess

class JSONDestinationSpec extends AnyFlatSpec with Matchers{

  implicit val listOfStrings = List[String](
    "02/22/2023,32.94,33.23,32.03,33.01,85608",
    "02/21/2023,33.06,33.71,32.51,32.69,131290",
    "02/17/2023,33.98,34.23,33.39,33.48,90566",
    "02/16/2023,33.85,34.18,33.46,33.85,92206"
  )

  "Transform method" should "return a reduced list when filterMode is set to 'high volume'" in {
    val filterMode: String = "HighVolume"

    val expectedList: List[List[String]] = List[List[String]](List[String](
      "02/21/2023","33.06","33.71","32.51","32.69","131290"))

    val reducedList:List[List[String]] = JSONDestination.transform(listOfStrings, filterMode)._1
    val returnedFilterMode: String = JSONDestination.transform(listOfStrings, filterMode)._2

    assert(reducedList == expectedList && returnedFilterMode == filterMode)
  }

  "Transform method" should "return a full list when no filter mode is selected" in {
    val filterMode: String = ""

    val returnedList: List[List[String]] = JSONDestination.transform(listOfStrings, filterMode)._1
    val returnedFilterMode: String = JSONDestination.transform(listOfStrings, filterMode)._2

    assert(returnedList.size == listOfStrings.size && returnedFilterMode == filterMode)
  }

  "Save method" should "create a file in the specified directory" in {
    val filepath: String = "src/main/scala/destination/output/"
    val outputDirectory: Directory = new Directory(new File(s"$filepath"))

    val filterMode: String = "MidVolume"

    val transformedList: List[List[String]] = JSONDestination.transform(listOfStrings, filterMode)._1
    val returnedFilterMode: String = JSONDestination.transform(listOfStrings, filterMode)._2

    JSONDestination.save(transformedList, returnedFilterMode)

    assert(outputDirectory.list.size > 0)
  }


}
