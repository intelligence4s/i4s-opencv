package i4s.opencv.core.model

import i4s.opencv.core.model.Math.NumberLike

import scala.reflect.ClassTag

trait SizeLike[T <: AnyVal] {
  val width: T
  val height: T

  def construct(width: T, height: T): SizeLike[T]

  def +(p: SizeLike[T])(implicit ev: NumberLike[T]): SizeLike[T] = construct(ev.plus(width,p.width),ev.plus(height,p.height))

  def -(p: SizeLike[T])(implicit ev: NumberLike[T]): SizeLike[T] = construct(ev.minus(width,p.width),ev.minus(height,p.height))

  def *(p: SizeLike[T])(implicit ev: NumberLike[T]): SizeLike[T] = construct(ev.multiply(width,p.width),ev.multiply(height,p.height))

  def /(p: SizeLike[T])(implicit ev: NumberLike[T]): SizeLike[T] = construct(ev.divide(width,p.width),ev.divide(height,p.height))

  def *(a: T)(implicit ev: NumberLike[T]): SizeLike[T] = construct(ev.multiply(width,a),ev.multiply(height,a))

  def /(a: T)(implicit ev: NumberLike[T]): SizeLike[T] = construct(ev.divide(width,a),ev.divide(height,a))

  def asArray(implicit classTag: ClassTag[T]): Array[T] = Array(width,height)

  def canEqual(that: Any): Boolean = that.isInstanceOf[SizeLike[T]]

  override def equals(that: Any): Boolean = that match {
    case that: SizeLike[T] => that.canEqual(this) && this.width == that.width && this.height == that.height
    case _ => false
  }

  override def hashCode(): Int = 1 << 32 * width.hashCode() + height.hashCode()

  override def toString: String = s"[${width}x${height}]"

}
