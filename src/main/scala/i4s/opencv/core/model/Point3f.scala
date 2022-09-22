package i4s.opencv.core.model

object Point3f {
  def apply(x: Float, y: Float, z: Float): Point3f = new Point3f(x,y,z)
  def apply(p: org.bytedeco.opencv.opencv_core.Point3f): Point3f = new Point3f(p.x,p.y,p.z)

  def apply(vals: Float*): Point3f = {
    val vs: Array[Float] = vals.toArray.take(2).padTo(2, 0f)
    new Point3f(vs(0), vs(1), vs(2))
  }

  import scala.language.implicitConversions
  implicit def p2p(p: org.bytedeco.opencv.opencv_core.Point3f): Point3f = apply(p)
  implicit def pl2p(pl: PointLike[Float]): Point3f = pl.asInstanceOf[Point3f]
}

class Point3f(override val x: Float, override val y: Float, override val z: Float) extends org.bytedeco.opencv.opencv_core.Point3f(x,y,z) with Point3Like[Float] {
  override def construct(x: Float, y: Float, z: Float): Point3Like[Float] = new Point3f(x,y,z)
}
