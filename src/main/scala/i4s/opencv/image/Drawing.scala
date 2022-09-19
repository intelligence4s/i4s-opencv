package i4s.opencv.image

import i4s.opencv.image.constants.HersheyFonts.HersheyFont
import i4s.opencv.image.constants.LineTypes.LineType
import i4s.opencv.image.constants.MarkerTypes.MarkerType
import i4s.opencv.image.constants.{LineTypes, MarkerTypes}
import org.bytedeco.opencv.global.opencv_imgproc
import org.bytedeco.opencv.opencv_core._

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

  def ellipse2Poly(center: Point, axes: Size, angle: Int, arcStart: Int, arcEnd: Int, delta: Int): PointVector = {
    val pointVector = new PointVector()
    opencv_imgproc.ellipse2Poly(center, axes, angle, arcStart, arcEnd, delta, pointVector)
    pointVector
  }


  def ellipse2Poly(center: Point2d, axes: Size2d, angle: Int, arcStart: Int, arcEnd: Int, delta: Int): Point2dVector = {
    val pt2dVector = new Point2dVector()
    opencv_imgproc.ellipse2Poly(center, axes, angle, arcStart, arcEnd, delta, pt2dVector)
    pt2dVector
  }

  implicit class ImageDrawing(image: Image) {
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

    def fillConvexPoly(points: Mat, color: Scalar, lineType: LineType, shift: Int): Unit =
      opencv_imgproc.fillConvexPoly(image,points,color,lineType.id,shift)

    def fillConvexPoly(points: Mat, color: Scalar): Unit =
      fillConvexPoly(points,color,LineTypes.Line8,shift = 0)

    def fillPoly(pts: Seq[Mat], color: Scalar, lineType: LineType, shift: Int, offset: Point): Unit =
      opencv_imgproc.fillPoly(image,new MatVector(pts:_*),color,lineType.id,shift,offset)

    def fillPoly(pts: Seq[Mat], color: Scalar): Unit =
      fillPoly(pts, color, LineTypes.Line8, shift = 0, offset = new Point())

    def fillPoly(pts: Array[Point], npts: Int, ncontours: Int, color: Scalar, lineType: LineType, shift: Int, offset: Point): Unit = {
      opencv_imgproc.fillPoly(image,pts.asPointer,npts.asPointer,ncontours,color,lineType.id,shift,offset)
    }

    def polylines(pts: Seq[Mat], isClosed: Boolean, color: Scalar, thickness: Int, lineType: LineType, shift: Int): Unit =
      opencv_imgproc.polylines(image,new MatVector(pts:_*),isClosed,color,thickness,lineType.id,shift)

    def polylines(pts: Seq[Mat], isClosed: Boolean, color: Scalar): Unit =
      polylines(pts,isClosed,color,thickness = 1,LineTypes.Line8,shift = 0)

    def polylines(pts: Array[Point], npts: Int, ncontours: Int, isClosed: Boolean, color: Scalar, thickness: Int, lineType: LineType, shift: Int): Unit =
      opencv_imgproc.polylines(image,pts.asPointer,npts.asPointer,ncontours,isClosed,color,thickness,lineType.id,shift)

    def drawContours(contours: Seq[Mat], contourIdx: Int, color: Scalar, thickness: Int, lineType: LineType, hierarchy: Mat, maxLevel: Int, offset: Point): Unit =
      opencv_imgproc.drawContours(image,new MatVector(contours:_*),contourIdx,color,thickness,lineType.id,hierarchy,maxLevel,offset)

    def drawContours(contours: Seq[Mat], contourIdx: Int, color: Scalar): Unit =
      drawContours(contours,contourIdx,color,thickness = 1,LineTypes.Line8,hierarchy = new Mat(),maxLevel = Int.MaxValue,offset = new Point())

  }
}
