package i4s.opencv.image.constants

object HoughModes extends Enumeration {
  type HoughMode = Value

  val Standard: HoughMode = Value(0)
  val Probabilistic: HoughMode = Value(1)
  val MultiScale: HoughMode = Value(2)
  val Gradient: HoughMode = Value(3)
  val AlternateGradient: HoughMode = Value(4)
}
