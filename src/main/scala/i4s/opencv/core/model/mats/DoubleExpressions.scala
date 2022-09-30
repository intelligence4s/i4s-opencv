package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Scalar
import org.bytedeco.opencv.global.opencv_core

import scala.reflect.ClassTag

trait DoubleExpressions {
  val self: Double

  def add[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(Scalar(self), other))

  def add[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(Scalar(self), other))

  def +[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(Scalar(self), other))

  def +[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(Scalar(self), other))
  
  def subtract[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(Scalar(self), other))

  def subtract[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(Scalar(self), other))

  def -[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(Scalar(self), other))

  def -[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(Scalar(self), other))
  
  def multiply[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def multiply[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def *[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def *[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def divide[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def divide[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def /[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def /[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))
    
  def lessThan[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThan(self,other))

  def <[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThan(self,other))

  def lessThanEquals[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThanEquals(self,other))

  def <=[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThanEquals(self,other))

  def greaterThan[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThan(self,other))

  def >[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThan(self,other))

  def greaterThanEquals[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThanEquals(self,other))

  def >=[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThanEquals(self,other))

  def equals[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.equals(self, other))

  def ==[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.equals(self, other))

  def notEquals[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.notEquals(self, other))

  def !=[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.notEquals(self, other))
}
