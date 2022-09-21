package i4s.opencv.core.model

object Size2f {
  def apply(sz: org.bytedeco.opencv.opencv_core.Size2f): Size2f = new Size2f(sz.width,sz.height)

  import scala.language.implicitConversions
  implicit def s2s(sz: org.bytedeco.opencv.opencv_core.Size2f): Size2f = apply(sz)
}

case class Size2f(override val width: Float, override val height: Float) extends org.bytedeco.opencv.opencv_core.Size2f(width,height) with SizeLike[Float] {
  override def construct(width: Float, height: Float): SizeLike[Float] = new Size2f(width,height)
}
