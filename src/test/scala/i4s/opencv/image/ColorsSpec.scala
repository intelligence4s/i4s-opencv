package i4s.opencv.image

import com.google.common.hash.{HashCode, Hashing}
import i4s.opencv.core.model.{Point, Scalar}
import i4s.opencv.core.types.{MatTypes, Types}
import i4s.opencv.image.constants.{ColorConversionCodes, ColorMaps, LineTypes}
import org.bytedeco.opencv.opencv_core.Mat
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ColorsSpec extends AnyWordSpec with Matchers {

  "Colors" should {
    import Colors._
    import Drawing._

    "support color conversion" in {
      val image = Image(30,30,3)
      image.circle(Point(15,15),5,Scalar.Blue,-1,LineTypes.Line4,shift = 0)
      val gray = image.cvtColor(ColorConversionCodes.BGR2Gray)

      image.channels shouldBe 3
      image.dataType shouldBe Types.Cv8U
      image.matType shouldBe MatTypes.Cv8UC3

      val hash = image.rawValues.foldLeft(Hashing.sha256().newHasher())(_ putInt _).hash()
      hash shouldBe HashCode.fromString("b0571a3555c7a248fceccf9bb364903136d1abedfe5b2e14e21cddf453b0fa06")

      gray.channels shouldBe 1
      gray.dataType shouldBe Types.Cv8U
      gray.matType shouldBe MatTypes.Cv8UC1

      val bwhash = gray.rawValues.foldLeft(Hashing.sha256().newHasher())(_ putInt _).hash()
      bwhash shouldBe HashCode.fromString("2b13ab05bf81b9ae1e8e2b6e22088b53608bda7e95ae088be60f7be0d8298dd7")
    }

    "support applying a color map" in {
      val gray = Image(30,30,1)
      gray.circle(Point(15,15),5,Scalar.Gray,-1,LineTypes.Line4,shift = 0)
      val bwhash = gray.rawValues.foldLeft(Hashing.sha256().newHasher())(_ putInt _).hash()
      bwhash shouldBe HashCode.fromString("b820e95d9d6f63000ece56442b5303f37146dd96e05d456e0e536cf6631cf098")

      val withColors = gray.applyColorMap(ColorMaps.Hot)
      val hash = withColors.rawValues.foldLeft(Hashing.sha256().newHasher())(_ putInt _).hash()
      hash shouldBe HashCode.fromString("d70a195c77d373db0bb9396e9de422c6fcdf58e5e8a8b4121a2977fde9c76f11")

      withColors.channels shouldBe 3
      withColors.dataType shouldBe Types.Cv8U
      withColors.matType shouldBe MatTypes.Cv8UC3
    }
  }

}
