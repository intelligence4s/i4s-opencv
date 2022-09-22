package i4s.opencv.core.model

object Point3d {
  def apply(x: Double, y: Double, z: Double): Point3d = new Point3d(x,y,z)
  def apply(p: org.bytedeco.opencv.opencv_core.Point3d): Point3d = new Point3d(p.x,p.y,p.z)

  def apply(vals: Double*): Point3d = {
    val vs: Array[Double] = vals.toArray.take(2).padTo(2, 0f)
    new Point3d(vs(0), vs(1), vs(2))
  }

  import scala.language.implicitConversions
  implicit def p2p(p: org.bytedeco.opencv.opencv_core.Point3d): Point3d = apply(p)
  implicit def pl2p(pl: PointLike[Double]): Point3d = pl.asInstanceOf[Point3d]
}

class Point3d(override val x: Double, override val y: Double, override val z: Double) extends org.bytedeco.opencv.opencv_core.Point3d(x,y,z) with Point3Like[Double] {
  override def construct(x: Double, y: Double, z: Double): Point3Like[Double] = new Point3d(x,y,z)
}
