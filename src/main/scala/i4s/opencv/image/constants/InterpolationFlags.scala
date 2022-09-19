package i4s.opencv.image.constants

object InterpolationFlags extends Enumeration {
  type InterpolationFlag = Value

  /** nearest neighbor interpolation */
  val Nearest: Value = Value(0)

  /** bilinear interpolation */
  val Linear: Value = Value(1)

  /** bicubic interpolation */
  val Cubic: Value = Value(2)

  /** resampling using pixel area relation. It may be a preferred method for image decimation, as
   * it gives moire'-free results. But when the image is zoomed, it is similar to the INTER_NEAREST
   * method. */
  val Area: Value = Value(3)

  /** Lanczos interpolation over 8x8 neighborhood */
  val Lanczos4: Value = Value(4)

  /** Bit exact bilinear interpolation */
  val LinearExact: Value = Value(5)

  /** Bit exact nearest neighbor interpolation. This will produce same results as
   * the nearest neighbor method in PIL, scikit-image or Matlab. */
  val NearestExact: Value = Value(6)

  /** mask for interpolation codes */
  val Max: Value = Value(7)

  /** flag, fills all of the destination image pixels. If some of them correspond to outliers in the
   * source image, they are set to zero */
  val WarpFillOutliers: Value = Value(8)

  /** flag, inverse transformation
   * <p>
   * For example, #linearPolar or #logPolar transforms:
   * - flag is __not__ set: <pre>dst( \rho , \phi ) &#61; src(x,y)</pre>
   * - flag is set: <pre>dst(x,y) &#61; src( \rho , \phi )</pre>
   */
  val WarpInverseMap: Value = Value(16)

  /** Remaps an image to/from polar space. */
  val WarpPolarLinear: Value = Value(0)

  /** Remaps an image to/from semilog-polar space. */
  val WarpPolarLog: Value = Value(256)
}
