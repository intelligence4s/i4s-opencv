package i4s.opencv.image

import i4s.opencv.core.types.{MatTypes, Types}
import i4s.opencv.image.constants.{ColorConversionCodes, ColorMaps}
import org.bytedeco.opencv.opencv_core.Mat
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorsSpec extends AnyWordSpec with Matchers {

  "Colors" should {
    import Colors._

    "support color conversion" in {
      val image = Image(30,30,3)
      val gray = image.cvtColor(ColorConversionCodes.BGR2Gray)

      image.channels shouldBe 3
      image.dataType shouldBe Types.Cv8U
      image.matType shouldBe MatTypes.Cv8UC3

      gray.channels shouldBe 1
      gray.dataType shouldBe Types.Cv8U
      gray.matType shouldBe MatTypes.Cv8UC1
    }

    "support applying a color map" in {
      val gray = Image(30,30,1)
      val withColors = gray.applyColorMap(ColorMaps.Hot)
      println(withColors.description)

      withColors.channels shouldBe 3
      withColors.dataType shouldBe Types.Cv8U
      withColors.matType shouldBe MatTypes.Cv8UC3
    }
  }

}
