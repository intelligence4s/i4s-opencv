package i4s.opencv.image.constants

object DistanceLabelTypes extends Enumeration {
  type DistanceLabelType = Value
  
  /** each connected component of zeros in src (as well as all the non-zero pixels closest to the
   * connected component) will be assigned the same label */
  val ConnectedComponent: Value = Value(0)

  /** each zero pixel (and all the non-zero pixels closest to it) gets its own label. */
  val Pixel: Value = Value(1)
}
