package i4s.opencv.image

import i4s.opencv.core.constants.BorderTypes
import i4s.opencv.core.constants.BorderTypes.BorderType
import i4s.opencv.core.model.{Point, Point2f, Point3d, Point3f, Scalar4f, Scalar4i, Size}
import i4s.opencv.core.model.mats.{MappedMat, Mat}
import i4s.opencv.image.constants.HoughModes.HoughMode
import i4s.opencv.image.constants.LineSegmentDetectorModes
import i4s.opencv.image.constants.LineSegmentDetectorModes.LineSegmentDetectorMode
import org.bytedeco.opencv.opencv_imgproc.{LineSegmentDetector, Vec2fVector, Vec3fVector, Vec4fVector, Vec4iVector}
import org.bytedeco.opencv.global.opencv_imgproc
import org.bytedeco.opencv.opencv_core.TermCriteria


object Features extends Features

trait Features {

  def createLineSegmentDetector(mode: LineSegmentDetectorMode, scale: Double, sigmaScale: Double, quantizationLimit: Double,
                                angleTheta: Double, logEpsilon: Double, densityTheta: Double, numberOfBins: Int): LineSegmentDetector =
    opencv_imgproc.createLineSegmentDetector(mode.id,scale,sigmaScale,quantizationLimit,angleTheta,logEpsilon,densityTheta,numberOfBins)

  def createLineSegmentDetector(): LineSegmentDetector =
    createLineSegmentDetector(LineSegmentDetectorModes.StandardRefinement,0.8d,0.6d,2.0d,22.5d,0,0.7d,1024)

  def canny(dx: Image, dy: Image, threshold1: Double, threshold2: Double, l2Gradient: Boolean): Image = {
    val edges = new org.bytedeco.opencv.opencv_core.Mat()
    opencv_imgproc.Canny(dx, dy, edges, threshold1, threshold2, l2Gradient)
    edges
  }

  def canny(dx: Image, dy: Image, threshold1: Double, threshold2: Double): Image =
    canny(dx, dy, threshold1, threshold2, l2Gradient = false)

  def houghLinesPointSet(points: MappedMat[Point,Int], maxLines: Int, threshold: Int, minRho: Double, maxRho: Double, rhoStep: Double, minTheta: Double, maxTheta: Double, thetaStep: Double): MappedMat[Point3d,Double] = {
    import i4s.opencv.core.model.mats.syntax._
    val lines = new org.bytedeco.opencv.opencv_core.Mat()
    opencv_imgproc.HoughLinesPointSet(points,lines,maxLines,threshold,minRho,maxRho,rhoStep,minTheta,maxTheta,thetaStep)
    new MappedMat[Point3d,Double](lines)
  }

  def houghLinesPoint2fSet(points: MappedMat[Point2f,Float], maxLines: Int, threshold: Int, minRho: Double, maxRho: Double, rhoStep: Double, minTheta: Double, maxTheta: Double, thetaStep: Double): MappedMat[Point3d,Double] = {
    import i4s.opencv.core.model.mats.syntax._
    val lines = new org.bytedeco.opencv.opencv_core.Mat()
    opencv_imgproc.HoughLinesPointSet(points,lines,maxLines,threshold,minRho,maxRho,rhoStep,minTheta,maxTheta,thetaStep)
    new MappedMat[Point3d,Double](lines)
  }

