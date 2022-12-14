package i4s.opencv.core.constants

object ErrorCodes extends Enumeration {
  type ErrorCode = Value

  val Ok: ErrorCode = Value(0)
  val BackTrace: ErrorCode = Value(-1)
  val StsError: ErrorCode = Value(-2)
  val StsInternal: ErrorCode = Value(-3)
  val StsNoMem: ErrorCode = Value(-4)
  val StsBadArg: ErrorCode = Value(-5)
  val StsBadFunc: ErrorCode = Value(-6)
  val StsNoConv: ErrorCode = Value(-7)
  val StsAutoTrace: ErrorCode = Value(-8)
  val HeaderIsNull: ErrorCode = Value(-9)
  val BadImageSize: ErrorCode = Value(-10)
  val BadOffset: ErrorCode = Value(-11)
  val BadDataPtr: ErrorCode = Value(-12)
  val BadStep: ErrorCode = Value(-13)
  val BadModelOrChSeq: ErrorCode = Value(-14)
  val BadNumChannels: ErrorCode = Value(-15)
  val BadNumChannel1U: ErrorCode = Value(-16)
  val BadDepth: ErrorCode = Value(-17)
  val BadAlphaChannel: ErrorCode = Value(-18)
  val BadOrder: ErrorCode = Value(-19)
  val BadOrigin: ErrorCode = Value(-20)
  val BadAlign: ErrorCode = Value(-21)
  val BadCallBack: ErrorCode = Value(-22)
  val BadTileSize: ErrorCode = Value(-23)
  val BadCOI: ErrorCode = Value(-24)
  val BadROISize: ErrorCode = Value(-25)
  val MaskIsTiled: ErrorCode = Value(-26)
  val StsNullPtr: ErrorCode = Value(-27)
  val StsVecLengthErr: ErrorCode = Value(-28)
  val StsFilterStructContentErr: ErrorCode = Value(-29)
  val StsKernelStructContentErr: ErrorCode = Value(-30)
  val StsFilterOffsetErr: ErrorCode = Value(-31)
  val StsBadSize: ErrorCode = Value(-201)
  val StsDivByZero: ErrorCode = Value(-202)
  val StsInplaceNotSupported: ErrorCode = Value(-203)
  val StsObjectNotFound: ErrorCode = Value(-204)
  val StsUnmatchedFormats: ErrorCode = Value(-205)
  val StsBadFlag: ErrorCode = Value(-206)
  val StsBadPoint: ErrorCode = Value(-207)
  val StsBadMask: ErrorCode = Value(-208)
  val StsUnmatchedSizes: ErrorCode = Value(-209)
  val StsUnsupportedFormat: ErrorCode = Value(-210)
  val StsOutOfRange: ErrorCode = Value(-211)
  val StsParseError: ErrorCode = Value(-212)
  val StsNotImplemented: ErrorCode = Value(-213)
  val StsBadMemBlock: ErrorCode = Value(-214)
  val StsAssert: ErrorCode = Value(-215)
  val GpuNotSupported: ErrorCode = Value(-216)
  val GpuApiCallError: ErrorCode = Value(-217)
  val OpenGlNotSupported: ErrorCode = Value(-218)
  val OpenGlApiCallError: ErrorCode = Value(-219)
  val OpenCLApiCallError: ErrorCode = Value(-220)
  val OpenCLDoubleNotSupported: ErrorCode = Value(-221)
  val OpenCLInitError: ErrorCode = Value(-222)
  val OpenCLNoAMDBlasFft: ErrorCode = Value(-223)
}
