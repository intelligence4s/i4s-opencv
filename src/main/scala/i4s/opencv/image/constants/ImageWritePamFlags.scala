package i4s.opencv.image.constants

object ImageWritePamFlags extends Enumeration {
  type ImageReadFlags = Value

  val NullFormat: Value = Value(0)
  val BlackAndWhite: Value = Value(1)
  val Grayscale: Value = Value(2)
  val GrayscaleAlpha: Value = Value(3)
  val Rgb: Value = Value(4)
  val RgbAlpha: Value = Value(5)

}
