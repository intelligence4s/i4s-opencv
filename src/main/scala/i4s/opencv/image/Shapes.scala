package i4s.opencv.image

import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type
import i4s.opencv.image.constants.ConnectedComponentsAlgorithmsTypes.ConnectedComponentsAlgorithmsType
import i4s.opencv.image.constants.ContourApproximationMethods.ContourApproximationMethod
import i4s.opencv.image.constants.DistanceTypes.DistanceType
import i4s.opencv.image.constants.RectanglesIntersectTypes
import i4s.opencv.image.constants.RectanglesIntersectTypes.RectanglesIntersectType
import i4s.opencv.image.constants.RetrievalModes.RetrievalMode
import i4s.opencv.image.constants.ShapeMatchModes.ShapeMatchMode
import org.bytedeco.javacpp.{DoublePointer, FloatPointer}
import org.bytedeco.opencv.global.opencv_imgproc
import org.bytedeco.opencv.opencv_core._
import org.bytedeco.opencv.opencv_imgproc.{GeneralizedHoughBallard, GeneralizedHoughGuil}

import java.nio.DoubleBuffer

object Shapes extends Shapes

trait Shapes {
  def moments(array: Mat, binaryImage: Boolean /*=false*/): Moments =
    opencv_imgproc.moments(array,binaryImage)

  def moments(array: Mat): Moments = moments(array, binaryImage = false)

  def huMoments(moments: Moments, hu: DoublePointer): Unit =
    opencv_imgproc.HuMoments(moments,hu)

  def huMoments(moments: Moments, hu: DoubleBuffer): Unit =
    opencv_imgproc.HuMoments(moments,hu)

  def huMoments(moments: Moments, hu: Array[Double]): Unit =
    opencv_imgproc.HuMoments(moments,hu)

  def huMoments(moments: Moments, hu: Mat): Unit =
    opencv_imgproc.HuMoments(moments,hu)

  implicit class ImageShapes(image: Image) {

    def connectedComponentsWithAlgorithm(labels: Mat, connectivity: Int, ltype: Type, ccltype: ConnectedComponentsAlgorithmsType): Int =
      opencv_imgproc.connectedComponentsWithAlgorithm(image,labels,connectivity,ltype.id,ccltype.id)

    def connectedComponents(connectivity: Int, ltype: Type): (Image,Int) = {
      val labels = new Mat()
      val result = opencv_imgproc.connectedComponents(image,labels,connectivity,ltype.id)
      (labels,result)
    }

    def connectedComponents(): (Image,Int) =
      connectedComponents(connectivity = 8,ltype = Types.Cv32S)

    def connectedComponentsWithStatsWithAlgorithm(connectivity: Int, ltype: Type, ccltype: ConnectedComponentsAlgorithmsType): (Image,Mat,Mat,Int) = {
      val labels = new Mat()
      val stats = new Mat()
      val centroids = new Mat()
      val result = opencv_imgproc.connectedComponentsWithStatsWithAlgorithm(image,labels,stats,centroids,connectivity,ltype.id,ccltype.id)
      (labels,stats,centroids,result)
    }

    def connectedComponentsWithStats(connectivity: Int, ltype: Type): (Image,Mat,Mat,Int) = {
      val labels = new Mat()
      val stats = new Mat()
      val centroids = new Mat()
      val result = opencv_imgproc.connectedComponentsWithStats(image,labels,stats,centroids,connectivity,ltype.id)
      (labels,stats,centroids,result)
    }

    def connectedComponentsWithStats(): (Image,Mat,Mat,Int) =
      connectedComponentsWithStats(connectivity = 8,ltype = Types.Cv32S)

    def findContours(hierarchy: Mat, mode: RetrievalMode, method: ContourApproximationMethod, offset: Point): MatVector = {
      val contours = new MatVector()
      opencv_imgproc.findContours(image, contours, hierarchy, mode.id, method.id, offset)
      contours
    }

    def findContours(hierarchy: Mat, mode: RetrievalMode, method: ContourApproximationMethod): MatVector =
      findContours(hierarchy,mode,method,offset = new Point())

    def findContours(mode: RetrievalMode, method: ContourApproximationMethod, offset: Point): MatVector = {
      val contours = new MatVector()
      opencv_imgproc.findContours(image,contours,mode.id,method.id,offset)
      contours
    }

    def findContours(mode: RetrievalMode, method: ContourApproximationMethod): MatVector =
      findContours(mode,method,offset = new Point())

  }

