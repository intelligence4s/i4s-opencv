package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Scalar
import org.bytedeco.opencv.global.opencv_core

import scala.reflect.ClassTag

trait MatExpressions[T <: AnyVal] {
  self: Mat[T] =>

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

  def multiply(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other.v0))

  def multiply(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def *(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def *(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def *(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self, other.v0))

  def *(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.multiply(self,other))

  def divide(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def divide(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def divide(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other.v0))

  def divide(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def /(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def /(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))

  def /(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self, other.v0))

  def /(other: MatExpr[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.divide(self,other))
    
  def lessThan(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThan(self,other))

  def lessThan(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThan(self,other))

  def <(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThan(self,other))

  def <(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThan(self,other))

  def lessThanEquals(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThanEquals(self,other))

  def lessThanEquals(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThanEquals(self,other))

  def <=(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThanEquals(self,other))

  def <=(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.lessThanEquals(self,other))

  def greaterThan(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThan(self,other))

  def greaterThan(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThan(self,other))

  def >(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThan(self,other))

  def >(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThan(self,other))

  def greaterThanEquals(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThanEquals(self,other))

  def greaterThanEquals(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThanEquals(self,other))

  def >=(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThanEquals(self,other))

  def >=(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.greaterThanEquals(self,other))
  
  def equals(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.equals(self, other))

  def equals(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.equals(self, other))

  def ==(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.equals(self, other))

  def ==(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.equals(self, other))

  def notEquals(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.notEquals(self, other))

  def notEquals(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.notEquals(self, other))

  def !=(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.notEquals(self, other))

  def !=(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.notEquals(self, other))

  def and(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.and(self, other))

  def and(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.and(self, other))

  def &(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.and(self, other))

  def &(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.and(self, other))

  def or(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.or(self, other))

  def or(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.or(self, other))

  def |(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.or(self, other))

  def |(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.or(self, other))

  def xor(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.xor(self, other))

  def xor(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.xor(self, other))

  def ^(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.xor(self, other))

  def ^(other: Scalar)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.xor(self, other))

  def not(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.not(self))

  def ~(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.not(self))

  def min(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.min(self, other))

  def min(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.min(self, other))

  def max(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.max(self, other))

  def max(other: Double)(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.max(self, other))

  def abs(implicit matable: Matable[T], tag: ClassTag[T]): MatExpr[T] =
    new MatExpr(opencv_core.abs(self))

}
