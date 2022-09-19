package i4s.opencv.core.model

object Point {
  def apply(p: org.bytedeco.opencv.opencv_core.Point): Point = new Point(p.x,p.y)

  def apply(vals: Int*): Point = {
    val vs: Array[Int] = vals.toArray.take(2).padTo(2,0)
    new Point(vs(0),vs(1))
  }

  import scala.language.implicitConversions
  implicit def p2p(p: org.bytedeco.opencv.opencv_core.Point): Point = apply(p)
}

case class Point(override val x: Int, override val y: Int) extends org.bytedeco.opencv.opencv_core.Point(x,y) with PointLike[Int] {
  override def construct(x: Int, y: Int): PointLike[Int] = new Point(x,y)
}
