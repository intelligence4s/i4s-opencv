package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Scalar
import org.bytedeco.opencv.global.opencv_core

import scala.reflect.ClassTag

trait ScalarExpressions {
  val self: Scalar

  def add[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def add[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def +[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def +[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.add(self,other))

  def subtract[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def subtract[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def -[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def -[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.subtract(self,other))

  def multiply[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self.v0,other))

  def multiply[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self.v0,other))

  def *[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self.v0,other))

  def *[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self.v0,other))

  def divide[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self.v0,other))

  def divide[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self.v0,other))

  def /[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self.v0,other))

  def /[T <: AnyVal](other: MatExpr[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self.v0,other))

  def and[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.and(self, other))

  def &[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.and(self, other))

  def or[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.or(self, other))

  def |[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.or(self, other))

  def xor[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.xor(self, other))

  def ^[T <: AnyVal](other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.xor(self, other))
}
