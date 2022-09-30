package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Scalar
import org.bytedeco.opencv.global.opencv_core

import scala.reflect.ClassTag

trait MatExprExpressions[T <: AnyVal] {
  self: MatExpr[T] =>

  def add(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def add(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def add(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def +(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def +(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def +(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def subtract(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def subtract(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def subtract(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def -(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def -(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def -(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def multiply(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def multiply(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def multiply(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def *(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def *(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def *(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def divide(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def divide(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def divide(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def /(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def /(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def /(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def abs(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.abs(self))

}