  def approxPolyDP(curve: Mat, approxCurve: Mat, epsilon: Double, closed: Boolean): Unit =
    opencv_imgproc.approxPolyDP(curve, approxCurve, epsilon, closed)

  def arcLength(curve: Mat, closed: Boolean): Double =
    opencv_imgproc.arcLength(curve, closed)

  def boundingRect(array: Mat): Rect =
    opencv_imgproc.boundingRect(array)

  def contourArea(contour: Mat, oriented: Boolean): Double =
    opencv_imgproc.contourArea(contour, oriented)

  def contourArea(contour: Mat): Double = contourArea(contour, oriented = false)

  def minAreaRect(points: Mat): RotatedRect =
    opencv_imgproc.minAreaRect(points)

  def boxPoints(box: RotatedRect): Mat = {
    val points = new Mat()
    opencv_imgproc.boxPoints(box, points)
    points
  }

  def minEnclosingCircle(points: Mat): (Point2f, Float) = {
    val center = new Point2f()
    val radius = new FloatPointer()
    opencv_imgproc.minEnclosingCircle(points, center, radius)
    (center, radius.get)
  }

  def minEnclosingTriangle(points: Mat): (Mat, Double) = {
    val triangle = new Mat()
    val area = opencv_imgproc.minEnclosingTriangle(points, triangle)
    (triangle, area)
  }

  def matchShapes(contour1: Mat, contour2: Mat, method: ShapeMatchMode, parameter: Double): Double =
    opencv_imgproc.matchShapes(contour1, contour2, method.id, parameter)

  def convexHull(points: Mat, clockwise: Boolean, returnPoints: Boolean): Mat = {
    val hull = new Mat()
    opencv_imgproc.convexHull(points, hull, clockwise, returnPoints)
    hull
  }

  def convexHull(points: Mat): Mat =
    convexHull(points, clockwise = false, returnPoints = true)

  def convexityDefects(contour: Mat, convexhull: Mat): Mat = {
    val defects = new Mat
    opencv_imgproc.convexityDefects(contour, convexhull, defects)
    defects
  }

  def isContourConvex(contour: Mat): Boolean =
    opencv_imgproc.isContourConvex(contour)

  def intersectConvexConvex(p1: Mat, p2: Mat, handleNested: Boolean): (Mat, Float) = {
    val p12 = new Mat()
    val area = opencv_imgproc.intersectConvexConvex(p1, p1, p12, handleNested)
    (p12, area)
  }

  def intersectConvexConvex(p1: Mat, p2: Mat): (Mat, Float) =
    intersectConvexConvex(p1, p2, handleNested = true)

  def fitEllipse(points: Mat): RotatedRect =
    opencv_imgproc.fitEllipse(points)

  def fitEllipseAMS(points: Mat): RotatedRect =
    opencv_imgproc.fitEllipseAMS(points)

  def fitEllipseDirect(points: Mat): RotatedRect =
    opencv_imgproc.fitEllipseDirect(points)

  def fitLine(points: Mat, distType: DistanceType, param: Double, reps: Double, aeps: Double): Mat = {
    val line = new Mat()
    opencv_imgproc.fitLine(points, line, distType.id, param, reps, aeps)
    line
  }

  def pointPolygonTest(contour: Mat, pt: Point2f, measureDist: Boolean): Double =
    opencv_imgproc.pointPolygonTest(contour, pt, measureDist)

  def rotatedRectangleIntersection(rect1: RotatedRect, rect2: RotatedRect): (RectanglesIntersectType, Mat) = {
    val intersectingRegion = new Mat()
    val itypeFlag = opencv_imgproc.rotatedRectangleIntersection(rect1, rect2, intersectingRegion)
    val itype: RectanglesIntersectType = RectanglesIntersectTypes(itypeFlag)
    (itype, intersectingRegion)
  }

  def createGeneralizedHoughBallard: GeneralizedHoughBallard =
    opencv_imgproc.createGeneralizedHoughBallard()

  def createGeneralizedHoughGuil: GeneralizedHoughGuil =
    opencv_imgproc.createGeneralizedHoughGuil()
}
