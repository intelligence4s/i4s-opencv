package i4s.opencv.core.constants

object DiscreteFourierTransformFlags extends Enumeration {
  type DiscreteFourierTransformFlag = Value

  val Inverse = Value(1)
  val Scale = Value(2)
  val Rows = Value(4)
  val ComplexOutput = Value(16)
  val RealOutput = Value(32)
  val ComplexInput = Value(64)

}
