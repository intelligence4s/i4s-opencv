package i4s.opencv.core.model

object Scalar4f {
  def apply(s: org.bytedeco.opencv.opencv_core.Scalar4f): Scalar4f = new Scalar4f(s.get(0),s.get(1),s.get(2),s.get(3))

  def apply(values: Float*): Scalar4f = {
    val vs: Array[Float] = values.toArray.take(4).padTo(4, 0)
    new Scalar4f(vs(0), vs(1), vs(2), vs(3))
  }

  import scala.language.implicitConversions
  implicit def s2s(s: org.bytedeco.opencv.opencv_core.Scalar4f): Scalar4f = apply(s)
}

case class Scalar4f(v0: Float, v1: Float, v2: Float, v3: Float) extends org.bytedeco.opencv.opencv_core.Scalar4f(v0,v1,v2,v3) with ScalarLike[Float] {
  override def construct(v0: Float, v1: Float, v2: Float, v3: Float): ScalarLike[Float] = Scalar4f(v0,v1,v2,v3)
}
