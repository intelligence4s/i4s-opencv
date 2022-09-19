package i4s.opencv.image.constants

object DistanceTypes extends Enumeration {
  type DistanceType = Value

  /** User defined distance */
  val User: Value = Value(-1)

  /** distance = |x1-x2| + |y1-y2| */
  val L1: Value = Value(1)

  /** the simple euclidean distance */
  val L2: Value = Value(2)

  /** distance = max(|x1-x2|,|y1-y2|) */
  val C: Value = Value(3)

  /** L1-L2 metric: distance = 2(sqrt(1+x*x/2) - 1)) */
  val L12: Value = Value(4)

  /** distance = c^2(|x|/c-log(1+|x|/c)), c = 1.3998 */
  val Fair: Value = Value(5)

  /** distance = c^2/2(1-exp(-(x/c)^2)), c = 2.9846 */
  val Welsch: Value = Value(6)

  /** distance = |x|<c ? x^2/2 : c(|x|-c/2), c=1.345 */
  val Huber: Value = Value(7)

}
