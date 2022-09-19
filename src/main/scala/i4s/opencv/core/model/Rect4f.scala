package i4s.opencv.core.model

import i4s.opencv.core.model.Math.NumberLike

object Rect4f {
  def apply(r: org.bytedeco.opencv.opencv_core.Rect2f): Rect4f = new Rect4f(r.x,r.y,r.width,r.height)
  def apply(tl: Point2f, br: Point2f): Rect4f = new Rect4f(tl,br)

  def apply(vals: Float*): Rect4f = {
    val vs: Array[Float] = vals.toArray.take(4).padTo(4, 0)
    new Rect4f(Point2f(vs(0), vs(1)), Point2f(vs(2), vs(3)))
  }

  import scala.language.implicitConversions
  implicit def r2r(r: org.bytedeco.opencv.opencv_core.Rect2f): Rect4f = apply(r)
  implicit def rl2r(rl: RectLike[Float]): Rect4f = rl.asInstanceOf[Rect4f]
}

case class Rect4f(override val x: Float, override val y: Float, override val width: Float, override val height: Float)
  extends org.bytedeco.opencv.opencv_core.Rect2f(x,y,width,height) with RectLike[Float]
{
  def this(tl: Point2f, br: Point2f) = this(tl.x,tl.y,math.abs(br.x-tl.x),math.abs(br.y-tl.y))

  override def construct(x: Float, y: Float, width: Float, height: Float): RectLike[Float] = Rect4f(x,y,width,height)

  override def moveTo(p: PointLike[Float]): Rect4f = super.moveTo(p)
  override def scaleBy(sz: SizeLike[Float])(implicit ev: NumberLike[Float]): Rect4f = super.scaleBy(sz)
  override def shiftBy(sz: SizeLike[Float])(implicit ev: NumberLike[Float]): Rect4f = super.shiftBy(sz)

  override def tl(): Point2f = Point2f(super.tl)
  override def br(): Point2f = Point2f(super.br)
}
