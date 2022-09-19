package i4s.opencv.core.model

import i4s.opencv.core.model.Math.NumberLike

import scala.reflect.ClassTag

trait ScalarLike[T <: AnyVal] {
  protected val v0, v1, v2, v3: T

  def construct(v0: T, v1: T, v2: T, v3: T): ScalarLike[T]

  def +(p: ScalarLike[T])(implicit ev: NumberLike[T]): ScalarLike[T] = construct(ev.plus(v0, p.v0),ev.plus(v1, p.v1),ev.plus(v2, p.v2),ev.plus(v3, p.v3))
  def -(p: ScalarLike[T])(implicit ev: NumberLike[T]): ScalarLike[T] = construct(ev.minus(v0, p.v0),ev.minus(v1, p.v1),ev.minus(v2, p.v2),ev.minus(v3, p.v3))
  def *(p: ScalarLike[T])(implicit ev: NumberLike[T]): ScalarLike[T] = construct(ev.multiply(v0, p.v0),ev.multiply(v1, p.v1),ev.multiply(v2, p.v2),ev.multiply(v3, p.v3))
  def /(p: ScalarLike[T])(implicit ev: NumberLike[T]): ScalarLike[T] = construct(ev.divide(v0, p.v0),ev.divide(v1, p.v1),ev.divide(v2, p.v2),ev.divide(v3, p.v3))
  def *(a: T)(implicit ev: NumberLike[T]): ScalarLike[T] = construct(ev.multiply(v0, a),ev.multiply(v1, a),ev.multiply(v2, a),ev.multiply(v3, a))
  def /(a: T)(implicit ev: NumberLike[T]): ScalarLike[T] = construct(ev.divide(v0, a),ev.divide(v1, a),ev.divide(v2, a),ev.divide(v3, a))

  def asArray(implicit classTag: ClassTag[T]): Array[T] = Array(v0, v1, v2, v3)

  def canEqual(that: Any): Boolean = that.isInstanceOf[ScalarLike[T]]

  override def equals(that: Any): Boolean = that match {
    case that: ScalarLike[T] => that.canEqual(this) && this.v0 == that.v0 && this.v1 == that.v1 && this.v2 == that.v2 && this.v3 == that.v3
    case _ => false
  }

  override def hashCode(): Int = 1 << 8 * v0.hashCode() + 1 << 16 * v1.hashCode() + 1 << 24 * v2.hashCode() + 1 << 32 * v3.hashCode()

  override def toString: String = s"($v0,$v1,$v2,$v3)"

}
