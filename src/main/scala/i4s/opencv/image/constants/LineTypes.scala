package i4s.opencv.image.constants

object LineTypes extends Enumeration {
  type LineType = Value

  val Filled: Value = Value(-1)

  /** 4-connected line */
  val Line4: Value = Value(4)

  /** 8-connected line */
  val Line8: Value = Value(8)

  /** anti-=aliased line */
  val LineAntiAliased: Value = Value(16)
}
