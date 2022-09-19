package i4s.opencv.image

import i4s.opencv.core.constants.BorderTypes.BorderType
import i4s.opencv.core.constants.DecompositionMethods.DecompositionMethod
import i4s.opencv.core.constants.{BorderTypes, DecompositionMethods}
import i4s.opencv.core.types.MatTypes.MatType
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type
import i4s.opencv.image.constants.DistanceLabelTypes.DistanceLabelType
import i4s.opencv.image.constants.DistanceTypes.DistanceType
import i4s.opencv.image.constants.InterpolationFlags.InterpolationFlag
import i4s.opencv.image.constants.ThresholdMethods.ThresholdMethod
import i4s.opencv.image.constants.ThresholdTypes.ThresholdType
import i4s.opencv.image.constants.{DistanceLabelTypes, InterpolationFlags}
import org.bytedeco.opencv.global.opencv_imgproc
import org.bytedeco.opencv.opencv_core._

object Transforms extends Transforms

trait Transforms {
  def convertMaps(map1: Mat, map2: Mat, dstmap1: Mat, dstmap2: Mat, dstmap1type: MatType, nninterpolation: Boolean): Unit =
    opencv_imgproc.convertMaps(map1,map2,dstmap1,dstmap2,dstmap1type.id,nninterpolation)

  def convertMaps(map1: Mat, map2: Mat, dstmap1: Mat, dstmap2: Mat, dstmap1type: MatType): Unit =
    convertMaps(map1,map2,dstmap1,dstmap2,dstmap1type,nninterpolation = true)

  def getRotationMatrix2D(center: Point2f, angle: Double, scale: Double): Mat =
    opencv_imgproc.getRotationMatrix2D(center, angle, scale)

  def getAffineTransform(src: Point2f): (Mat, Point2f) = {
    val dst = new Point2f()
    val result = opencv_imgproc.getAffineTransform(src,dst)
    (result,dst)
  }

  def invertAffineTransform(affine: Mat): Mat = {
    val reverseAffine = new Mat()
    opencv_imgproc.invertAffineTransform(affine,reverseAffine)
    reverseAffine
  }

  def getPerspectiveTransform(src: Mat, dst: Mat, solveMethod: DecompositionMethod): Mat =
    opencv_imgproc.getPerspectiveTransform(src, dst, solveMethod.id)

  def getPerspectiveTransform(src: Mat, dst: Mat): Mat =
    getPerspectiveTransform(src, dst, DecompositionMethods.Lu)

  /** \overload */
  def getPerspectiveTransform(src: Point2f, dst: Point2f, solveMethod: DecompositionMethod): Mat =
    opencv_imgproc.getPerspectiveTransform(src, dst, solveMethod.id)

  def getPerspectiveTransform(src: Point2f, dst: Point2f): Mat =
    getPerspectiveTransform(src, dst, DecompositionMethods.Lu)

  def getAffineTransform(src: Mat, dst: Mat): Mat =
    opencv_imgproc.getAffineTransform(src, dst)

