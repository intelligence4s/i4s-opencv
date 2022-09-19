package i4s.opencv.core.model

object Scalar {
  def apply(s: org.bytedeco.opencv.opencv_core.Scalar): Scalar = new Scalar(s.get(0),s.get(1),s.get(2),s.get(3))

  def apply(vals: Double*): Scalar = {
    val vs: Array[Double] = vals.toArray.take(4).padTo(4, 0)
    new Scalar(vs(0),vs(1),vs(2),vs(3))
  }

  import scala.language.implicitConversions
  implicit def s2s(s: org.bytedeco.opencv.opencv_core.Scalar): Scalar = apply(s)

  def RGB(red: Double, blue: Double, green: Double): Scalar = Scalar(blue,green,red,0)

  val Zero: Scalar = Scalar(0, 0, 0, 0)
  val One: Scalar = Scalar(1, 1, 1, 1)
  val OneHalf: Scalar = Scalar(0.5, 0.5, 0.5, 0.5)
  val Alpha1: Scalar = Scalar(0, 0, 0, 1)
  val alpha256: Scalar = Scalar(0, 0, 0, 256)

  val White: Scalar = RGB(255, 255, 255)
  val Gray: Scalar = RGB(128, 128, 128)
  val Black: Scalar = RGB(0, 0, 0)
  val Red: Scalar = RGB(255, 0, 0)
  val Green:Scalar = RGB(0,255,0)
  val Blue:Scalar = RGB(0,0,255)
  val Cyan: Scalar = RGB(0, 255, 255)
  val Magenta: Scalar = RGB(255, 0, 255)
  val Yellow: Scalar = RGB(255, 255, 0)
}

case class Scalar(v0: Double, v1: Double, v2: Double, v3: Double) extends org.bytedeco.opencv.opencv_core.Scalar(v0,v1,v2,v3) with ScalarLike[Double] {
  override def construct(v0: Double, v1: Double, v2: Double, v3: Double): ScalarLike[Double] = Scalar(v0,v1,v2,v3)
}
