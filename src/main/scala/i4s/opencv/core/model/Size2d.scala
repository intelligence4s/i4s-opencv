package i4s.opencv.core.model

object Size2d {
  def apply(sz: org.bytedeco.opencv.opencv_core.Size2d): Size2d = new Size2d(sz.width,sz.height)

  import scala.language.implicitConversions
  implicit def s2s(sz: org.bytedeco.opencv.opencv_core.Size2d): Size2d = apply(sz)
}

case class Size2d(override val width: Double, override val height: Double) extends org.bytedeco.opencv.opencv_core.Size2d(width,height) with SizeLike[Double] {
  override def construct(width: Double, height: Double): SizeLike[Double] = new Size2d(width,height)
}
