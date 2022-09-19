package i4s.opencv.core.model

import i4s.opencv.core.model.Math.NumberLike

object Rect {
  def apply(r: org.bytedeco.opencv.opencv_core.Rect): Rect = Rect(r.x,r.y,r.width,r.height)
  def apply(ul: Point, lr: Point): Rect = new Rect(ul,lr)

  def apply(vals: Int*): Rect = {
    val vs: Array[Int] = vals.toArray.take(4).padTo(4,0)
    new Rect(vs(0),vs(1),vs(2),vs(3))
  }

  import scala.language.implicitConversions
  implicit def r2r(r: org.bytedeco.opencv.opencv_core.Rect): Rect = apply(r)
  implicit def rl2r(rl: RectLike[Int]): Rect = rl.asInstanceOf[Rect]
}

case class Rect(override val x: Int, override val y: Int, override val width: Int, override val height: Int) extends org.bytedeco.opencv.opencv_core.Rect(x,y,width,height) with RectLike[Int] {
  def this(tl: Point, pr: Point) = this(tl.x,tl.y,math.abs(pr.x-tl.x),math.abs(pr.y-tl.y))

  override def construct(x: Int, y: Int, width: Int, height: Int): RectLike[Int] = Rect(x,y,width,height)

  override def moveTo(p: PointLike[Int]): Rect = super.moveTo(p)
  override def scaleBy(sz: SizeLike[Int])(implicit ev: NumberLike[Int]): Rect = super.scaleBy(sz)
  override def shiftBy(sz: SizeLike[Int])(implicit ev: NumberLike[Int]): Rect = super.shiftBy(sz)

  override def tl(): Point = Point(super.tl)
  override def br(): Point = Point(super.br)
}
