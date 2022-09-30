package i4s.opencv.core.model.mats

import i4s.opencv.core.constants.AccessFlags.AccessFlag
import i4s.opencv.core.constants.DecompositionMethods.DecompositionMethod
import i4s.opencv.core.model.{Scalar, Size}
import i4s.opencv.core.types.MatTypes
import i4s.opencv.core.types.Types.Type
import org.bytedeco.opencv.opencv_core
import org.bytedeco.opencv.opencv_core.{GpuMat, UMat}

import scala.annotation.tailrec
import scala.reflect.ClassTag

object Mat {
  def apply[T <: AnyVal](rows: Int)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat(Array(rows),MatTypes.makeType(matable.depth,matable.channels)))

  def apply[T <: AnyVal](rows: Int, init: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat(Array(rows),MatTypes.makeType(matable.depth,matable.channels),init))

  def apply[T <: AnyVal](rows: Int, depth: Option[Type], ch: Option[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat(Array(rows),MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))

  def apply[T <: AnyVal](rows: Int, depth: Option[Type], ch: Option[Int], init: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat(Array(rows),MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels)),init))

  def apply[T <: AnyVal](rows: Int, cols: Int)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat(Array(rows,cols),MatTypes.makeType(matable.depth,matable.channels)))

  def apply[T <: AnyVal](rows: Int, cols: Int, init: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat(Array(rows,cols),MatTypes.makeType(matable.depth,matable.channels),init))

  def apply[T <: AnyVal](rows: Int, cols: Int, depth: Option[Type], ch: Option[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat(Array(rows,cols),MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))

  def apply[T <: AnyVal](rows: Int, cols: Int, depth: Option[Type], ch: Option[Int], init: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat(Array(rows,cols),MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels)),init))

  def apply[T <: AnyVal](dim1: Int, dims: Int*)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat((dim1 +: dims).toArray,MatTypes.makeType(matable.depth,matable.channels)))

  def apply[T <: AnyVal](init: Scalar,dim1: Int, dims: Int*)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat((dim1 +: dims).toArray,MatTypes.makeType(matable.depth,matable.channels),init))

  def apply[T <: AnyVal](depth: Option[Type], ch: Option[Int], dim1: Int, dims: Int*)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat((dim1 +: dims).toArray,MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))

  def apply[T <: AnyVal](depth: Option[Type], ch: Option[Int], init: Scalar, dim1: Int, dims: Int*)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](new org.bytedeco.opencv.opencv_core.Mat((dim1 +: dims).toArray,MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels)),init))

  def apply[T <: AnyVal](wrapped: org.bytedeco.opencv.opencv_core.Mat)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](wrapped.clone)

  def apply[T <: AnyVal](wrapped: org.bytedeco.opencv.opencv_core.UMat, accessFlag: AccessFlag)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    new Mat[T](wrapped.getMat(accessFlag.id).clone)

  def zeros[T <: AnyVal](size: Size)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.zeros(size,MatTypes.makeType(matable.depth,matable.channels)))

  def zeros[T <: AnyVal](rows: Int, cols: Int)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.zeros(rows,cols,MatTypes.makeType(matable.depth,matable.channels)))

  def zeros[T <: AnyVal](depth: Option[Type], ch: Option[Int], size: Size)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.zeros(size,MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))

  def zeros[T <: AnyVal](depth: Option[Type], ch: Option[Int], rows: Int, cols: Int)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.zeros(rows,cols,MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))

  def ones[T <: AnyVal](size: Size)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
  new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.ones(size,MatTypes.makeType(matable.depth,matable.channels)))

  def ones[T <: AnyVal](rows: Int, cols: Int)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
  new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.ones(rows,cols,MatTypes.makeType(matable.depth,matable.channels)))

  def ones[T <: AnyVal](depth: Option[Type], ch: Option[Int], size: Size)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
  new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.ones(size,MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))

  def ones[T <: AnyVal](depth: Option[Type], ch: Option[Int], rows: Int, cols: Int)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
  new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.ones(rows,cols,MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))

  def eye[T <: AnyVal](size: Size)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.eye(size,MatTypes.makeType(matable.depth,matable.channels)))

  def eye[T <: AnyVal](rows: Int, cols: Int)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.eye(rows,cols,MatTypes.makeType(matable.depth,matable.channels)))

  def eye[T <: AnyVal](depth: Option[Type], ch: Option[Int], size: Size)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.eye(size,MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))

  def eye[T <: AnyVal](depth: Option[Type], ch: Option[Int], rows: Int, cols: Int)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr[T](org.bytedeco.opencv.opencv_core.Mat.eye(rows,cols,MatTypes.makeType(depth.getOrElse(matable.depth),ch.getOrElse(matable.channels))))
}

class Mat[T <: AnyVal : ClassTag](wrapped: org.bytedeco.opencv.opencv_core.Mat)(implicit matable: Matable[T])
  extends BaseMat[T](wrapped) with MatMath[T] with MatExpressions[T]
{
  def get(i: Int): T = matable.get(this,i)
  def get(i: Int, is: Int*): T = matable.get(this, i +: is:_*)

  def getN(n: Int, i: Int): IndexedSeq[T] = matable.getN(this,Array(i),n)
  def getN(n: Int, i: Int, is: Int*): IndexedSeq[T] = matable.getN(this,i +: is,n)

  def values: i4s.compat.LazyList[T] = valueStream

  def put(i: Int, value: T): Unit = matable.put(this,Array(i),value)
  def put(i: Int, j: Int, value: T): Unit = matable.put(this,Array(i,j),value)
  def put(i: Int, j: Int, k: Int, value: T): Unit = matable.put(this,Array(i,j,k),value)
  def put(indices: IndexedSeq[Int], value: T): Unit = matable.put(this,indices,value)

  def put(values: Seq[T]): Unit = matable.putN(this,Array(0),values)
  def put(i: Int, values: Seq[T]): Unit = matable.putN(this,Array(i),values)
  def put(i: Int, j: Int, values: Seq[T]): Unit = matable.putN(this,Array(i,j),values)
  def put(indices: IndexedSeq[Int], values: Seq[T]): Unit = matable.putN(this,indices,values)

  def putT1(values: (Int, T)*): Unit = values.foreach { case (i, v) => put(i, v) }
  def putT2(values: (Int, Int, T)*): Unit = values.foreach { case (i, j, v) => put(i, j, v) }
  def putT3(values: (Int, Int, Int, T)*): Unit = values.foreach { case (i, j, k, v) => put(i, j, k, v) }

  override def t: MatExpr[T] = super.t()
  def inv(method: DecompositionMethod): MatExpr[T] = super.inv(method.id)
  override def inv: MatExpr[T] = super.inv()
  override def mul(m: opencv_core.Mat, scale: Double): MatExpr[T] = super.mul(m, scale)
  override def mul(m: opencv_core.Mat): MatExpr[T] = super.mul(m)

  import scala.language.implicitConversions
  implicit def fromNativeMatExpr(native: org.bytedeco.opencv.opencv_core.MatExpr): MatExpr[T] = new MatExpr[T](native)
  implicit def fromNativeMat(native: org.bytedeco.opencv.opencv_core.Mat): Mat[T] = new Mat[T](native)

}
