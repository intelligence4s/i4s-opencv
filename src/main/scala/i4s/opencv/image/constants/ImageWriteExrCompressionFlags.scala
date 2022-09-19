package i4s.opencv.image.constants

object ImageWriteExrCompressionFlags extends Enumeration {
  type ImageReadFlags = Value

  /*IMWRITE_EXR_TYPE_UNIT = 0, //!< not supported */
  /** store as HALF (FP16) */
  val Half: Value = Value(1)

  /** store as FP32 (default) */
  val Float: Value = Value(2)

}
