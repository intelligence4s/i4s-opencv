package i4s.opencv.image

import i4s.opencv.core.model.mats.{MappedMat, Mat}
import i4s.opencv.core.model.{Point, Point2d, Rect, RotatedRect, Scalar, Size, Size2d}
import i4s.opencv.core.types.Types
import i4s.opencv.image.constants.HersheyFonts.HersheyFont
import i4s.opencv.image.constants.LineTypes.LineType
import i4s.opencv.image.constants.MarkerTypes.MarkerType
import i4s.opencv.image.constants.{LineTypes, MarkerTypes}
import org.bytedeco.opencv.global.opencv_imgproc
import org.bytedeco.opencv.opencv_core.{MatVector, Point2dVector, PointVector}

object Drawing extends Drawing

trait Drawing {
  import i4s.opencv.TypeConverters._

  def getTextSize(text: String, fontFace: HersheyFont, italic: Boolean, fontScale: Double, thickness: Int, baseLine: Int): Size =
    opencv_imgproc.getTextSize(text, fontFace.face(italic), fontScale, thickness, baseLine.asPointer)

  def getTextSize(text: String, fontFace: HersheyFont, italic: Boolean, fontScale: Double, thickness: Int, baseLine: Array[Int]): Size =
    opencv_imgproc.getTextSize(text, fontFace.face(italic), fontScale, thickness, baseLine)

  def getFontScaleFromHeight(fontFace: HersheyFont, italic: Boolean, pixelHeight: Int, thickness: Int): Double =
    opencv_imgproc.getFontScaleFromHeight(fontFace.face(italic), pixelHeight, thickness)

  def getFontScaleFromHeight(fontFace: HersheyFont, italic: Boolean, pixelHeight: Int): Double =
    getFontScaleFromHeight(fontFace, italic, pixelHeight, thickness = 1)

  def clipLine(imgSize: Size, pt1: Point, pt2: Point): Boolean =
    opencv_imgproc.clipLine(imgSize, pt1, pt2)

  def clipLine(imgRect: Rect, pt1: Point, pt2: Point): Boolean =
    opencv_imgproc.clipLine(imgRect, pt1, pt2)

  def ellipse2Poly(center: Point, axes: Size, angle: Int, arcStart: Int, arcEnd: Int, delta: Int): Seq[Point] = {
    val pointVector = new PointVector()
    opencv_imgproc.ellipse2Poly(center, axes, angle, arcStart, arcEnd, delta, pointVector)
    pointVector
  }

  def ellipse2Poly(center: Point2d, axes: Size2d, angle: Int, arcStart: Int, arcEnd: Int, delta: Int): Seq[Point2d] = {
    val pt2dVector = new Point2dVector()
    opencv_imgproc.ellipse2Poly(center, axes, angle, arcStart, arcEnd, delta, pt2dVector)
    pt2dVector
  }