  implicit class ImageFeatures(image: Image) {

    def canny(threshold1: Double, threshold2: Double, apertureSize: Int, l2Gradiant: Boolean): Image = {
      val edges = new org.bytedeco.opencv.opencv_core.Mat()
      opencv_imgproc.Canny(image,edges,threshold1,threshold2,apertureSize,l2Gradiant)
      edges
    }

    def canny(threshold1: Double, threshold2: Double): Image =
      canny(threshold1,threshold2,apertureSize = 3,l2Gradiant = false)

    def cornerMinEigenVal(blockSize: Int, ksize: Int, borderType: BorderType): Mat[Float] = {
      import i4s.opencv.core.model.mats.syntax._

      val dst = new org.bytedeco.opencv.opencv_core.Mat()
      opencv_imgproc.cornerMinEigenVal(image,dst,blockSize,ksize,borderType.id)
      new Mat[Float](dst)
    }

    def cornerMinEigenVal(blockSize: Int): Mat[Float] =
      cornerMinEigenVal(blockSize,3,BorderTypes.Default)

    def cornerHarris(blockSize: Int, ksize: Int, k: Double, borderType: BorderType): Image = {
      val dst = new org.bytedeco.opencv.opencv_core.Mat()
      opencv_imgproc.cornerHarris(image,dst,blockSize,ksize,k,borderType.id)
      dst
    }

    def cornerHarris(blockSize: Int, ksize: Int, k: Double): Image =
      cornerHarris(blockSize,ksize,k,BorderTypes.Default)

    def cornerEigenValuesAndVectors(blockSize: Int, ksize: Int, borderType: BorderType): MappedMat[IndexedSeq[Float],Float] = {
      import i4s.opencv.core.model.mats.syntax._

      val dst = new org.bytedeco.opencv.opencv_core.Mat()
      opencv_imgproc.cornerEigenValsAndVecs(image,dst,blockSize,ksize,borderType.id)
      new MappedMat[IndexedSeq[Float],Float](dst)
    }

    def cornerEigenValuesAndVectors(blockSize: Int, ksize: Int): MappedMat[IndexedSeq[Float],Float] =
      cornerEigenValuesAndVectors(blockSize,ksize,borderType = BorderTypes.Default)

    def preCornerDetect(ksize: Int, borderType: BorderType): Mat[Float] = {
      import i4s.opencv.core.model.mats.syntax._
      val dst = new org.bytedeco.opencv.opencv_core.Mat()
      opencv_imgproc.preCornerDetect(image,dst,ksize,borderType.id)
      new Mat[Float](dst)
    }

    def preCornerDetect(ksize: Int): Unit = preCornerDetect(ksize,BorderTypes.Default)

    def cornerSubPix(corners: MappedMat[Point2f,Float], winSize: Size, zeroZone: Size, criteria: TermCriteria): MappedMat[Point2f,Float] = {
      opencv_imgproc.cornerSubPix(image,corners,winSize,zeroZone,criteria)
      corners
    }

   def goodFeaturesToTrack(maxCorners: Int, qualityLevel: Double, minDistance: Double, mask: Image, blockSize: Int, useHarrisDetector: Boolean, k: Double /*=0.04*/): MappedMat[Point2f,Float] = {
      import i4s.opencv.core.model.mats.syntax._
      val dst = new org.bytedeco.opencv.opencv_core.Mat()
      opencv_imgproc.goodFeaturesToTrack(image,dst,maxCorners,qualityLevel,minDistance,mask,blockSize,useHarrisDetector,k)
      new MappedMat[Point2f,Float](dst)
    }

    def goodFeaturesToTrack(maxCorners: Int, qualityLevel: Double, minDistance: Double): MappedMat[Point2f, Float] =
      goodFeaturesToTrack(maxCorners, qualityLevel, minDistance, new org.bytedeco.opencv.opencv_core.Mat(), blockSize = 3, useHarrisDetector = false, k = 0.04)

    def goodFeaturesToTrack(maxCorners: Int, qualityLevel: Double, minDistance: Double, mask: Image, blockSize: Int, gradientSize: Int, useHarrisDetector: Boolean, k: Double): MappedMat[Point2f,Float] = {
      import i4s.opencv.core.model.mats.syntax._
      val dst = new org.bytedeco.opencv.opencv_core.Mat()
      opencv_imgproc.goodFeaturesToTrack(image, dst, maxCorners, qualityLevel, minDistance, mask, blockSize, gradientSize, useHarrisDetector, k)
      new MappedMat[Point2f, Float](dst)
    }

    def goodFeaturesToTrack(maxCorners: Int, qualityLevel: Double, minDistance: Double, mask: Image, blockSize: Int, gradientSize: Int): MappedMat[Point2f,Float] =
      goodFeaturesToTrack(maxCorners, qualityLevel, minDistance, mask, blockSize, gradientSize, k = 0.04, useHarrisDetector = false)

    def goodFeaturesToTrackWithQuality(maxCorners: Int, qualityLevel: Double, minDistance: Double, mask: Image, blockSize: Int, gradientSize: Int, useHarrisDetector: Boolean, k: Double): (MappedMat[Point2f,Float],Mat[Float]) = {
      import i4s.opencv.core.model.mats.syntax._
      val dst = new org.bytedeco.opencv.opencv_core.Mat()
      val quality = new org.bytedeco.opencv.opencv_core.Mat()
      opencv_imgproc.goodFeaturesToTrackWithQuality(image, dst, maxCorners, qualityLevel, minDistance, mask, quality, blockSize, gradientSize, useHarrisDetector, k)
      (new MappedMat[Point2f, Float](dst),new Mat[Float](quality))
    }

    def goodFeaturesToTrackWithQuality(maxCorners: Int, qualityLevel: Double, minDistance: Double, mask: Image): (MappedMat[Point2f,Float],Mat[Float]) =
      goodFeaturesToTrackWithQuality(maxCorners,qualityLevel,minDistance,mask,blockSize = 3,gradientSize = 3,useHarrisDetector = false,k = 0.04)

    def houghLines(rho: Double, theta: Double, threshold: Int, srn: Double /*=0*/ , stn: Double, minTheta: Double, maxTheta: Double): Seq[Point2f] = {
      val vec2fVector = new Vec2fVector()
      opencv_imgproc.HoughLines(image,vec2fVector,rho,theta,threshold,srn,stn,minTheta,maxTheta)
      vec2fVector
    }

    def houghLinesWithVotes(rho: Double, theta: Double, threshold: Int, srn: Double, stn: Double, minTheta: Double, maxTheta: Double): Seq[Point3f] = {
      val vec3fVector = new Vec3fVector()
      opencv_imgproc.HoughLines(image,vec3fVector,rho,theta,threshold,srn,stn,minTheta,maxTheta)
      vec3fVector
    }

    def probabilisticHoughLines(rho: Double, theta: Double, threshold: Int, minLineLength: Double, maxLineGap: Double): Seq[Scalar4i] = {
      val lines = new Vec4iVector()
      opencv_imgproc.HoughLinesP(image,lines,rho,theta,threshold,minLineLength,maxLineGap)
      lines
    }

    def houghCircles(method: HoughMode, dp: Double, minDist: Double, param1: Double, param2: Double, minRadius: Int, maxRadius: Int): Seq[Point3f] = {
      val circles = new Vec3fVector()
      opencv_imgproc.HoughCircles(image,circles,method.id,dp,minDist,param1,param2,minRadius,maxRadius)
      circles
    }

    def houghCirclesWithVotes(method: HoughMode, dp: Double, minDist: Double, param1: Double, param2: Double, minRadius: Int, maxRadius: Int): Seq[Scalar4f] = {
      val circles = new Vec4fVector()
      opencv_imgproc.HoughCircles(image,circles,method.id,dp,minDist,param1,param2,minRadius,maxRadius)
      circles
    }

  }
}

