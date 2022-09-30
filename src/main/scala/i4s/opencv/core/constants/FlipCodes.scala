package i4s.opencv.core.constants

object FlipCodes extends Enumeration {
  type FlipCode = Value

  val Vertical = Value(0)
  val Horizontal = Value(1)
  val Both = Value(-1)
}
