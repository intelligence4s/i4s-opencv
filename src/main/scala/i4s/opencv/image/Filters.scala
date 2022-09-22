package i4s.opencv.image

import i4s.opencv.core.constants.BorderTypes.BorderType
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types._
import i4s.opencv.core.constants.BorderTypes
import i4s.opencv.core.constants.CvPi
import i4s.opencv.image.constants.MorphShapes.MorphShape
import org.bytedeco.opencv.global.opencv_imgproc
import org.bytedeco.opencv.opencv_core._

object Filters extends Filters

trait Filters {
  def getGaussianKernel(ksize: Int, sigma: Double, ktype: Type): Mat =
      opencv_imgproc.getGaussianKernel(ksize,sigma,ktype.id)

  def getGaussianKernel(ksize: Int, sigma: Double): Mat =
    getGaussianKernel(ksize,sigma,Types.Cv64F)

  def getDerivKernels(dx: Int, dy: Int, ksize: Int, normalize: Boolean, ktype: Type): (Mat,Mat) = {
    val kx, ky: Mat = new Mat()
    opencv_imgproc.getDerivKernels(kx,ky,dx,dy,ksize,normalize,ktype.id)
    (kx,ky)
  }

  def getDerivKernels(dx: Int, dy: Int, ksize: Int): (Mat,Mat) =
    getDerivKernels(dx,dy,ksize,normalize = false,Types.Cv32F)

  def getGaborKernel(ksize: Size, sigma: Double, theta: Double, lambd: Double, gamma: Double, psi: Double, ktype: Type): Mat =
    opencv_imgproc.getGaborKernel(ksize,sigma,theta,lambd,gamma,psi,ktype.id)

  def getGaborKernel(ksize: Size, sigma: Double, theta: Double, lambd: Double, gamma: Double): Mat =
    getGaborKernel(ksize,sigma,theta,lambd,gamma,psi = CvPi * 0.5, Cv64F)

  def morphologyDefaultBorderValue: Scalar = opencv_imgproc.morphologyDefaultBorderValue()

  def getStructuringElement(shape: MorphShape, ksize: Size, anchor: Point): Mat =
    opencv_imgproc.getStructuringElement(shape.id,ksize,anchor)

  def getStructuringElement(shape: MorphShape, ksize: Size): Mat =
    getStructuringElement(shape,ksize,new Point(-1,-1))

