package i4s.opencv.core.constants

object BorderTypes extends Enumeration {
  type BorderType = Value

  /** <pre>iiiiii|abcdefgh|iiiiiii</pre>  with some specified <pre>i</pre> */
  val Constant: Value = Value(0)

  /** <pre>aaaaaa|abcdefgh|hhhhhhh</pre> */
  val Replicate: Value = Value(1)

  /** <pre>fedcba|abcdefgh|hgfedcb</pre> */
  val Reflect: Value = Value(2)

  /** <pre>cdefgh|abcdefgh|abcdefg</pre> */
  val Wrap: Value = Value(3)

  /** <pre>gfedcb|abcdefgh|gfedcba</pre> */
  val Reflect101: Value = Value(4)

  /** <pre>uvwxyz|abcdefgh|ijklmno</pre> */
  val Transparent: Value = Value(5)

  /** same as BORDER_REFLECT_101 */
  val Default: Value = Reflect101

  /** do not look outside of ROI */
  val Isolated: Value = Value(16)
}
