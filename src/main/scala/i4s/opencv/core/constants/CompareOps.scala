package i4s.opencv.core.constants

object CompareOps extends Enumeration {
  type CompareOp = Value

  val Equals = Value(0)
  val GreaterThan = Value(1)
  val GreaterThanOrEuals = Value(2)
  val LessThan = Value(3)
  val LessThanOrEuals = Value(4)
  val NotEquals = Value(5)
}
