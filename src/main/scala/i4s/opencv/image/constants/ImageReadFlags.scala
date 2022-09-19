package i4s.opencv.image.constants

object ImageReadFlags extends Enumeration {
  type ImageReadFlag = Value

  /** If set, return the loaded image as is (with alpha channel, otherwise it gets cropped). Ignore EXIF orientation. */
  val Unchanged: Value = Value(-1)

  /** If set, always convert image to the single channel grayscale image (codec internal conversion). */
  val GrayScale: Value = Value(0)

  /** If set, always convert image to the 3 channel BGR color image. */
  val Color: Value = Value(1)

  /** If set, return 16-bit/32-bit image when the input has the corresponding depth, otherwise convert it to 8-bit. */
  val AnyDepth: Value = Value(2)

  /** If set, the image is read in any possible color format. */
  val AnyColor: Value = Value(4)

  /** If set, use the gdal driver for loading the image. */
  val LoadGdal: Value = Value(8)

  /** If set, always convert image to the single channel grayscale image and the image size reduced 1/2. */
  val ReducedGrayScale: Value = Value(16)

  /** If set, always convert image to the 3 channel BGR color image and the image size reduced 1/2. */
  val ReducedColor2: Value = Value(17)

  /** If set, always convert image to the single channel grayscale image and the image size reduced 1/4. */
  val ReducedGrayScale4: Value = Value(32)

  /** If set, always convert image to the 3 channel BGR color image and the image size reduced 1/4. */
  val ReducedColor4: Value = Value(33)

  /** If set, always convert image to the single channel grayscale image and the image size reduced 1/8. */
  val ReducedGrayScale8: Value = Value(64)

  /** If set, always convert image to the 3 channel BGR color image and the image size reduced 1/8. */
  val ReducedColor8: Value = Value(65)

  /** If set, do not rotate the image according to EXIF's orientation flag. */
  val IgnoreOrientation: Value = Value(128)

}
