package i4s.opencv.core.constants

object CovariantFlags extends Enumeration {
  type CovariantFlag = Value

  val Scrampled = Value(0)
  val Normal = Value(1)
  val UseAverage = Value(2)
  val Scale = Value(4)
  val Rows = Value(8)
  val Cols = Value(16)
}
