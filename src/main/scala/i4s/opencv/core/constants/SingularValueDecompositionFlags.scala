package i4s.opencv.core.constants

object SingularValueDecompositionFlags extends Enumeration {
  type SingularValueDecompositionFlag = Value

  val ModifyA = Value(1)
  val NoUorVT = Value(2)
  val FullUandVT = Value(4)
}
