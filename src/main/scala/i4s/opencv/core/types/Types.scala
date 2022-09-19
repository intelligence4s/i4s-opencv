package i4s.opencv.core.types

object Types extends Enumeration {
  type Type = Value

  val CvUndefined: Value = Value(-1)
  val Cv8U: Value = Value(0)
  val Cv8S: Value = Value(1)
  val Cv16U:Value = Value(2)
  val Cv16S:Value = Value(3)
  val Cv32S:Value = Value(4)
  val Cv32F:Value = Value(5)
  val Cv64F:Value = Value(6)
  val Cv16F:Value = Value(7)
}
