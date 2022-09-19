package i4s.opencv.core.types

import i4s.opencv.core.types.Types.{Cv16F, Cv16S, Cv16U, Cv32F, Cv32S, Cv64F, Cv8S, Cv8U, Type}

object MatTypes extends Enumeration {

  private val ChannelMax = 512
  private val ChannelShift = 3
  private val DepthMax = 1 << ChannelShift

  protected case class FlagVal(override val id: Int) extends super.Val(id) {
    override def toString(): String = s"${dataType.toString}C${channels}"

    def channels: Int = MatTypes.channels(this)
    def dataType: Type = MatTypes.depth(this)
  }
  type MatType = Value

  import scala.language.implicitConversions
  implicit def valueToTypeChannel(v: Value): FlagVal = v.asInstanceOf[FlagVal]

  def toString(f: Int): String = makeType(depth(f),channels(f)).toString

  // predefined type constants
  val Cv8UC1: FlagVal = FlagVal(Cv8UC(1))
  val Cv8UC2: FlagVal = FlagVal(Cv8UC(2))
  val Cv8UC3: FlagVal = FlagVal(Cv8UC(3))
  val Cv8UC4: FlagVal = FlagVal(Cv8UC(4))

  val Cv8SC1:FlagVal = FlagVal(Cv8SC(1))
  val Cv8SC2:FlagVal = FlagVal(Cv8SC(2))
  val Cv8SC3:FlagVal = FlagVal(Cv8SC(3))
  val Cv8SC4:FlagVal = FlagVal(Cv8SC(4))

  val Cv16UC1:FlagVal = FlagVal(Cv16UC(1))
  val Cv16UC2:FlagVal = FlagVal(Cv16UC(2))
  val Cv16UC3:FlagVal = FlagVal(Cv16UC(3))
  val Cv16UC4:FlagVal = FlagVal(Cv16UC(4))

  val Cv16SC1:FlagVal = FlagVal(Cv16SC(1))
  val Cv16SC2:FlagVal = FlagVal(Cv16SC(2))
  val Cv16SC3:FlagVal = FlagVal(Cv16SC(3))
  val Cv16SC4:FlagVal = FlagVal(Cv16SC(4))

  val Cv32SC1:FlagVal = FlagVal(Cv32SC(1))
  val Cv32SC2:FlagVal = FlagVal(Cv32SC(2))
  val Cv32SC3:FlagVal = FlagVal(Cv32SC(3))
  val Cv32SC4:FlagVal = FlagVal(Cv32SC(4))

  val Cv32FC1:FlagVal = FlagVal(Cv32FC(1))
  val Cv32FC2:FlagVal = FlagVal(Cv32FC(2))
  val Cv32FC3:FlagVal = FlagVal(Cv32FC(3))
  val Cv32FC4:FlagVal = FlagVal(Cv32FC(4))

  val Cv64FC1:FlagVal = FlagVal(Cv64FC(1))
  val Cv64FC2:FlagVal = FlagVal(Cv64FC(2))
  val Cv64FC3:FlagVal = FlagVal(Cv64FC(3))
  val Cv64FC4:FlagVal = FlagVal(Cv64FC(4))

  val Cv16FC1:FlagVal = FlagVal(Cv16FC(1))
  val Cv16FC2:FlagVal = FlagVal(Cv16FC(2))
  val Cv16FC3:FlagVal = FlagVal(Cv16FC(3))
  val Cv16FC4:FlagVal = FlagVal(Cv16FC(4))

  def makeType(depth: Type, channels: Int): Int = {
    require(channels > 0 && channels < ChannelMax, s"Channel value is $channels -- Channels count should be 1..${ChannelMax - 1}")
    require(depth.id <= ChannelMax, s"Unsupported channel depth $depth")
    (depth.id & (DepthMax - 1)) + ((channels - 1) << ChannelShift)
  }

  def Cv8UC(ch: Int): Int = makeType(Types.Cv8U,ch)
  def Cv8SC(ch: Int): Int = makeType(Types.Cv8S,ch)
  def Cv16UC(ch: Int): Int = makeType(Types.Cv16U,ch)
  def Cv16SC(ch: Int): Int = makeType(Types.Cv16S,ch)
  def Cv32SC(ch: Int): Int = makeType(Types.Cv32S,ch)
  def Cv32FC(ch: Int): Int = makeType(Types.Cv32F,ch)
  def Cv64FC(ch: Int): Int = makeType(Types.Cv64F,ch)
  def Cv16FC(ch: Int): Int = makeType(Types.Cv16F,ch)

  def channels(flowType: MatType): Int = channels(flowType.id)
  def depth(flowType: MatType): Type = depth(flowType.id)

  def channels(flag: Int): Int = (flag >> ChannelShift) + 1

  def depth(flag: Int): Type = Types(flag & (DepthMax - 1))

  def isInteger(flowType: MatType): Boolean = depth(flowType).id < Cv32F.id

  def ElementSize(flowType: MatType): Int = depth(flowType) match {
    case Cv8U | Cv8S => channels(flowType)
    case Cv16S | Cv16U | Cv16F => 2 * channels(flowType)
    case Cv32S | Cv32F => 4 * channels(flowType)
    case Cv64F => 8 * channels(flowType)
    case _ =>
      throw new UnsupportedOperationException(s"Unsupported CvType value: $flowType:${flowType.id}")
  }
  
}