  implicit class ImageTransforms(image: Image) {
    def resize(dsize: Size, fx: Double, fy: Double, interpolation: InterpolationFlag): Image = {
      val dst = new Mat()
      opencv_imgproc.resize(image,dst,dsize,fx,fy,interpolation.id)
      dst
    }

    def resize(dsize: Size): Unit = resize(dsize, fx = 0, fy = 0, InterpolationFlags.Linear)

    def warpAffine(matrix: Mat, dsize: Size, flags: Set[InterpolationFlag], borderMode: BorderType, borderValue: Scalar): Image = {
      val iflags = flags.foldLeft(0)(_ | _.id)
      val dst = new Mat()
      opencv_imgproc.warpAffine(image,dst,matrix,dsize,iflags,borderMode.id,borderValue)
      dst
    }

    def warpAffine(matrix: Mat, dsize: Size): Image =
      warpAffine(matrix,dsize,Set(InterpolationFlags.Linear),BorderTypes.Constant,new Scalar())

    def warpPerspective(matrix: Mat, dsize: Size, flags: Set[InterpolationFlag], borderMode: BorderType, borderValue: Scalar): Image =
    {
      val iflags = flags.foldLeft(0)(_ | _.id)
      val dst = new Mat()
      opencv_imgproc.warpPerspective(image,dst,matrix,dsize,iflags,borderMode.id,borderValue)
      dst
    }

    def warpPerspective(matrix: Mat, dsize: Size): Image =
      warpPerspective(matrix,dsize,Set(InterpolationFlags.Linear),BorderTypes.Constant,new Scalar())

    def remap(map1: Mat, map2: Mat, interpolation: InterpolationFlag, borderMode: BorderType /*=cv::BORDER_CONSTANT*/, borderValue: Scalar): Image = {
      val dst = new Mat()
      opencv_imgproc.remap(image,dst,map1,map2,interpolation.id,borderMode.id,borderValue)
      dst
    }

    def remap(map1: Mat, map2: Mat, interpolation: InterpolationFlag): Image =
      remap(map1,map2,interpolation,BorderTypes.Constant,new Scalar())

    def rectSubPixels(patchSize: Size, center: Point2f, patchType: Type): Image = {
      val patch = new Mat()
      opencv_imgproc.getRectSubPix(image,patchSize,center,patch,patchType.id)
      patch
    }

    def rectSubPixels(patchSize: Size, center: Point2f): Image =
      rectSubPixels(patchSize,center,Types.CvUndefined)

    def linearPolar(center: Point2f, maxRadius: Double, flags: Set[InterpolationFlag]): Image = {
      val iflags = flags.foldLeft(0)(_ | _.id)
      val dst = new Mat()
      opencv_imgproc.linearPolar(image,dst,center,maxRadius,iflags)
      dst
    }

    def warpPolar(dsize: Size, center: Point2f, maxRadius: Double, flags: Set[InterpolationFlag]): Image = {
      val dst = new Mat()
      val iflags = flags.foldLeft(0)(_ | _.id)
      opencv_imgproc.warpPolar(image,dst,dsize,center,maxRadius,iflags)
      dst
    }

    def integral(sdepth: Int): Image = {
      val sum = new Mat()
      opencv_imgproc.integral(image, sum, sdepth)
      sum
    }

    def integral(): Image = integral(sdepth = -1)

    def integral2(sdepth: Int, sqdepth: Int): (Mat,Mat) = {
      val sum = new Mat()
      val sqsum = new Mat()
      opencv_imgproc.integral2(image,sum,sqsum,sdepth,sqdepth)
      (sum,sqsum)
    }

    def integral2(): (Mat,Mat) = integral2(sdepth = -1,sqdepth = -1)

    def integral3(sdepth: Int, sqdepth: Int): (Mat,Mat,Mat) = {
      val sum = new Mat()
      val sqsum = new Mat()
      val tilted = new Mat()
      opencv_imgproc.integral3(image,sum,sqsum,tilted,sdepth,sqdepth)
      (sum,sqsum,tilted)
    }

    def integral3(): (Mat,Mat,Mat) = integral3(sdepth = -1,sqdepth = -1)

    def threshold(thresh: Double, maxval: Double, thresholdType: Set[ThresholdType]): (Image,Double) = {
      val tflag = thresholdType.foldLeft(0)(_ | _.id)
      val dst = new Mat()
      val result = opencv_imgproc.threshold(image,dst,thresh,maxval,tflag)
      (dst,result)
    }

    def adaptiveThreshold(maxValue: Double, adaptiveMethod: ThresholdMethod, thresholdType: ThresholdType, blockSize: Int, constant: Double): Image = {
      val dst = new Mat()
      opencv_imgproc.adaptiveThreshold(image,dst,maxValue,adaptiveMethod.id,thresholdType.id,blockSize,constant)
      dst
    }

    def distanceTransformWithLabels(labels: Image, distanceType: DistanceType, maskSize: Int, labelType: DistanceLabelType): Image = {
      val dst = new Mat()
      opencv_imgproc.distanceTransformWithLabels(image,dst,labels,distanceType.id,maskSize,labelType.id)
      dst
    }

    def distanceTransformWithLabels(labels: Image, distanceType: DistanceType, maskSize: Int): Image =
      distanceTransformWithLabels(labels,distanceType,maskSize,DistanceLabelTypes.ConnectedComponent)

    def distanceTransform(distanceType: DistanceType, maskSize: Int, dstType: Type): Image = {
      val dst = new Mat()
      opencv_imgproc.distanceTransform(image,dst,distanceType.id,maskSize,dstType.id)
      dst
    }

    def distanceTransform(distanceType: DistanceType, maskSize: Int): Image =
      distanceTransform(distanceType,maskSize,Types.Cv32F)

    def floodFill(mask: Image, seedPoint: Point, newVal: Scalar, loDiff: Scalar, upDiff: Scalar, flags: Int): (Rect,Int) = {
      val rect = new Rect(0)
      val result = opencv_imgproc.floodFill(image,mask,seedPoint,newVal,rect,loDiff,upDiff,flags)
      (rect,result)
    }

    def floodFill(mask: Image, seedPoint: Point, newVal: Scalar): (Rect,Int) =
      floodFill(mask,seedPoint,newVal,new Scalar(),new Scalar(),flags = 4)

    def floodFill(seedPoint: Point, newVal: Scalar, loDiff: Scalar, upDiff: Scalar, flags: Int): (Rect,Int) = {
      val rect = new Rect(0)
      val result = opencv_imgproc.floodFill(image,seedPoint,newVal,rect,loDiff,upDiff,flags)
      (rect,result)
    }

    def floodFill(seedPoint: Point, newVal: Scalar): (Rect,Int) =
      floodFill(seedPoint, newVal, new Scalar(), new Scalar(), flags = 4)

    def blendLinear(src2: Image, weights1: Mat, weights2: Mat): Image = {
      val dst = new Mat()
      opencv_imgproc.blendLinear(image,src2,weights1,weights2,dst)
      dst
    }
  }
}



