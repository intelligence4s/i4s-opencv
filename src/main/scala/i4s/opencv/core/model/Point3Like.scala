package i4s.opencv.core.model

import i4s.opencv.core.model.Math.NumberLike

import scala.reflect.ClassTag

trait Point3Like[T <: AnyVal] {
  val x: T
  val y: T
  val z: T

  def construct(x: T, y: T, z: T): Point3Like[T]

  def +(p: Point3Like[T])(implicit ev: NumberLike[T]): Point3Like[T] = construct(ev.plus(x,p.x),ev.plus(y,p.y),ev.plus(z,p.z))
  def -(p: Point3Like[T])(implicit ev: NumberLike[T]): Point3Like[T] = construct(ev.minus(x,p.x),ev.minus(y,p.y),ev.minus(z,p.z))
  def *(p: Point3Like[T])(implicit ev: NumberLike[T]): Point3Like[T] = construct(ev.multiply(x,p.x),ev.multiply(y,p.y),ev.multiply(z,p.z))
  def /(p: Point3Like[T])(implicit ev: NumberLike[T]): Point3Like[T] = construct(ev.divide(x,p.x),ev.divide(y,p.y),ev.divide(z,p.z))
  def *(a: T)(implicit ev: NumberLike[T]): Point3Like[T] = construct(ev.multiply(x,a),ev.multiply(y,a),ev.multiply(z,a))
  def /(a: T)(implicit ev: NumberLike[T]): Point3Like[T] = construct(ev.divide(x,a),ev.divide(y,a),ev.divide(z,a))

  def asArray(implicit classTag: ClassTag[T]): Array[T] = Array(x,y,z)

  def canEqual(that: Any): Boolean = that.isInstanceOf[Point3Like[T]]

  override def equals(that: Any): Boolean = that match {
    case that: Point3Like[T] => that.canEqual(this) && this.x == that.x && this.y == that.y
    case _ => false
  }

  override def hashCode(): Int = 1 << 32 * x.hashCode() + 1 << 16 *y.hashCode() + z.hashCode()

  override def toString: String = s"($x,$y,$z)"

}
