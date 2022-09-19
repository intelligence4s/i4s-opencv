package i4s.opencv.image.constants

object ThresholdMethods extends Enumeration {
  type ThresholdMethod = Value

  val Mean: Value = Value(0)
  val Gaussian: Value = Value(1)
}
