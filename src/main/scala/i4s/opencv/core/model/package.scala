package i4s.opencv.core

import org.bytedeco.opencv.opencv_core.{Mat, MatVector, Point2dVector, PointVector}
import org.bytedeco.opencv.opencv_imgproc.{Vec2fVector, Vec3fVector, Vec4fVector, Vec4iVector}

package object model {

  import scala.language.implicitConversions

  implicit def pointSeq2PointVector(pointSeq: Seq[Point]): PointVector = new PointVector(pointSeq.toArray:_*)
  implicit def point2dSeq2Point2dVector(pointSeq: Seq[Point2d]): Point2dVector = new Point2dVector(pointSeq.toArray: _*)

  implicit def pointVector2PointSeq(pointVector: PointVector): Seq[Point] = pointVector.get().toSeq.map(Point.apply)
  implicit def point2dVector2Point2dSeq(pointVector: Point2dVector): Seq[Point2d] = pointVector.get().toSeq.map(Point2d.apply)

  implicit def matSeq2MatVector(matSeq: Seq[Mat]): MatVector = new MatVector(matSeq.toArray:_*)
  implicit def mataVector2MatSeq(matVector: MatVector): Seq[Mat] = matVector.get().toSeq.map(new Mat(_))

  implicit def point2fSeq2vec2fvector(floatSeqSeq: Seq[Point2f]): Vec2fVector = new Vec2fVector(floatSeqSeq:_*)
  implicit def vec2fVector2point2fSeq(vec2fVector: Vec2fVector): Seq[Point2f] = vec2fVector.get().toSeq.map(Point2f.apply)

  implicit def point3fSeq2vec2fvector(floatSeqSeq: Seq[Point3f]): Vec3fVector = new Vec3fVector(floatSeqSeq:_*)
  implicit def vec2fVector2point3fSeq(vec3fVector: Vec3fVector): Seq[Point3f] = vec3fVector.get().toSeq.map(Point3f.apply)

  implicit def scalar4iSeq2vec4iVector(scalar4iSeq: Seq[Scalar4i]): Vec4iVector = new Vec4iVector(scalar4iSeq:_*)
  implicit def vec4iVector2scalar4iSeq(vec4iVector: Vec4iVector): Seq[Scalar4i] = vec4iVector.get().toSeq.map(Scalar4i.apply)

  implicit def scalar4fSeq2vec4fVector(scalar4fSeq: Seq[Scalar4f]): Vec4fVector = new Vec4fVector(scalar4fSeq:_*)
  implicit def vec4fVector2scalar4fSeq(vec4fVector: Vec4fVector): Seq[Scalar4f] = vec4fVector.get().toSeq.map(Scalar4f.apply)

}
