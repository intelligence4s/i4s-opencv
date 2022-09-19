package i4s.opencv.image.constants

object MorphShapes extends Enumeration {
  type MorphShape = Value

  val Rectangle: Value = Value(0)

  /** a cross-shaped structuring element:
   * <pre> \[E_{ij} = \begin{cases} 1 & \texttt{if } {i=\texttt{anchor.y } {or } {j=\texttt{anchor.x}}} \\0 & \texttt{otherwise} \end{cases}\]}</pre> */
  val Cross: Value = Value(1)

  /** an elliptic structuring element, that is, a filled ellipse inscribed
   * into the rectangle Rect(0, 0, esize.width, 0.esize.height) */
  val Ellipse: Value = Value(2)

}
