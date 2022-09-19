package i4s.opencv.core.model

import scala.annotation.implicitNotFound

object Math {
  @implicitNotFound("No member of type class NumberLike in scope for ${T}")
  trait NumberLike[T <: AnyVal] {
    def plus(x: T, y: T): T
    def minus(x: T, y: T): T
    def multiply(x: T, y: T): T
    def divide(x: T, y: T): T

    def power(x: T, y: T): T
    def root(x: T, Y: T): T

    def toDouble(x: T): Double
    def toLong(x: T): Long
    def toFloat(x: T): Float
    def toInt(x: T): Int
    def toShort(x: T): Short
    def toChar(x: T): Char
    def toByte(x: T): Byte

    def fromDouble(x: Double): T
    def fromLong(x: Long): T
    def fromFloat(x: Float): T
    def fromInt(x: Int): T
    def fromShort(x: Short): T
    def fromChar(x: Char): T
    def fromByte(x: Byte): T
  }

  object NumberLike {

    implicit object NumberLikeInt extends NumberLike[Int] {
      def plus(x: Int, y: Int): Int = x + y
      def minus(x: Int, y: Int): Int = x - y
      def multiply(x: Int, y: Int): Int = x * y
      def divide(x: Int, y: Int): Int = x / y
      def power(x: Int, y: Int): Int = scala.math.pow(x,y).toInt
      def root(x: Int, y: Int): Int = scala.math.pow(x,1/ y.toDouble).toInt

      override def toDouble(x: Int): Double = x.toDouble
      override def toLong(x: Int): Long = x.toLong
      override def toFloat(x: Int): Float = x.toFloat
      override def toInt(x: Int): Int = x
      override def toShort(x: Int): Short = x.toShort
      override def toChar(x: Int): Char = x.toChar
      override def toByte(x: Int): Byte = x.toByte

      override def fromDouble(x: Double): Int = x.toInt
      override def fromLong(x: Long): Int = x.toInt
      override def fromFloat(x: Float): Int = x.toInt
      override def fromInt(x: Int): Int = x
      override def fromShort(x: Short): Int = x.toInt
      override def fromChar(x: Char): Int = x.toInt
      override def fromByte(x: Byte): Int = x.toInt
    }

    implicit object NumberLikeDouble extends NumberLike[Double] {
      def plus(x: Double, y: Double): Double = x + y
      def minus(x: Double, y: Double): Double = x - y
      def multiply(x: Double, y: Double): Double = x * y
      def divide(x: Double, y: Double): Double = x / y
      def power(x: Double, y: Double): Double = scala.math.pow(x,y).toInt
      def root(x: Double, y: Double): Double = scala.math.pow(x,1/ y).toInt
      
      override def toDouble(x: Double): Double = x
      override def toLong(x: Double): Long = x.toLong
      override def toFloat(x: Double): Float = x.toFloat
      override def toInt(x: Double): Int = x.toInt
      override def toShort(x: Double): Short = x.toShort
      override def toChar(x: Double): Char = x.toChar
      override def toByte(x: Double): Byte = x.toByte

      override def fromDouble(x: Double): Double = x
      override def fromLong(x: Long): Double = x.toDouble
      override def fromFloat(x: Float): Double = x.toDouble
      override def fromInt(x: Int): Double = x.toDouble
      override def fromShort(x: Short): Double = x.toDouble
      override def fromChar(x: Char): Double = x.toDouble
      override def fromByte(x: Byte): Double = x.toDouble
    }

    implicit object NumberLikeFloat extends NumberLike[Float] {
      def plus(x: Float, y: Float): Float = x + y
      def minus(x: Float, y: Float): Float = x - y
      def multiply(x: Float, y: Float): Float = x * y
      def divide(x: Float, y: Float): Float = x / y
      def power(x: Float, y: Float): Float = scala.math.pow(x,y).toInt
      def root(x: Float, y: Float): Float = scala.math.pow(x,1/ y.toDouble).toInt

      override def toDouble(x: Float): Double = x.toDouble
      override def toLong(x: Float): Long = x.toLong
      override def toFloat(x: Float): Float = x
      override def toInt(x: Float): Int = x.toInt
      override def toShort(x: Float): Short = x.toShort
      override def toChar(x: Float): Char = x.toChar
      override def toByte(x: Float): Byte = x.toByte

      override def fromDouble(x: Double): Float = x.toFloat
      override def fromLong(x: Long): Float = x.toFloat
      override def fromFloat(x: Float): Float = x
      override def fromInt(x: Int): Float = x.toFloat
      override def fromShort(x: Short): Float = x.toFloat
      override def fromChar(x: Char): Float = x.toFloat
      override def fromByte(x: Byte): Float = x.toFloat
    }

    implicit object NumberLikeChar extends NumberLike[Char] {
      def plus(x: Char, y: Char): Char = (x + y).toChar
      def minus(x: Char, y: Char): Char = (x - y).toChar
      def multiply(x: Char, y: Char): Char = (x * y).toChar
      def divide(x: Char, y: Char): Char = (x / y).toChar
      def power(x: Char, y: Char): Char = scala.math.pow(x,y).toChar
      def root(x: Char, y: Char): Char = scala.math.pow(x,1/ y.toDouble).toChar

      override def toDouble(x: Char): Double = x.toDouble
      override def toLong(x: Char): Long = x.toLong
      override def toFloat(x: Char): Float = x.toFloat
      override def toInt(x: Char): Int = x.toInt
      override def toShort(x: Char): Short = x.toShort
      override def toChar(x: Char): Char = x
      override def toByte(x: Char): Byte = x.toByte

      override def fromDouble(x: Double): Char = x.toChar
      override def fromLong(x: Long): Char = x.toChar
      override def fromFloat(x: Float): Char = x.toChar
      override def fromInt(x: Int): Char = x.toChar
      override def fromShort(x: Short): Char = x.toChar
      override def fromChar(x: Char): Char = x
      override def fromByte(x: Byte): Char = x.toChar

    }

    implicit object NumberLikeByte extends NumberLike[Byte] {
      def plus(x: Byte, y: Byte): Byte = (x + y).toByte
      def minus(x: Byte, y: Byte): Byte = (x - y).toByte
      def multiply(x: Byte, y: Byte): Byte = (x * y).toByte
      def divide(x: Byte, y: Byte): Byte = (x / y).toByte
      def power(x: Byte, y: Byte): Byte = scala.math.pow(x,y).toByte
      def root(x: Byte, y: Byte): Byte = scala.math.pow(x,1/ y.toDouble).toByte

      override def toDouble(x: Byte): Double = x.toDouble
      override def toLong(x: Byte): Long = x.toLong
      override def toFloat(x: Byte): Float = x.toFloat
      override def toInt(x: Byte): Int = x.toInt
      override def toShort(x: Byte): Short = x.toShort
      override def toChar(x: Byte): Char = x.toChar
      override def toByte(x: Byte): Byte = x

      override def fromDouble(x: Double): Byte = x.toByte
      override def fromLong(x: Long): Byte = x.toByte
      override def fromFloat(x: Float): Byte = x.toByte
      override def fromInt(x: Int): Byte = x.toByte
      override def fromShort(x: Short): Byte = x.toByte
      override def fromChar(x: Char): Byte = x.toByte
      override def fromByte(x: Byte): Byte = x

    }
  }
}
