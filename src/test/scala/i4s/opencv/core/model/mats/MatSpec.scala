package i4s.opencv.core.model.mats

import i4s.opencv.core.model.{Scalar, mats}
import i4s.opencv.core.types.{MatTypes, Types}
import org.bytedeco.javacpp.indexer.{DoubleIndexer, FloatIndexer, UByteIndexer}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatSpec extends AnyWordSpec with Matchers {
  "Mat" should {
    import i4s.opencv.core.model.mats.syntax._

    "wrap native Mat of UBytes with Mat[Int] constructor" in {
      val matOfPixels = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv8U, 3), Scalar.Red)
      val pixels = new Mat[Int](matOfPixels)
      pixels.get(0) shouldBe Scalar.Red.v0
      val indexer = matOfPixels.createIndexer().asInstanceOf[UByteIndexer]
      indexer.put(0L, 128)
      pixels.get(0) shouldBe 128
    }

    "wrap native 1 dim Mat of Doubles with Mat[Double] constructor" in {
      val arrayOfDoubles = new org.bytedeco.opencv.opencv_core.Mat(Array(150), MatTypes.makeType(Types.Cv64F, 1), Scalar.White)
      val doubleArray = new Mat[Double](arrayOfDoubles)
      doubleArray.get(0) shouldBe Scalar.White.v0
      val indexer = arrayOfDoubles.createIndexer().asInstanceOf[DoubleIndexer]
      indexer.put(0L, 128d)
      doubleArray.get(0) shouldBe 128
    }

    "wrap native 2 dim Mat of Doubles with Mat[Double] constructor" in {
      val matOfDoubles = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv64F, 1), Scalar.White)
      val doubles = new Mat[Double](matOfDoubles)
      doubles.get(0, 0) shouldBe Scalar.White.v0
      val indexer = matOfDoubles.createIndexer().asInstanceOf[DoubleIndexer]
      indexer.put(0L, 0L, 128d)
      doubles.get(0, 0) shouldBe 128
    }

    "wrap native 1 dim Mat of Float with Mat[Float] constructor" in {
      val arrayOfFloats = new org.bytedeco.opencv.opencv_core.Mat(Array(150), MatTypes.makeType(Types.Cv32F, 1), Scalar.White)
      val floatArray = new Mat[Float](arrayOfFloats)
      floatArray.get(0) shouldBe Scalar.White.v0
      val indexer = arrayOfFloats.createIndexer().asInstanceOf[FloatIndexer]
      indexer.put(0L,128f)
      floatArray.get(0) shouldBe 128
    }

    "wrap native 2 dim Mat of Float with Mat[Float] constructor" in {
      val matOfFloats = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv32F, 1), Scalar.White)
      val floats = new Mat[Float](matOfFloats)
      floats.get(0,0) shouldBe Scalar.White.v0
      val indexer = matOfFloats.createIndexer().asInstanceOf[FloatIndexer]
      indexer.put(0L, 0L, 128f)
      floats.get(0, 0) shouldBe 128
    }

    "copy native Mat of UBytes with Mat factory" in {
      val matOfPixels = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv8U, 3), Scalar.Red)
      val pixels = Mat[Int](matOfPixels)
      pixels.get(0) shouldBe Scalar.Red.v0
      val indexer = matOfPixels.createIndexer().asInstanceOf[UByteIndexer]
      indexer.put(0L, 128)
      pixels.get(0) shouldBe Scalar.Red.v0
    }

    "copy native 1 dim Mat of Doubles with Mat factory" in {
      val arrayOfDoubles = new org.bytedeco.opencv.opencv_core.Mat(Array(150), MatTypes.makeType(Types.Cv64F, 1), Scalar.White)
      val doubleArray = Mat[Double](arrayOfDoubles)
      doubleArray.get(0) shouldBe Scalar.White.v0
      val indexer = arrayOfDoubles.createIndexer().asInstanceOf[DoubleIndexer]
      indexer.put(0L, 128d)
      doubleArray.get(0) shouldBe Scalar.White.v0
    }

    "copy native 2 dim Mat of Doubles with Mat factory" in {
      val matOfDoubles = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv64F, 1), Scalar.White)
      val doubles = Mat[Double](matOfDoubles)
      doubles.get(0, 0) shouldBe Scalar.White.v0
      val indexer = matOfDoubles.createIndexer().asInstanceOf[DoubleIndexer]
      indexer.put(0L, 0L, 128d)
      doubles.get(0, 0) shouldBe Scalar.White.v0
    }

    "copy native 1 dim Mat of Float with Mat factory" in {
      val arrayOfFloats = new org.bytedeco.opencv.opencv_core.Mat(Array(150), MatTypes.makeType(Types.Cv32F, 1), Scalar.White)
      val floatArray = Mat[Float](arrayOfFloats)
      floatArray.get(0) shouldBe Scalar.White.v0
      val indexer = arrayOfFloats.createIndexer().asInstanceOf[FloatIndexer]
      indexer.put(0L,128f)
      floatArray.get(0) shouldBe Scalar.White.v0
    }

    "copy native 2 dim Mat of Float with Mat factory" in {
      val matOfFloats = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv32F, 1), Scalar.White)
      val floats = new Mat[Float](matOfFloats)
      floats.get(0,0) shouldBe Scalar.White.v0
      val indexer = matOfFloats.createIndexer().asInstanceOf[FloatIndexer]
      indexer.put(0L, 0L, 128f)
      floats.get(0, 0) shouldBe 128
    }

    "throw an when wrapping a native Mat with an incompatible type" in {
      val wrapped = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv8U, 3), Scalar.Red)
      assertThrows[AssertionError](new Mat[Float](wrapped))
    }

    "throw an exception when copying a native Mat with an incompatible type" in {
      val wrapped = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv8U, 3), Scalar.Red)
      assertThrows[AssertionError](Mat[Float](wrapped))
    }

    "support a 1 dim array" in {
      val mat = mats.Mat[Double](10, Scalar(0,0,0,0))

      val values = (1 to 10).map(_.toDouble)
      mat.put(0, values)

      mat.dims shouldBe 2
      mat.shape shouldBe Seq(10,1)

      mat.total shouldBe 10
      mat.total(1) shouldBe 1

      mat.getN(n = 5, 5) shouldBe (6 to 10)
      mat.get(5) shouldBe 6

      assertThrows[IndexOutOfBoundsException] {
        mat.get(10)
      }
    }

    "support a 2 dim matrix" in {
      val mat = mats.Mat[Double](10,10, Scalar(0,0,0,0))

      val values = 1 to 100 map (_.toDouble)
      mat.put(0,values)

      mat.dims shouldBe 2
      mat.shape shouldBe Seq(10, 10)

      mat.total shouldBe 100
      mat.total(1) shouldBe 10

      mat.getN(n = 10, 1) shouldBe (11 to 20)
      mat.getN(n = 5, 9, 5) shouldBe (96 to 100)

      mat.get(5) shouldBe 51
      mat.getN(n = 5, 5) shouldBe (51 to 55)

      mat.get(5,5) shouldBe 56
      mat.getN(n = 5, 5, 5) shouldBe (56 to 60)
      mat.getN(n = 10, 5, 5) shouldBe (56 to 65)

      assertThrows[IndexOutOfBoundsException] {
        mat.get(10)
      }

      assertThrows[IndexOutOfBoundsException] {
        mat.get(9,10)
      }
    }

    "support a 3 dim matrix" in {
      val mat = mats.Mat[Double](Scalar(0,0,0,0),2,10,10)

      val value = 1 to 200 map (_.toDouble)
      mat.put(0, value)

      mat.dims shouldBe 3
      mat.shape shouldBe Seq(2,10,10)

      mat.total shouldBe 200
      mat.total(1) shouldBe 100
      mat.total(2) shouldBe 10

      mat.getN(10,1,5) shouldBe (151 to 160)
      mat.getN(10,1,9,0) shouldBe (191 to 200)

      mat.getN(n = 10, 1) shouldBe (101 to 110)
      mat.getN(n = 5, 0, 5) shouldBe (51 to 55)

      mat.get(1) shouldBe 101

      mat.get(1, 5) shouldBe 151
      mat.getN(n = 5, 1, 5, 5) shouldBe (156 to 160)
      mat.getN(n = 10, 1, 5, 5) shouldBe (156 to 165)

      mat.get(1,5,9) shouldBe 160

      assertThrows[IndexOutOfBoundsException](mat.get(2))
      assertThrows[IndexOutOfBoundsException](mat.get(1, 10))
      assertThrows[IndexOutOfBoundsException](println(mat.get(1,9,10)))
    }
  }
}
