package i4s.opencv.core.model.mats

import i4s.opencv.core.model.{Scalar, mats}
import i4s.opencv.core.types.{MatTypes, Types}
import org.bytedeco.javacpp.indexer.{DoubleIndexer, FloatIndexer, UByteIndexer}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatMathSpec extends AnyWordSpec with Matchers {
  "Mat" should {
    import i4s.opencv.core.model.mats.syntax._

    "support adding matrices" in {
      val matOfFives = Mat[Int](3,20)
      val fives = Array.fill(60)(5)
      matOfFives.put(0,fives.toSeq)

      val matOfTens = Mat[Int](3,20)
      val tens = Array.fill(60)(10)
      matOfTens.put(0,tens.toSeq)

      (matOfTens + matOfFives).values.foreach(_ shouldBe 15)
      matOfFives.add(matOfTens).values.foreach(_ shouldBe 15)
    }
  }


}