  implicit class ImageDrawing(image: Image) {
    import i4s.opencv.core.model.mats.syntax._

    def circle(center: Point, radius: Int, color: Scalar, thickness: Int, lineType: LineType, shift: Int): Unit =
      opencv_imgproc.circle(image, center, radius, color, thickness, lineType.id, shift)

    def circle(center: Point, radius: Int, color: Scalar): Unit =
      circle(center, radius, color, thickness = 1, LineTypes.Line8, shift = 0)

    def ellipse(center: Point, axes: Size, angle: Double, startAngle: Double, endAngle: Double, color: Scalar, thickness: Int, lineType: LineType, shift: Int): Unit =
      opencv_imgproc.ellipse(image,center,axes,angle,startAngle,endAngle,color,thickness,lineType.id,shift)

    def ellipse(center: Point, axes: Size, angle: Double, startAngle: Double, endAngle: Double, color: Scalar): Unit =
      ellipse(center,axes,angle,startAngle,endAngle,color,thickness = 1,LineTypes.Line8,shift = 0)

    def ellipse(box: RotatedRect, color: Scalar, thickness: Int, lineType: LineType): Unit =
      opencv_imgproc.ellipse(image,box,color,thickness,lineType.id)

    def ellipse(box: RotatedRect, color: Scalar): Unit =
      ellipse(box,color,thickness = 1,LineTypes.Line8)

    def rectangle(point1: Point, point2: Point, color: Scalar, lineType: LineType, thickness: Int, shift: Int): Unit =
      opencv_imgproc.rectangle(image, point1, point2, color, thickness, lineType.id, shift)

    def rectangle(point1: Point, point2: Point, color: Scalar): Unit =
      rectangle(point1, point2, color, LineTypes.Line8, thickness = 1, shift = 0)

    def rectangle(rect: Rect, color: Scalar, lineType: LineType, thickness: Int, shift: Int): Unit =
      opencv_imgproc.rectangle(image, rect, color, thickness, lineType.id, shift)

    def rectangle(rect: Rect, color: Scalar): Unit =
      rectangle(rect,color,LineTypes.Line8,thickness = 1,shift = 0)

    def drawMarker(position: Point, color: Scalar, markerType: MarkerType, markerSize: Int, thickness: Int, lineType: LineType): Unit =
      opencv_imgproc.drawMarker(image,position,color,markerType.id,markerSize,thickness,lineType.id)

    def drawMarker(position: Point, color: Scalar): Unit =
      drawMarker(position,color,MarkerTypes.Cross,markerSize = 20,thickness = 1,LineTypes.Line8)

    def putText(text: String, org: Point, fontFace: HersheyFont, fontScale: Double, italic: Boolean, color: Scalar, thickness: Int, lineType: LineType, bottomLeftOrigin: Boolean): Unit =
      opencv_imgproc.putText(image,text,org,fontFace.face(italic),fontScale,color,thickness,lineType.id,bottomLeftOrigin)

    def putText(text: String, org: Point, fontFace: HersheyFont, fontScale: Double, italic: Boolean, color: Scalar): Unit =
      putText(text,org,fontFace,fontScale,italic,color,1,LineTypes.Line8,bottomLeftOrigin = false)

    def fillConvexPoly(points: Seq[Point], color: Scalar, lineType: LineType, shift: Int): Unit = {
      val ptMat = MappedMat[Point,Int](points.size,Some(Types.Cv32S),Some(2))
      ptMat.put(0,points)
      opencv_imgproc.fillConvexPoly(image,ptMat,color,lineType.id,shift)
    }

    def fillConvexPoly(points: Seq[Point], color: Scalar): Unit =
      fillConvexPoly(points,color,LineTypes.Line8,shift = 0)

    def fillPoly(pts: Seq[Point], color: Scalar, lineType: LineType, shift: Int, offset: Point): Unit = {
      val ptMat = MappedMat[Point, Int](pts.size, Some(Types.Cv32S), Some(2))
      ptMat.put(0,pts)
      opencv_imgproc.fillPoly(image, Seq(ptMat), color, lineType.id, shift, offset)
    }

    def fillPoly(pts: Seq[Point], color: Scalar): Unit =
      fillPoly(pts, color, LineTypes.Line8, shift = 0, offset = Point())

    def fillPolyList(pts: Seq[Seq[Point]], color: Scalar, lineType: LineType, shift: Int, offset: Point): Unit = {
      val ptMats = pts.map { pointSeq =>
        val ptMat = MappedMat[Point,Int](pointSeq.size,Some(Types.Cv32S),Some(2))
        ptMat.put(0,pointSeq)
        ptMat
      }
      opencv_imgproc.fillPoly(image,ptMats,color,lineType.id,shift,offset)
    }

    def fillPolyList(pts: Seq[Seq[Point]], color: Scalar): Unit =
    fillPolyList(pts, color, LineTypes.Line8, shift = 0, offset = Point())

    def polyline(pts: Seq[Point], isClosed: Boolean, color: Scalar, thickness: Int, lineType: LineType, shift: Int): Unit = {
      val ptMat = MappedMat[Point, Int](pts.size, Some(Types.Cv32S), Some(2))
      ptMat.put(0, pts)
      opencv_imgproc.polylines(image,Seq(ptMat),isClosed,color,thickness,lineType.id,shift)
    }

    def polyline(pts: Seq[Point], isClosed: Boolean, color: Scalar): Unit =
      polyline(pts,isClosed,color,thickness = 1,LineTypes.Line8,shift = 0)

    def polylines(pts: Seq[Seq[Point]], isClosed: Boolean, color: Scalar, thickness: Int, lineType: LineType, shift: Int): Unit = {
      val ptMats = pts.map { pointSeq =>
        val ptMat = MappedMat[Point, Int](pointSeq.size, Some(Types.Cv32S), Some(2))
        ptMat.put(0, pointSeq)
        ptMat
      }
      opencv_imgproc.polylines(image,ptMats,isClosed,color,thickness,lineType.id,shift)
    }

    def polylines(pts: Seq[Seq[Point]], isClosed: Boolean, color: Scalar): Unit =
      polylines(pts,isClosed,color,thickness = 1,LineTypes.Line8,shift = 0)

    def drawContour(contour: Seq[Point], color: Scalar, thickness: Int, lineType: LineType, hierarchy: Mat[Int], maxLevel: Int, offset: Point): Unit = {
      val contourMat = MappedMat[Point, Int](contour.size, Some(Types.Cv32S), Some(2))
      contourMat.put(0,contour)
      opencv_imgproc.drawContours(image,Seq(contourMat),0,color,thickness,lineType.id,hierarchy,maxLevel,offset)
    }

    def drawContour(contour: Seq[Point], color: Scalar): Unit =
      drawContour(contour,color,thickness = 1,LineTypes.Line8,hierarchy = Mat[Int](0,0),maxLevel = Int.MaxValue,offset = Point())

    def drawContours(contours: Seq[Seq[Point]], color: Scalar, thickness: Int, lineType: LineType, hierarchy: Mat[Int], maxLevel: Int, offset: Point): Unit = {
      val contourMats = contours.map { pointSeq =>
        val ptMat = MappedMat[Point, Int](pointSeq.size, Some(Types.Cv32S), Some(2))
        ptMat.put(0, pointSeq)
        ptMat
      }
      opencv_imgproc.drawContours(image,contourMats,-1,color,thickness,lineType.id,hierarchy,maxLevel,offset)
    }

    def drawContours(contours: Seq[Seq[Point]], color: Scalar): Unit =
      drawContours(contours,color,thickness = 1,LineTypes.Line8,hierarchy = Mat[Int](0,0),maxLevel = Int.MaxValue,offset = Point())

  }
}
