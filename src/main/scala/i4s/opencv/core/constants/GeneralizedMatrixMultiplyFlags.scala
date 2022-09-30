package i4s.opencv.core.constants

object GeneralizedMatrixMultiplyFlags extends Enumeration {
  type GeneralizedMatrixMultiplyFlag = Value

  val TransposeSelf = Value(1)
  val TransposeFirst = Value(2)
  val TransposeSecond = Value(4)
}