  implicit class ImageFilters(image: Image) {

    def medianBlur(ksize: Int): Image = {
      val dst = new Mat()
      opencv_imgproc.medianBlur(image,dst,ksize)
      dst
    }

    def gaussianBlur(ksize: Size, sigmaX: Double, sigmaY: Double, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.GaussianBlur(image,dst,ksize,sigmaX,sigmaY,borderType.id)
      dst
    }

    def gaussianBlur(ksize: Size, sigmaX: Double): Image =
      gaussianBlur(ksize,sigmaX,sigmaY = -1,BorderTypes.Default)

    def bilateralFilter(d: Int, sigmaColor: Double, sigmaSpace: Double, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.bilateralFilter(image,dst,d,sigmaColor,sigmaSpace,borderType.id)
      dst
    }

    def bilateralFilter(d: Int, sigmaColor: Double, sigmaSpace: Double): Image =
      bilateralFilter(d, sigmaColor, sigmaSpace, BorderTypes.Default)

    def boxFilter(ddepth: Type, ksize: Size, anchor: Point, normalize: Boolean, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.boxFilter(image,dst,ddepth.id,ksize,anchor,normalize,borderType.id)
      dst
    }

    def boxFilter(ddepth: Type, ksize: Size): Image =
      boxFilter(ddepth,ksize,new Point(-1,-1),normalize = true,BorderTypes.Default)

    def squareBoxFilter(ddepth: Type, ksize: Size, anchor: Point, normalize: Boolean, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.sqrBoxFilter(image,dst,ddepth.id,ksize,anchor,normalize,borderType.id)
      dst
    }

    def squareBoxFilter(ddepth: Type, ksize: Size): Image =
      squareBoxFilter(ddepth,ksize,new Point(-1,-1),normalize = true,BorderTypes.Default)

    def blur(ksize: Size, anchor: Point, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.blur(image,dst,ksize,anchor,borderType.id)
      dst
    }

    def blur(ksize: Size): Image = blur(ksize,new Point(-1,-1),BorderTypes.Default)

    def filter2D(ddepth: Type, kernel: Mat, anchor: Point, delta: Double, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.filter2D(image,dst,ddepth.id,kernel,anchor,delta,borderType.id)
      dst
    }

    def filter2D(ddepth: Type, kernel: Mat): Image =
      filter2D(ddepth,kernel,new Point(-1,-1),delta = 0,BorderTypes.Default)

    def sepFilter2D(ddepth: Type, kernelX: Mat, kernelY: Mat, anchor: Point, delta: Double, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.sepFilter2D(image,dst,ddepth.id,kernelX,kernelY,anchor,delta,borderType.id)
      dst
    }

    def sepFilter2D(ddepth: Type, kernelX: Mat, kernelY: Mat): Image =
      sepFilter2D(ddepth,kernelX,kernelY,new Point(-1,-1),0,BorderTypes.Default)

    def sobel(ddepth: Type, dx: Int, dy: Int, ksize: Int /*=3*/ , scale: Double /*=1*/ , delta: Double, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.Sobel(image,dst,ddepth.id,dx,dy,ksize,scale,delta,borderType.id)
      dst
    }

    def sobel(ddepth: Type, dx: Int, dy: Int): Image =
      sobel(ddepth,dx,dy,ksize = 3,scale = 1,delta = 0,BorderTypes.Default)

    def spatialGradient(ksize: Int, borderType: BorderType): (Image,Image) = {
      val dx: Image = new Mat()
      val dy: Image = new Mat()
      opencv_imgproc.spatialGradient(image,dx,dy,ksize,borderType.id)
      (dx,dy)
    }

    def spatialGradient(): (Image,Image) =
      spatialGradient(ksize = 3,BorderTypes.Default)

    def scharr(ddepth: Type, dx: Int, dy: Int, scale: Double, delta: Double, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.Scharr(image,dst,ddepth.id,dx,dy,scale,delta,borderType.id)
      dst
    }

    def scharr(ddepth: Type, dx: Int, dy: Int): Image =
      scharr(ddepth,dx,dy,scale = 1,delta = 0,BorderTypes.Default)

    def laplacian(ddepth: Type, ksize: Int, scale: Double, delta: Double, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.Laplacian(image,dst,ddepth.id,ksize,scale,delta,borderType.id)
      dst
    }

    def laplacian(ddepth: Type): Image =
      laplacian(ddepth,ksize = 1,scale = 1,delta = 0,BorderTypes.Default)

    def erode(kernel: Mat, anchor: Point, iterations: Int, borderType: BorderType, borderValue: Scalar): Image = {
      val dst = new Mat()
      opencv_imgproc.erode(image,dst,kernel,anchor,iterations,borderType.id,borderValue)
      dst
    }

    def erode(kernel: Mat): Image =
      erode(kernel,new Point(-1,-1),iterations = 1,BorderTypes.Constant,new Scalar(morphologyDefaultBorderValue))

    def dilate(kernel: Mat, anchor: Point, iterations: Int, borderType: BorderType, borderValue: Scalar): Image = {
      val dst = new Mat()
      opencv_imgproc.dilate(image,dst,kernel,anchor,iterations,borderType.id,borderValue)
      dst
    }

    def dilate(kernel: Mat): Image =
      dilate(kernel,new Point(-1,-1),iterations = 1,BorderTypes.Constant,new Scalar(morphologyDefaultBorderValue))

    def morphologyEx(op: Int, kernel: Mat, anchor: Point, iterations: Int, borderType: BorderType, borderValue: Scalar): Image = {
      val dst = new Mat()
      opencv_imgproc.morphologyEx(image,dst,op,kernel,anchor,iterations,borderType.id,borderValue)
      dst
    }

    def morphologyEx(op: Int, kernel: Mat): Image =
      morphologyEx(op,kernel,new Point(-1,-1),iterations = 1,BorderTypes.Constant,new Scalar(morphologyDefaultBorderValue))

    def pyrDown(dstsize: Size, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.pyrDown(image,dst,dstsize,borderType.id)
      dst
    }

    def pyrDown(): Image = pyrDown(new Size(),BorderTypes.Default)

    def pyrUp(dstsize: Size, borderType: BorderType): Image = {
      val dst = new Mat()
      opencv_imgproc.pyrUp(image,dst,dstsize,borderType.id)
      dst
    }

    def pyrUp(): Image = pyrUp(dstsize = new Size(),BorderTypes.Default)

    def buildPyramid(maxlevel: Int, borderType: BorderType): MatVector = {
      val dst = new MatVector()
      opencv_imgproc.buildPyramid(image,dst,maxlevel,borderType.id)
      dst
    }

    def buildPyramid(maxlevel: Int): MatVector =
      buildPyramid(maxlevel, BorderTypes.Default)


    def pyrMeanShiftFiltering(sp: Double, sr: Double, maxlevel: Int, termCriteria: TermCriteria): Image = {
      val dst = new Mat()
      opencv_imgproc.pyrMeanShiftFiltering(image,dst,sp,sr,maxlevel,termCriteria)
      dst
    }

    def pyrMeanShiftFiltering(sp: Double, sr: Double): Image =
      pyrMeanShiftFiltering(sp,sr,1,new TermCriteria(TermCriteria.MAX_ITER + TermCriteria.EPS,5,1))
  }
}

