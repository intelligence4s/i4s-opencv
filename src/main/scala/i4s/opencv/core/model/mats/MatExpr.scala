package i4s.opencv.core.model.mats
import i4s.opencv.core.model.{Rect, Size}
import i4s.opencv.core.types.MatTypes
import i4s.opencv.core.types.MatTypes.MatType
import org.bytedeco.opencv.opencv_core
import org.bytedeco.opencv.opencv_core.{MatOp, Scalar}

import scala.reflect.ClassTag

class MatExpr[T <: AnyVal : ClassTag](wrapped: org.bytedeco.opencv.opencv_core.MatExpr)(implicit matable: Matable[T])
  extends org.bytedeco.opencv.opencv_core.MatExpr(wrapped.getPointer(0)) with MatExprExpressions[T]
{
  override def asMat(): Mat[T] = new Mat[T](super.asMat())

  override def size(): Size = Size(super.size())

  def matType(): MatType =  MatTypes(super.`type`())

  override def row(y: Int): MatExpr[T] = super.row(y)

  override def col(x: Int): MatExpr[T] = super.col(x)

  override def diag(d: Int): MatExpr[T] = super.diag(d)

  override def diag(): MatExpr[T] = super.diag()

  def apply(rows: Range, cols: Range): MatExpr[T] =
    super.apply(new opencv_core.Range(rows.head,rows.end), new opencv_core.Range(cols.head,cols.end))

  def apply(roi: Rect): MatExpr[T] = super.apply(roi)

  override def t(): MatExpr[T] = super.t()

  override def inv(method: Int): MatExpr[T] = super.inv(method)

  override def inv(): MatExpr[T] = super.inv()

  override def mul(e: opencv_core.MatExpr, scale: Double): MatExpr[T] = super.mul(e, scale)

  override def mul(e: opencv_core.MatExpr): MatExpr[T] = super.mul(e)

  override def mul(m: opencv_core.Mat, scale: Double): MatExpr[T] = super.mul(m, scale)

  override def mul(m: opencv_core.Mat): MatExpr[T] = super.mul(m)

  override def cross(m: opencv_core.Mat): Mat[T] = super.cross(m)

  override def op(): MatOp = super.op()

  override def op(setter: MatOp): MatExpr[T] = super.op(setter)

  override def flags(): Int = super.flags()

  override def flags(setter: Int): MatExpr[T] = super.flags(setter)

  override def a(): opencv_core.Mat = super.a()

  override def a(setter: opencv_core.Mat): MatExpr[T] = super.a(setter)

  override def b(): opencv_core.Mat = super.b()

  override def b(setter: opencv_core.Mat): MatExpr[T] = super.b(setter)

  override def c(): opencv_core.Mat = super.c()

  override def c(setter: opencv_core.Mat): MatExpr[T] = super.c(setter)

  override def alpha(): Double = super.alpha()

  override def alpha(setter: Double): MatExpr[T] = super.alpha(setter)

  override def beta(): Double = super.beta()

  override def beta(setter: Double): MatExpr[T] = super.beta(setter)

  override def s(): Scalar = super.s()

  override def s(setter: Scalar): MatExpr[T] = super.s(setter)

  import scala.language.implicitConversions
  implicit def fromNativeMatExpr(native: org.bytedeco.opencv.opencv_core.MatExpr): MatExpr[T] = new MatExpr[T](native)
  implicit def fromNativeMat(native: org.bytedeco.opencv.opencv_core.Mat): Mat[T] = new Mat[T](native)

}
