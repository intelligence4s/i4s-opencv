package i4s.opencv.core.constants

object AccessFlags extends Enumeration {
  type AccessFlag = Value
  private val AccessFlagRoot = 1 << 24

  val Read      = Value(1 * AccessFlagRoot)
  val Write     = Value(2 * AccessFlagRoot)
  val ReadWrite = Value(3 * AccessFlagRoot)
  val Fast      = Value(4 * AccessFlagRoot)
  val Mask      = ReadWrite

}
