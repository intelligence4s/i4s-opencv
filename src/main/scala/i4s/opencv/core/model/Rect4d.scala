package i4s.opencv.core.model

import i4s.opencv.core.model.Math.NumberLike

object Rect4d {
  def apply(r: org.bytedeco.opencv.opencv_core.Rect2d): Rect4d = new Rect4d(r.x,r.y,r.width,r.height)
  def apply(tl: Point2d, br: Point2d): Rect4d = new Rect4d(tl,br)

  def apply(vals: Double*): Rect4d = {
    val vs: Array[Double] = vals.toArray.take(4).padTo(4, 0)
    Rect4d(Point2d(vs(0), vs(1)), Point2d(vs(2), vs(3)))
  }

  import scala.language.implicitConversions
  implicit def r2r(r: org.bytedeco.opencv.opencv_core.Rect2d): Rect4d = apply(r)
  implicit def rl2r(rl: RectLike[Double]): Rect4d = rl.asInstanceOf[Rect4d]

}

case class Rect4d(override val x: Double, override val y: Double, override val width: Double, override val height: Double)
  extends org.bytedeco.opencv.opencv_core.Rect2d(x,y,width,height) with RectLike[Double]
{
  def this(tl: Point2d, br: Point2d) = this(tl.x,tl.y,math.abs(br.x-tl.x),math.abs(br.y-tl.y))

  override def construct(x: Double, y: Double, width: Double, height: Double): RectLike[Double] = Rect4d(x,y,width,height)

  override def moveTo(p: PointLike[Double]): Rect4d = super.moveTo(p)
  override def scaleBy(sz: SizeLike[Double])(implicit ev: NumberLike[Double]): Rect4d = super.scaleBy(sz)
  override def shiftBy(sz: SizeLike[Double])(implicit ev: NumberLike[Double]): Rect4d = super.shiftBy(sz)

  override def tl(): Point2d = Point2d(super.tl)
  override def br(): Point2d = Point2d(super.br)

}
