package i4s.opencv.image.constants

object ImageWriteFlags extends Enumeration {
  type ImageWriteFlag = Value

  /** For JPEG, it can be a quality from 0 to 100 (the higher is the better). Default value is 95. */
  val JpegQuality: Value = Value(1)

  /** Enable JPEG features, 0 or 1, default is False. */
  val JpegProgressive: Value = Value(2)

  /** Enable JPEG features, 0 or 1, default is False. */
  val JpegOptimize: Value = Value(3)

  /** JPEG restart interval, 0 - 65535, default is 0 - no restart. */
  val JpegRstInterval: Value = Value(4)

  /** Separate luma quality level, 0 - 100, default is 0 - don't use. */
  val JpegLumaQuality: Value = Value(5)

  /** Separate chroma quality level, 0 - 100, default is 0 - don't use. */
  val JpegChromaQuality: Value = Value(6)

  /** For PNG, it can be the compression level from 0 to 9. A higher value means a smaller size and longer compression time. If specified, strategy is changed to IMWRITE_PNG_STRATEGY_DEFAULT (Z_DEFAULT_STRATEGY). Default value is 1 (best speed setting). */
  val PngCoompression: Value = Value(16)

  /** One of cv::ImwritePNGFlags, default is IMWRITE_PNG_STRATEGY_RLE. */
  val PngStrategy: Value = Value(17)

  /** Binary level PNG, 0 or 1, default is 0. */
  val PngBilevel: Value = Value(18)

  /** For PPM, PGM, or PBM, it can be a binary format flag, 0 or 1. Default value is 1. */
  val PxmBinary: Value = Value(32)

  /** override EXR storage type (FLOAT (FP32) is default) */
  val ExrType: Value = Value((3 << 4) + 0) /* 48 */

  /** override EXR compression type (ZIP_COMPRESSION = 3 is default) */
  val ExrCompression: Value = Value((3 << 4) + 1) /* 49 */

  /** For WEBP, it can be a quality from 1 to 100 (the higher is the better). By default (without any parameter) and for quality above 100 the lossless compression is used. */
  val WebpQuality: Value = Value(64)

  /** For PAM, sets the TUPLETYPE field to the corresponding string value that is defined for the format */
  val PamTupleType: Value = Value(128)

  /** For TIFF, use to specify which DPI resolution unit to set; see libtiff documentation for valid values */
  val TiffResUnit: Value = Value(256)

  /** For TIFF, use to specify the X direction DPI */
  val TiffXdpi: Value = Value(257)

  /** For TIFF, use to specify the Y direction DPI */
  val TiffYdpi: Value = Value(258)

  /** For TIFF, use to specify the image compression scheme. See libtiff for integer constants corresponding to compression formats. Note, for images whose depth is CV_32F, only libtiff's SGILOG compression scheme is used. For other supported depths, the compression scheme can be specified by this flag; LZW compression is the default. */
  val TiffCompression: Value = Value(259)

  /** For JPEG2000, use to specify the target compression rate (multiplied by 1000). The value can be from 0 to 1000. Default is 1000. */
  val Jpeg2000CompressionX1000: Value = Value(272)
}
