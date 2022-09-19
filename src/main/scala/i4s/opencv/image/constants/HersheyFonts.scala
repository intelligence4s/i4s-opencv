package i4s.opencv.image.constants

object HersheyFonts extends Enumeration {
  protected case class TypeVal(override val id: Int) extends super.Val {
    def face(italic: Boolean): Int = if (italic) id | HersheyFonts.Italic.id else id
  }

  type HersheyFont = Value

  import scala.language.implicitConversions
  implicit def valueToImageReadFlag(v: Value): TypeVal = v.asInstanceOf[TypeVal]

  /** normal size sans-serif font */
  val Simplex: TypeVal = TypeVal(0)

  /** small size sans-serif font */
  val Plain: TypeVal = TypeVal(1)

  /** normal size sans-serif font (more complex than FONT_HERSHEY_SIMPLEX) */
  val Duplex: TypeVal = TypeVal(2)

  /** normal size serif font */
  val Complex: TypeVal = TypeVal(3)

  /** normal size serif font (more complex than FONT_HERSHEY_COMPLEX) */
  val Triplex: TypeVal = TypeVal(4)

  /** smaller version of FONT_HERSHEY_COMPLEX */
  val ComplexSmall: TypeVal = TypeVal(5)

  /** hand-writing style font */
  val ScriptSimplex: TypeVal = TypeVal(6)

  /** more complex variant of FONT_HERSHEY_SCRIPT_SIMPLEX */
  val ScriptComplex: TypeVal = TypeVal(7)

  /** flag for italic font */
  val Italic: TypeVal = TypeVal(16)

}
