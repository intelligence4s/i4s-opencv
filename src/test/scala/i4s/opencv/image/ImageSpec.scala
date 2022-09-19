package i4s.opencv.image
import i4s.opencv.core.model.Scalar
import i4s.opencv.core.types.{MatTypes, Types}
import org.bytedeco.javacpp.indexer.{DoubleIndexer, IntIndexer, UByteIndexer}
import org.bytedeco.opencv.opencv_core.Mat
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class ImageSpec extends AnyWordSpec with Matchers {
  "Image" should {
    "wrap a compatible Mat with the constructor" in {
      val mat = new Mat(150,150,MatTypes.makeType(Types.Cv8U,3),Scalar.Red)
      val image = new Image(mat)
      image.get(0,0) shouldBe Scalar.Red
      image.get(149, 149) shouldBe Scalar.Red

      val indexer = mat.createIndexer().asInstanceOf[UByteIndexer]
      indexer.put(0L,0L,0L,Scalar.Blue.v0.toInt)
      indexer.put(0L,0L,1L,Scalar.Blue.v1.toInt)
      indexer.put(0L,0L,2L,Scalar.Blue.v2.toInt)

      image.get(0,0) shouldBe Scalar.Blue
    }

    "create a copy of a compatible Mat with the companion object factory" in {
      val mat = new Mat(150, 150, MatTypes.makeType(Types.Cv8U, 3), Scalar.Red)

      val image = Image(mat)
      image.get(0, 0) shouldBe Scalar.Red
      image.get(149, 149) shouldBe Scalar.Red

      val indexer = mat.createIndexer().asInstanceOf[UByteIndexer]
      indexer.put(0L, 0L, 0L, Scalar.Blue.v0.toInt)
      indexer.put(0L, 0L, 1L, Scalar.Blue.v1.toInt)
      indexer.put(0L, 0L, 2L, Scalar.Blue.v2.toInt)

      image.get(0, 0) shouldBe Scalar.Red
    }

    "create a copy of an image with the copy constructor" in {
      val image = Image(150,150,3,Scalar.Blue)

      val copy = Image(image)
      copy.put(Scalar.White)

      image.get(0,0) shouldBe Scalar.Blue
      image.get(149,149) shouldBe Scalar.Blue

      copy.get(0,0) shouldBe Scalar.White
      copy.get(149,149) shouldBe Scalar.White
    }

    "transparently wrap a Mat to an Image" in {
      val mat = new Mat(150, 150, MatTypes.makeType(Types.Cv8U, 3), Scalar.Red)

      val image: Image = mat
      image.get(0,0) shouldBe Scalar.Red
    }

    "throw an exception for an incompatible Mat" in {
      val mat = new Mat(150,150,MatTypes.makeType(Types.Cv8S,3),Scalar.Red)
      assertThrows[AssertionError] {
        Image(mat)
      }
    }

    "support a stream of values" in {
      val image = Image(10,10,3,Scalar.Blue)
    }
  }

}
