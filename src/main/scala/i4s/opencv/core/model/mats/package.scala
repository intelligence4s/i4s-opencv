package i4s.opencv.core.model
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type
import org.bytedeco.opencv.global.opencv_core

import scala.reflect.ClassTag

package object mats {
  object syntax {
    import scala.language.implicitConversions
    implicit def matExpr2mat[T <: AnyVal](matExpr: MatExpr[T]): Mat[T] = matExpr.asMat()
    implicit def double2Scalar(d: Double): Scalar = Scalar(d)

    implicit class ScalarExprSupport(val self: Scalar) extends ScalarExpressions
    implicit class DoubleExprSupport(val self: Double) extends DoubleExpressions

    def min[T <: AnyVal](left: Mat[T], right: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
      new MatExpr(opencv_core.min(left, right))

    def min[T <: AnyVal](left: Mat[T], right: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
      new MatExpr(opencv_core.min(left,right))

    def min[T <: AnyVal](left: Double, right: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
      new MatExpr(opencv_core.max(left,right))

    def max[T <: AnyVal](left: Mat[T], right: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
      new MatExpr(opencv_core.max(left, right))

    def max[T <: AnyVal](left: Mat[T], right: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
      new MatExpr(opencv_core.max(left,right))

    def max[T <: AnyVal](left: Double, right: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
      new MatExpr(opencv_core.max(left,right))

    def abs[T <: AnyVal](self: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
      new MatExpr(opencv_core.abs(self))

    def abs[T <: AnyVal](self: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
      new MatExpr(opencv_core.abs(self))

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

    implicit val bytePoint2fMatable: Point2fMatable[Byte] = new Point2fMatable[Byte]
    implicit val shortPoint2fMatable: Point2fMatable[Short] = new Point2fMatable[Short]
    implicit val doublePoint2fMatable: Point2fMatable[Double] = new Point2fMatable[Double]
    implicit val floatPoint2fMatable: Point2fMatable[Float] = new Point2fMatable[Float]
    implicit val intPoint2fMatable: Point2fMatable[Int] = new Point2fMatable[Int]

    implicit val bytePoint3fMatable: Point3fMatable[Byte] = new Point3fMatable[Byte]
    implicit val shortPoint3fMatable: Point3fMatable[Short] = new Point3fMatable[Short]
    implicit val doublePoint3fMatable: Point3fMatable[Double] = new Point3fMatable[Double]
    implicit val floatPoint3fMatable: Point3fMatable[Float] = new Point3fMatable[Float]
    implicit val intPoint3fMatable: Point3fMatable[Int] = new Point3fMatable[Int]

    implicit val bytePoint3dMatable: Point3dMatable[Byte] = new Point3dMatable[Byte]
    implicit val shortPoint3dMatable: Point3dMatable[Short] = new Point3dMatable[Short]
    implicit val doublePoint3dMatable: Point3dMatable[Double] = new Point3dMatable[Double]
    implicit val floatPoint3dMatable: Point3dMatable[Float] = new Point3dMatable[Float]
    implicit val intPoint3dMatable: Point3dMatable[Int] = new Point3dMatable[Int]

    implicit val bytePointMatable: PointMatable[Byte] = new PointMatable[Byte]
    implicit val shortPointMatable: PointMatable[Short] = new PointMatable[Short]
    implicit val doublePointMatable: PointMatable[Double] = new PointMatable[Double]
    implicit val floatPointMatable: PointMatable[Float] = new PointMatable[Float]
    implicit val intPointMatable: PointMatable[Int] = new PointMatable[Int]

    implicit val byteSeqMatable: SeqMatable[Byte] = new SeqMatable[Byte]
    implicit val shortSeqMatable: SeqMatable[Short] = new SeqMatable[Short]
    implicit val doubleSeqMatable: SeqMatable[Double] = new SeqMatable[Double]
    implicit val floatSeqMatable: SeqMatable[Float] = new SeqMatable[Float]
    implicit val intSeqMatable: SeqMatable[Int] = new SeqMatable[Int]

    implicit val byteIndexedSeqMatable: IndexedSeqMatable[Byte] = new IndexedSeqMatable[Byte]
    implicit val shortIndexedSeqMatable: IndexedSeqMatable[Short] = new IndexedSeqMatable[Short]
    implicit val doubleIndexedSeqMatable: IndexedSeqMatable[Double] = new IndexedSeqMatable[Double]
    implicit val floatIndexedSeqMatable: IndexedSeqMatable[Float] = new IndexedSeqMatable[Float]
    implicit val intIndexedSeqMatable: IndexedSeqMatable[Int] = new IndexedSeqMatable[Int]
  }

}
