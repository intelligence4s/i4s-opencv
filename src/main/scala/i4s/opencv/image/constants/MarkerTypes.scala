package i4s.opencv.image.constants

object MarkerTypes extends Enumeration {
  type MarkerType = Value

  /** A crosshair marker shape */
  val Cross: Value = Value(0)

  /** A 45 degree tilted crosshair marker shape */
  val TiltedCross: Value = Value(1)

  /** A star marker shape, combination of cross and tilted cross */
  val Star: Value = Value(2)

  /** A diamond marker shape */
  val Diamond: Value = Value(3)

  /** A square marker shape */
  val Square: Value = Value(4)

  /** An upwards pointing triangle marker shape */
  val TriangleUp: Value = Value(5)

  /** A downwards pointing triangle marker shape */
  val TriangleDown: Value = Value(6)
}
