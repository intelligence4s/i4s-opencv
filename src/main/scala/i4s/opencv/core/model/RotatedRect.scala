package i4s.opencv.core.model

import i4s.opencv.core.model.Math.NumberLike

object RotatedRect {
  def apply(r: org.bytedeco.opencv.opencv_core.RotatedRect): RotatedRect = RotatedRect(Point2f(r.center()),Size2f(r.size()),r.angle())
  def apply(ul: Point, lr: Point): Rect = new Rect(ul,lr)

  def apply(vals: Float*): RotatedRect = {
    val vs: Array[Float] = vals.toArray.take(5).padTo(5,0)
    new RotatedRect(Point2f(vs(0),vs(1)),Size2f(vs(2),vs(3)),vs(4))
  }

  import scala.language.implicitConversions
  implicit def r2r(r: org.bytedeco.opencv.opencv_core.RotatedRect): RotatedRect = apply(r)
}

case class RotatedRect(override val center: Point2f, override val size: Size2f, override val angle: Float) extends org.bytedeco.opencv.opencv_core.RotatedRect(center,size,angle) {
  def this(tl: Point2f, br: Point2f, angle: Float) =
    this(Point2f((br.x + tl.x) / 2, (br.y + tl.y) / 2), Size2f(math.abs(br.x - tl.x), math.abs(br.y - tl.y)), angle)

  def moveTo(p: Point2f): RotatedRect = RotatedRect(p, size, angle)

  def scaleBy(sz: Size2f): RotatedRect = {
    val updated: Point2f = center * Point2f(sz.width, sz.height)
    RotatedRect(updated, size, angle)
  }

  def shiftBy(sz: Size2f): RotatedRect = {
    val updated: Point2f = center + Point2f(sz.width, sz.height)
    RotatedRect(updated, size, angle)
  }

  def rotateBy(diff: Float): RotatedRect = RotatedRect(center, size, angle + diff)
}
