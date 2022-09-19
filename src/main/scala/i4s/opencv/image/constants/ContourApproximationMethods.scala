package i4s.opencv.image.constants

object ContourApproximationMethods extends Enumeration {
  type ContourApproximationMethod = Value

  /** stores absolutely all the contour points. That is, any 2 subsequent points (x1,y1) and
   * (x2,y2) of the contour will be either horizontal, vertical or diagonal neighbors, that is,
   * max(abs(x1-x2),abs(y2-y1))==1. */
  val None = Value(1)

  /** compresses horizontal, vertical, and diagonal segments and leaves only their end points.
   * For example, an up-right rectangular contour is encoded with 4 points. */
  val Simple = Value(2)

  /** applies one of the flavors of the Teh-Chin chain approximation algorithm \cite TehChin89 */
  val TehChin89LI = Value(3)

  /** applies one of the flavors of the Teh-Chin chain approximation algorithm \cite TehChin89 */
  val TehChin89KCOS = Value(4)
}
