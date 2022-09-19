package i4s.opencv.image.constants

object ImageWriteExrTypeFlags extends Enumeration {
  type ImageReadFlags = Value
  
  /** no compression */
  val NoCompression: Value = Value(0)

  /** run length encoding */
  val Rle: Value = Value(1)

  /** zlib compression, one scan line at a time */
  val Zips: Value = Value(2)

  /** zlib compression, in blocks of 16 scan lines */
  val Zip: Value = Value(3)

  /** piz-based wavelet compression */
  val Piz: Value = Value(4)

  val Pxr24: Value = Value(5)

  /** lossy 4-by-4 pixel block compression, fixed compression rate */
  val B44: Value = Value(6)

  /** lossy 4-by-4 pixel block compression, flat fields are compressed more */
  val B44a: Value = Value(7)

  /** lossy DCT based compression, in blocks of 32 scanlines. More efficient for partial buffer access. Supported since OpenEXR 2.2.0. */
  val Dwaa: Value = Value(8)

  /** lossy DCT based compression, in blocks of 256 scanlines. More efficient space wise and faster to decode full frames than DWAA_COMPRESSION. Supported since OpenEXR 2.2.0. */
  val Dwab: Value = Value(9)

}
