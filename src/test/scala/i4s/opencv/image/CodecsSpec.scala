package i4s.opencv.image

import com.google.common.hash.{HashCode, Hashing}
import i4s.opencv.core.types.{MatTypes, Types}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.File

class CodecsSpec extends AnyWordSpec with Matchers {

  val jpgHash = HashCode.fromString("f476b30f035da88804e3b9d8a3d1ac110a42e08dadcc50d11ab7a4228c81904d")

  "Codecs" should {
    import i4s.opencv.image.Codecs._

    "reading a jpg" in {
      val testFile = new File(this.getClass.getClassLoader.getResource("shapes_and_colors.jpeg").getFile)
      val maybeImage = Codecs.readImage(testFile)
      maybeImage shouldBe defined
      val image = maybeImage.get

      image.dims() shouldBe 2
      image.dataType shouldBe Types.Cv8U
      image.matType shouldBe MatTypes.Cv8UC3
      image.channels shouldBe 3

      val hash = image.rawValues.foldLeft(Hashing.sha256().newHasher())(_.putInt(_)).hash()
      hash shouldBe jpgHash
    }

    "write & read a (lossless) png" in {
      val testFile = new File(this.getClass.getClassLoader.getResource("shapes_and_colors.jpeg").getFile)
      val maybeImage = Codecs.readImage(testFile)
      maybeImage shouldBe defined
      val jpg = maybeImage.get

      val tempFile = File.createTempFile("test-image",".png")
      tempFile.deleteOnExit()

      jpg.write(tempFile)
      tempFile.exists() shouldBe true

      val png = Codecs.readImage(tempFile).get

      val hash = png.rawValues.foldLeft(Hashing.sha256().newHasher())(_.putInt(_)).hash()
      hash shouldBe jpgHash
    }
  }

}
