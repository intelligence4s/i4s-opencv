package i4s.opencv.core.model

object Point2d {
  def apply(x: Double, y: Double): Point2d = new Point2d(x,y)
  def apply(p: org.bytedeco.opencv.opencv_core.Point2d): Point2d = new Point2d(p.x,p.y)

  def apply(vals: Double*): Point2d = {
    val vs: Array[Double] = vals.toArray.take(2).padTo(2, 0d)
    new Point2d(vs(0), vs(1))
  }

  import scala.language.implicitConversions
  implicit def p2p(p: org.bytedeco.opencv.opencv_core.Point2d): Point2d = apply(p)
}

class Point2d(override val x: Double, override val y: Double) extends org.bytedeco.opencv.opencv_core.Point2d(x,y) with PointLike[Double] {
  override def construct(x: Double, y: Double): PointLike[Double] = new Point2d(x,y)
}
