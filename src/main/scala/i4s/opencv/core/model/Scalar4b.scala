package i4s.opencv.core.model

object Scalar4b {
  def apply(s: org.bytedeco.opencv.opencv_core.Scalar4i): Scalar4b = new Scalar4b(s.get(0).toByte,s.get(1).toByte,s.get(2).toByte,s.get(3).toByte)

  def apply(bytes: Byte*): Scalar4b = {
    val bs: Array[Byte] = bytes.toArray.take(4).padTo(4, 0.toByte)
    new Scalar4b(bs(0), bs(1), bs(2), bs(3))
  }

  import scala.language.implicitConversions
  implicit def s2s(s: org.bytedeco.opencv.opencv_core.Scalar4i): Scalar4b = apply(s)
}

case class Scalar4b(v0: Byte, v1: Byte, v2: Byte, v3: Byte) extends org.bytedeco.opencv.opencv_core.Scalar4i(v0,v1,v2,v3) with ScalarLike[Byte] {
  override def construct(v0: Byte, v1: Byte, v2: Byte, v3: Byte): ScalarLike[Byte] = Scalar4b(v0,v1,v2,v3)
}
