package i4s.opencv.core.model

object Size {
  def apply(sz: org.bytedeco.opencv.opencv_core.Size): Size = new Size(sz.width,sz.height)

  import scala.language.implicitConversions
  implicit def s2s(sz: org.bytedeco.opencv.opencv_core.Size): Size = apply(sz)
}

case class Size(override val width: Int, override val height: Int) extends org.bytedeco.opencv.opencv_core.Size(width,height) with SizeLike[Int] {
  override def construct(width: Int, height: Int): SizeLike[Int] = new Size(width,height)
}
