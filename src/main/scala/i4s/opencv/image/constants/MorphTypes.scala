package i4s.opencv.image.constants

object MorphTypes extends Enumeration {
  type MorphType = Value

  val Erode: Value = Value(0)

  /** see #dilate */
  val Dilate: Value = Value(1)

  /** an opening operation
   * <pre> \[\texttt{dst} = \mathrm{open} ( \texttt{src} , \texttt{element} )= \mathrm{dilate} ( \mathrm{erode} ( \texttt{src} , \texttt{element} ))\]}</pre> */
  val Open: Value = Value(2)

  /** a closing operation
   * <pre> \[\texttt{dst} = \mathrm{close} ( \texttt{src} , \texttt{element} )= \mathrm{erode} ( \mathrm{dilate} ( \texttt{src} , \texttt{element} ))\]}</pre> */
  val Close: Value = Value(3)

  /** a morphological gradient
   * <pre> \[\texttt{dst} = \mathrm{morph\_grad} ( \texttt{src} , \texttt{element} )= \mathrm{dilate} ( \texttt{src} , \texttt{element} )- \mathrm{erode} ( \texttt{src} , \texttt{element} )\]}</pre> */
  val Gradient: Value = Value(4)

  /** "top hat"
   * <pre> \[\texttt{dst} = \mathrm{tophat} ( \texttt{src} , \texttt{element} )= \texttt{src} - \mathrm{open} ( \texttt{src} , \texttt{element} )\]}</pre> */
  val TopHat: Value = Value(5)

  /** "black hat"
   * <pre> \[\texttt{dst} = \mathrm{blackhat} ( \texttt{src} , \texttt{element} )= \mathrm{close} ( \texttt{src} , \texttt{element} )- \texttt{src}\]}</pre> */
  val BlackHat: Value = Value(6)

  /** "hit or miss"
   * .- Only supported for CV_8UC1 binary images. A tutorial can be found in the documentation */
  val HitOrMiss: Value = Value(7)

}
