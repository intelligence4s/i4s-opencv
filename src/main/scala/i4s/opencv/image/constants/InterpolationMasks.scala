package i4s.opencv.image.constants

object InterpolationMasks extends Enumeration {
  type InterpolationMask = Value

  val Bits: Value = Value(5)

  val Bits2: Value = Value(Bits.id * 2)

  val TabSize: Value = Value(1 << Bits.id)

  val TabSize2: Value = Value(TabSize.id * TabSize.id)
}
