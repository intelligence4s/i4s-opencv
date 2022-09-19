package i4s.opencv.image.constants

object ThresholdTypes extends Enumeration {
  type ThresholdType = Value

  /** <pre>\[\texttt{dst} (x,y) =  \fork{\texttt{maxval}}{if \(\texttt{src}(x,y) > \texttt{thresh}\)}{0}{otherwise}\]</pre> */
  val Binary: Value = Value(0)

  /** <pre>\[\texttt{dst} (x,y) =  \fork{0}{if \(\texttt{src}(x,y) > \texttt{thresh}\)}{\texttt{maxval}}{otherwise}\]</pre> */
  val BinaryInverted: Value = Value(1)

  /** <pre>\[\texttt{dst} (x,y) =  \fork{\texttt{threshold}}{if \(\texttt{src}(x,y) > \texttt{thresh}\)}{\texttt{src}(x,y)}{otherwise}\]</pre> */
  val Truncate: Value = Value(2)

  /** <pre>\[\texttt{dst} (x,y) =  \fork{\texttt{src}(x,y)}{if \(\texttt{src}(x,y) > \texttt{thresh}\)}{0}{otherwise}\]</pre> */
  val ToZero: Value = Value(3)

  /** <pre>\[\texttt{dst} (x,y) =  \fork{0}{if \(\texttt{src}(x,y) > \texttt{thresh}\)}{\texttt{src}(x,y)}{otherwise}\]</pre> */
  val ToZeroInverted: Value = Value(4)

  val Mask: Value = Value(7)

  /** flag, use Otsu algorithm to choose the optimal threshold value */
  val Otsu: Value = Value(8)

  /** flag, use Triangle algorithm to choose the optimal threshold value */
  val Triangle: Value = Value(16)

}
