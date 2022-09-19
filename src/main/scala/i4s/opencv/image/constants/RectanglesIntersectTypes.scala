package i4s.opencv.image.constants

object RectanglesIntersectTypes extends Enumeration {
  type RectanglesIntersectType = Value

  /** No intersection */
  val None: Value = Value(0)

  /** There is a partial intersection */
  val Partial: Value = Value(1)

  /** One of the rectangle is fully enclosed in the other */
  val Full: Value = Value(2)

}
