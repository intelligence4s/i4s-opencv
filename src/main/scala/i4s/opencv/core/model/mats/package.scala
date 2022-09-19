package i4s.opencv.core.model
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type

package object mats {
  object syntax {
    implicit val byteMatable: Matable[Byte] = new Matable[Byte] {
      override def depth: Type = Types.Cv8S
    }
    implicit val charMatable: Matable[Char] = new Matable[Char] {
      override def depth: Type = Types.Cv16S
    }
    implicit val shortMatable: Matable[Short] = new Matable[Short] {
      override def depth: Type = Types.Cv16S
    }
    implicit val doubleMatable: Matable[Double] = new Matable[Double] {
      override def depth: Type = Types.Cv64F
    }
    implicit val floatMatable: Matable[Float] = new Matable[Float] {
      override def depth: Type = Types.Cv32F
    }
    implicit val intMatable: Matable[Int] = new Matable[Int] {
      override def depth: Type = Types.Cv32S
    }

    implicit val byteScalarMatable: ScalarMatable[Byte] = new ScalarMatable[Byte]
    implicit val shortScalarMatable: ScalarMatable[Short] = new ScalarMatable[Short]
    implicit val doubleScalarMatable: ScalarMatable[Double] = new ScalarMatable[Double]
    implicit val floatScalarMatable: ScalarMatable[Float] = new ScalarMatable[Float]
    implicit val intScalarMatable: ScalarMatable[Int] = new ScalarMatable[Int]

    implicit val byteRectMatable: RectMatable[Byte] = new RectMatable[Byte]
    implicit val shortRectMatable: RectMatable[Short] = new RectMatable[Short]
    implicit val doubleRectMatable: RectMatable[Double] = new RectMatable[Double]
    implicit val floatRectMatable: RectMatable[Float] = new RectMatable[Float]
    implicit val intRectMatable: RectMatable[Int] = new RectMatable[Int]

    implicit val bytePointMatable: PointMatable[Byte] = new PointMatable[Byte]
    implicit val shortPointMatable: PointMatable[Short] = new PointMatable[Short]
    implicit val doublePointMatable: PointMatable[Double] = new PointMatable[Double]
    implicit val floatPointMatable: PointMatable[Float] = new PointMatable[Float]
    implicit val intPointMatable: PointMatable[Int] = new PointMatable[Int]
  }
}
