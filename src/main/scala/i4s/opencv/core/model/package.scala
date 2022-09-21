package i4s.opencv.core

import org.bytedeco.opencv.opencv_core.{Mat, MatVector, Point2dVector, PointVector}

package object model {

  import scala.language.implicitConversions

  implicit def pointSeq2PointVector(pointSeq: Seq[Point]): PointVector = new PointVector(pointSeq.toArray:_*)
  implicit def point2dSeq2Point2dVector(pointSeq: Seq[Point2d]): Point2dVector = new Point2dVector(pointSeq.toArray: _*)

  implicit def pointVector2PointSeq(pointVector: PointVector): Seq[Point] = pointVector.get().toSeq.map(Point.apply)
  implicit def point2dVector2Point2dSeq(pointVector: Point2dVector): Seq[Point2d] = pointVector.get().toSeq.map(Point2d.apply)

  implicit def matSeq2MatVector(matSeq: Seq[Mat]): MatVector = new MatVector(matSeq.toArray:_*)
  implicit def mataVector2MatSeq(matVector: MatVector): Seq[Mat] = matVector.get().toSeq.map(new Mat(_))




}
