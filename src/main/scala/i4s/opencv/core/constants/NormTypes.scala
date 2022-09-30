package i4s.opencv.core.constants

object NormTypes extends Enumeration {
  type NormType = Value

  val Inf = Value(1)
  val L1 = Value(2)
  val L2 = Value(4)
  val L2Square = Value(5)
  val Hamming = Value(6)
  val Hamming2 = Value(7)
  val TypeMask = Hamming2
  val Relative = Value(8)
  val MinMax = Value(32)
}
