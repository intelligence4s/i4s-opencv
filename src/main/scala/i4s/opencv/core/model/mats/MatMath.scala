package i4s.opencv.core.model.mats

import i4s.opencv.core.constants.CompareOps.CompareOp
import i4s.opencv.core.constants.CovariantFlags.CovariantFlag
import i4s.opencv.core.constants.DecompositionMethods.DecompositionMethod
import i4s.opencv.core.constants.DiscreteFourierTransformFlags.DiscreteFourierTransformFlag
import i4s.opencv.core.constants.FlipCodes.FlipCode
import i4s.opencv.core.constants.GeneralizedMatrixMultiplyFlags.GeneralizedMatrixMultiplyFlag
import i4s.opencv.core.constants.NormTypes.NormType
import i4s.opencv.core.constants.ReduceTypes.ReduceType
import i4s.opencv.core.constants.RotateCodes.RotateCode
import i4s.opencv.core.constants.SingularValueDecompositionFlags.SingularValueDecompositionFlag
import i4s.opencv.core.constants.SortFlags.SortFlag
import i4s.opencv.core.constants.{CompareOps, DecompositionMethods}
import i4s.opencv.core.model.{Point, Scalar}
import i4s.opencv.core.types.{MatTypes, Types}
import org.bytedeco.javacpp.{DoublePointer, IntPointer}
import org.bytedeco.opencv.global.opencv_core
import org.bytedeco.opencv.opencv_core.{MatVector, RNG}

import scala.reflect.ClassTag

object MatMath {

  def mixChannels[T <: AnyVal](sources: IndexedSeq[Mat[T]], targets: IndexedSeq[Mat[T]], map: IndexedSeq[(Int,Int)]): Unit = {
    opencv_core.mixChannels(new MatVector(sources:_*), new MatVector(targets:_*), new IntPointer(map.flatMap(t => Seq(t._1,t._2)):_*))
  }

  def withNewMat[T <: AnyVal, R](fn: org.bytedeco.opencv.opencv_core.Mat => R)(implicit matable: Matable[T], tag: ClassTag[T]): R = {
    val mat = new org.bytedeco.opencv.opencv_core.Mat(0,0,MatTypes.makeType(matable.depth,matable.channels))
    fn(mat)
  }

  def polarToCart[T <: AnyVal](magnitude: Mat[T], angle: Mat[T], angleInDegrees: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T]) = {
    withNewMat { x =>
      withNewMat { y =>
        opencv_core.polarToCart(magnitude, angle, x, y, angleInDegrees)
        (new Mat[T](x), new Mat[T](y))
      }
    }
  }

  def polarToCart[T <: AnyVal](angle: Mat[T], angleInDegrees: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T]) = {
    withNewMat { magnitude =>
      polarToCart(angle, new Mat[T](magnitude), angleInDegrees)
    }
  }

  def polarToCart[T <: AnyVal](angle: Mat[T], magnitude: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T]) =
    polarToCart(angle, magnitude, angleInDegrees = false)

  def polarToCart[T <: AnyVal](angle: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T]) = {
    withNewMat { magnitude =>
      polarToCart(angle, new Mat[T](magnitude))
    }
  }

  def cartToPolar[T <: AnyVal](x: Mat[T], y: Mat[T], angleInDegrees: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T]) = {
    withNewMat { magnitude =>
      withNewMat { angle =>
        opencv_core.cartToPolar(x, y, magnitude, angle, angleInDegrees)
        (new Mat[T](magnitude), new Mat[T](angle))
      }
    }
  }

  def cartToPolar[T <: AnyVal](x: Mat[T], y: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T]) =
    cartToPolar(x, y, angleInDegrees = false)

  def phase[T <: AnyVal](x: Mat[T], y: Mat[T], angleInDegrees: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    withNewMat { angle =>
      opencv_core.phase(x, y, angle, angleInDegrees)
      new Mat[T](angle)
    }
  }

  def phase[T <: AnyVal](x: Mat[T], y: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    phase(x, y, angleInDegrees = false)

  def magnitude[T <: AnyVal](x: Mat[T], y: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    withNewMat { magnitude =>
      opencv_core.magnitude(x, y, magnitude)
      new Mat[T](magnitude)
    }
  }

  def mulSpectrums[T <: AnyVal](a: Mat[T], b: Mat[T], flags: Seq[DiscreteFourierTransformFlag], conjB: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combined = flags.foldLeft(0)(_ | _.id)
    withNewMat { dst =>
      opencv_core.mulSpectrums(a, b, dst, combined, conjB)
      new Mat[T](dst)
    }
  }

  def mulSpectrums[T <: AnyVal](a: Mat[T], b: Mat[T], flags: Seq[DiscreteFourierTransformFlag])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combined = flags.foldLeft(0)(_ | _.id)
    withNewMat { dst =>
      opencv_core.mulSpectrums(a, b, dst, combined)
      new Mat[T](dst)
    }
  }

  def mahalanobis[T <: AnyVal](v1: Mat[T], v2: Mat[T], icovar: Mat[T]): Double =
    opencv_core.Mahalanobis(v1,v2,icovar)

  def svBackSubst[T <: AnyVal](w: Mat[T], u: Mat[T], vt: Mat[T], rhs: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    withNewMat { dst =>
      opencv_core.SVBackSubst(w, u, vt, rhs, dst)
      new Mat[T](dst)
    }
  }

  def getOptimalDFTSize(vecsize: Int): Int = opencv_core.getOptimalDFTSize(vecsize)

  def theRNG: RNG = opencv_core.theRNG()

  def setRNGSeed(seed: Int): Unit = opencv_core.setRNGSeed(seed)

}

trait MatMath[T <: AnyVal] {
  self: Mat[T] =>

  import MatMath._

/*
  def +(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.add(self, other, dst))

  def add(other: Mat[_ <: AnyVal], mask: Mat[Byte])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,depth) => opencv_core.add(self,other,dst,mask,depth))

  def add(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.add(self,other,dst))
*/

  def addTo[V <: AnyVal](other: Mat[_ <: AnyVal], mask: Mat[Byte])(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst,depth) => opencv_core.add(self,other,dst,mask,depth))

  def addTo[V <: AnyVal](other: Mat[_ <: AnyVal])(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst,_) => opencv_core.add(self,other,dst))

/*
  def -(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.subtract(self,other,dst))

  def subtract(other: Mat[_ <: AnyVal], mask: Mat[Byte])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,depth) => opencv_core.subtract(self,other,dst,mask,depth))

  def subtract(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.subtract(self,other,dst))
*/

  def subtractTo[V <: AnyVal](other: Mat[_ <: AnyVal], mask: Mat[Byte])(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst,depth) => opencv_core.subtract(self,other,dst,mask,depth))

  def subtractTo[V <: AnyVal](other: Mat[_ <: AnyVal])(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst,_) => opencv_core.subtract(self,other,dst))

  def x(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst, _) => opencv_core.multiply(self, other, dst))

  def mul(other: Mat[_ <: AnyVal], scaleFactor: Double)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,depth) => opencv_core.multiply(self,other,dst,scaleFactor,depth))

  def mul(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.multiply(self,other,dst))

  def mulTo[V <: AnyVal](other: Mat[_ <: AnyVal], scaleFactor: Double)(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst,depth) => opencv_core.multiply(self,other,dst,scaleFactor,depth))

  def mulTo[V <: AnyVal](other: Mat[_ <: AnyVal])(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst,_) => opencv_core.multiply(self,other,dst))

/*
  def /(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst, _) => opencv_core.divide(self, other, dst))

  def divide(other: Mat[_ <: AnyVal], scaleFactor: Double)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst, depth) => opencv_core.divide(self, other, dst, scaleFactor, depth))

  def divide(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst, _) => opencv_core.divide(self, other, dst))
*/

  def divideTo[V <: AnyVal](other: Mat[_ <: AnyVal], scaleFactor: Double)(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst, depth) => opencv_core.divide(self, other, dst, scaleFactor, depth))

  def divideTo[V <: AnyVal](other: Mat[_ <: AnyVal])(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst, _) => opencv_core.divide(self, other, dst))

  def addScaled(other: Mat[_ <: AnyVal], alpha: Double)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst, _) => opencv_core.scaleAdd(self, alpha, other, dst))

  def addWeighted(alpha: Double, other: Mat[_ <: AnyVal], beta: Double, gamma: Double)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst, _) => opencv_core.addWeighted(self, alpha, other, beta, gamma, dst))

  def addWeightedTo[V <: AnyVal](alpha: Double, other: Mat[_ <: AnyVal], beta: Double, gamma: Double)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst, depth) => opencv_core.addWeighted(self, alpha, other, beta, gamma, dst, depth))

  def convertScaleAbs(alpha: Double, beta: Double)(implicit matable: Matable[Int], tag: ClassTag[Int]): Mat[Int] =
    doItInShape[Int]((dst, _) => opencv_core.convertScaleAbs(self, dst, alpha, beta))

  def convertScaleAbs()(implicit matable: Matable[Int], tag: ClassTag[Int]): Mat[Int] =
    doItInShape[Int]((dst, _) => opencv_core.convertScaleAbs(self, dst))

  def lookupTransform(lut: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    assert(Types(self.depth) == Types.Cv8U, s"The lookup transform is valid for unsigned 8 bit matrices. this matrix is of ${Types(self.depth)}")
    doItInShape[T]((dst, _) => opencv_core.LUT(self,lut,dst))
  }

  def countNonZero(): Int = opencv_core.countNonZero(self)

  def sumElements(): Scalar = opencv_core.sumElems(self)

  def findNonZero(): Mat[Int] = {
    import i4s.opencv.core.model.mats.syntax._

    val indices = Mat[Int](0)
    opencv_core.findNonZero(self,indices)
    indices
  }

  def mean(mask: Mat[_ <: AnyVal]): Scalar = opencv_core.mean(self,mask)

  def mean(): Scalar = opencv_core.mean(self)

  def meansWithStdDeviations(mask: Mat[_ <: AnyVal]): (Mat[Double],Mat[Double]) = {
    import i4s.opencv.core.model.mats.syntax._

    val means = Mat[Double](0)
    val deviations = Mat[Double](0)
    opencv_core.meanStdDev(self,means,deviations,mask)
    (means,deviations)
  }

  def meansWithStdDeviations: (Mat[Double],Mat[Double]) = {
    import i4s.opencv.core.model.mats.syntax._

    val means = Mat[Double](0)
    val deviations = Mat[Double](0)
    opencv_core.meanStdDev(self,means,deviations)
    (means,deviations)
  }

  def norm(normType: NormType, mask: Mat[_ <: AnyVal]): Double = opencv_core.norm(self,normType.id,mask)

  def norm(): Double = opencv_core.norm(self)

  def norm(other: Mat[T], normType: NormType, mask: Mat[_ <: AnyVal]): Double = opencv_core.norm(self,other,normType.id,mask)

  def norm(other: Mat[T]): Double = opencv_core.norm(self,other)

  def peakSignalToNoiseRatio(other: Mat[T]): Double = opencv_core.PSNR(self,other)

  def peakSignalToNoiseRatio(other: Mat[T], r: Double): Double = opencv_core.PSNR(self,other,r)

  def batchDistance(other: Mat[T], normType: NormType, k: Int, mask: Mat[_ <: AnyVal], update: Int, crossCheck: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[Int]) = {
    import i4s.opencv.core.model.mats.syntax._

    val indices = Mat[Int](0)
    val dst = doItInShape[T]((dst,mtype) => opencv_core.batchDistance(self,other,dst,mtype,indices,normType.id,k,mask,update,crossCheck))
    (dst,indices)
  }

  def batchDistance(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[Int]) = {
    import i4s.opencv.core.model.mats.syntax._

    val indices = Mat[Int](0)
    val dst = doItInShape[T]((dst,mtype) => opencv_core.batchDistance(self,other,dst,mtype,indices))
    (dst,indices)
  }

  def batchDistanceTo[V <: AnyVal](other: Mat[T], normType: NormType, k: Int, mask: Mat[_ <: AnyVal], update: Int, crossCheck: Boolean)(implicit matable: Matable[V], tag: ClassTag[V]): (Mat[V], Mat[Int]) = {
    import i4s.opencv.core.model.mats.syntax._

    val indices = Mat[Int](0)
    val dst = doItInShape[V]((dst,mtype) => opencv_core.batchDistance(self,other,dst,mtype,indices,normType.id,k,mask,update,crossCheck))
    (dst,indices)
  }

  def batchDistanceTo[V <: AnyVal](other: Mat[T])(implicit matable: Matable[V], tag: ClassTag[V]): (Mat[V], Mat[Int]) = {
    import i4s.opencv.core.model.mats.syntax._

    val indices = Mat[Int](0)
    val dst = doItInShape[V]((dst,mtype) => opencv_core.batchDistance(self,other,dst,mtype,indices))
    (dst,indices)
  }

  def normalize(alpha: Double, beta: Double, normType: NormType, mask: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,mtype) => opencv_core.normalize(self,dst,alpha,beta,normType.id,mtype,mask))

  def normalize()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,mtype) => opencv_core.normalize(self,dst))

  def normalizeTo[V <: AnyVal](alpha: Double, beta: Double, normType: NormType, mask: Mat[_ <: AnyVal])(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst,mtype) => opencv_core.normalize(self,dst,alpha,beta,normType.id,mtype,mask))

  def normalizeTo[V <: AnyVal]()(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doItInShape[V]((dst,_) => opencv_core.normalize(self,dst))

  def minMaxWithLocation(mask: Mat[_ <: AnyVal]): (Double, Double, Point, Point) = {
    val minValue = new DoublePointer()
    val maxValue = new DoublePointer()
    val minLoc = Point()
    val maxLoc = Point()

    opencv_core.minMaxLoc(self,minValue,maxValue,minLoc,maxLoc,mask)
    (minValue.get(),maxValue.get(),minLoc,maxLoc)
  }

  def minMaxWithLocation(): (Double, Double, Point, Point) = {
    import i4s.opencv.core.model.mats.syntax._
    minMaxWithLocation(Mat[Int](0))
  }

  def minMax(mask: Mat[_ <: AnyVal]): (Double, Double) = {
    val (min,max,_,_) = minMaxWithLocation(mask)
    (min,max)
  }

  def minMax(): (Double, Double) = {
    val (min,max,_,_) = minMaxWithLocation()
    (min,max)
  }

  def reduceArgMin(axis: Int, lastIndex: Boolean): Mat[Int] = {
    import i4s.opencv.core.model.mats.syntax._
    val dst = Mat[Int](0)
    opencv_core.reduceArgMin(self,dst,axis,lastIndex)
    dst
  }

  def reduceArgMin(axis: Int): Mat[Int] = reduceArgMin(axis,lastIndex = false)

  def reduceArgMax(axis: Int, lastIndex: Boolean): Mat[Int] = {
    import i4s.opencv.core.model.mats.syntax._
    val dst = Mat[Int](0)
    opencv_core.reduceArgMin(self, dst, axis, lastIndex)
    dst
  }

  def reduceArgMax(axis: Int): Mat[Int] = reduceArgMax(axis,lastIndex = false)

  def minMaxIdx(mask: Mat[Int]): (Double,Double,Int,Int) = {
    val minValue = new DoublePointer()
    val maxValue = new DoublePointer()
    val minIndexValue = new IntPointer()
    val maxIndexValue = new IntPointer()
    opencv_core.minMaxIdx(self,minValue,maxValue,minIndexValue,maxIndexValue,mask)

    (minValue.get(),maxValue.get(),minIndexValue.get(),maxIndexValue.get())
  }

  def minMaxIdx(): (Double, Double, Int, Int) = {
    import i4s.opencv.core.model.mats.syntax._
    val mask = Mat[Int](0)
    minMaxIdx(mask)
  }

  def reduce(dim: Int, rtype: ReduceType)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,mtype) => opencv_core.reduce(self,dst,dim,rtype.id))

  def reduceTo[V <: AnyVal](dim: Int, rtype: ReduceType)(implicit matable: Matable[V], tag: ClassTag[V]): Mat[V] =
    doIt[V]((dst,mtype) => opencv_core.reduce(self,dst,dim,rtype.id,mtype))

  def merge(mats: Mat[T]*)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val matVector = new MatVector(self :: mats.toList)
    doIt[T]((dst,_) => opencv_core.merge(matVector,dst))
  }

  def split()(implicit matable: Matable[T], tag: ClassTag[T]): Seq[Mat[T]] = {
    val results = new MatVector()
    opencv_core.split(self,results)
    results.get().toSeq.map(m => new Mat[T](m))
  }

  def mixChannels(map: IndexedSeq[(Int, Int)])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val shape = self.shape
    val dst = Mat[T](depth = Some(Types(self.depth)), ch = Some(shape.size), shape.head, shape.tail:_*)

    opencv_core.mixChannels(new MatVector(self), new MatVector(dst), new IntPointer(map.flatMap(t => Seq(t._1, t._2)): _*))
    dst
  }

  def extractChannel(index: Int)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.extractChannel(self,dst,index))

  def insertChannel(index: Int)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.insertChannel(self,dst,index))

  def flip(flipCode: FlipCode)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.flip(self,dst,flipCode.id))

  def rotate(rotateCode: RotateCode)(implicit matable: Matable[T], tag: ClassTag[T]): Unit =
    doIt[T]((dst,_) => opencv_core.rotate(self,dst,rotateCode.id))

  def repeat(yRepeat: Int, xRepeat: Int)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.repeat(self,yRepeat,xRepeat))

  def hconcat(other: Mat[T], others: Mat[T]*)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val mats = new MatVector(self +: other  +: others)
    doIt[T]((dst,_) => opencv_core.hconcat(mats,dst))
  }

  def vconcat(other: Mat[T], others: Mat[T]*)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val mats = new MatVector(self +: other  +: others)
    doIt[T]((dst,_) => opencv_core.vconcat(mats,dst))
  }

/*
  def &(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.bitwise_and(self,other,dst))
*/

  def bitwiseAnd(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
  doItInShape[T]((dst,_) => opencv_core.bitwise_and(self,other,dst))

  def bitwiseAnd(other: Mat[T], mask: Mat[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
  doItInShape[T]((dst,_) => opencv_core.bitwise_and(self,other,dst,mask))

/*
  def |(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.bitwise_or(self,other,dst))
*/

  def bitwiseOr(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
  doItInShape[T]((dst,_) => opencv_core.bitwise_or(self,other,dst))

  def bitwiseOr(other: Mat[T], mask: Mat[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
  doItInShape[T]((dst,_) => opencv_core.bitwise_or(self,other,dst,mask))

/*
  def ^(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.bitwise_xor(self,other,dst))
*/

  def bitwiseXor(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
  doItInShape[T]((dst,_) => opencv_core.bitwise_xor(self,other,dst))

  def bitwiseXor(other: Mat[T], mask: Mat[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
  doItInShape[T]((dst,_) => opencv_core.bitwise_xor(self,other,dst,mask))

/*
  def ~ (implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.bitwise_not(self,dst))
*/

  def bitwiseNot(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.bitwise_not(self,dst))

  def bitwiseNot(mask: Mat[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.bitwise_not(self,dst,mask))

  def absdiff(other: Mat[T], mask: Mat[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.absdiff(self,dst,mask))

  def absdiff(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    doItWithMask[T]((dst,mask,_) => opencv_core.absdiff(self,dst,mask))
  }

  def copyTo(other: Mat[T], mask: Mat[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.copyTo(self,dst,mask))

  def copyTo(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    doItWithMask[T]((dst,mask,_) => opencv_core.copyTo(self,dst,mask))
  }

  def inRange(lowerb: Mat[T], upperb: Mat[T]): Mat[Int] = {
    import i4s.opencv.core.model.mats.syntax._
    doItInShape[Int]((dst,_) => opencv_core.inRange(self,lowerb,upperb,dst))
  }

  def inRange(lowerb: Scalar, upperb: Scalar): Mat[Int] = {
    import i4s.opencv.core.model.mats.syntax._
    val lbMat = Mat[Double](4)
    lbMat.put(0,lowerb.asArray)

    val ubMat = Mat[Double](4)
    ubMat.put(0,upperb.asArray)

    doItInShape[Int]((dst,_) => opencv_core.inRange(self,lbMat,ubMat,dst))
  }

  def compare(other: Mat[T], compareOp: CompareOp): Mat[Int] = {
    import i4s.opencv.core.model.mats.syntax._
    doIt[Int]((dst, _) => opencv_core.compare(self, other, dst, compareOp.id))
  }

  def >(other: Mat[T]): Mat[Int] = compare(other,CompareOps.GreaterThan)
  def >=(other: Mat[T]): Mat[Int] = compare(other,CompareOps.GreaterThanOrEuals)
  def <(other: Mat[T]): Mat[Int] = compare(other,CompareOps.LessThan)
  def <=(other: Mat[T]): Mat[Int] = compare(other,CompareOps.LessThanOrEuals)
  def ==(other: Mat[T]): Mat[Int] = compare(other,CompareOps.Equals)
  def !=(other: Mat[T]): Mat[Int] = compare(other,CompareOps.NotEquals)

  def min(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.min(self,other,dst))

  def max(other: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.max(self,other,dst))

  def sqrt()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.sqrt(self,dst))

  def pow(power: Double)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.pow(self,power,dst))

  def exp()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst, _) => opencv_core.exp(self, dst))

  def log()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst, _) => opencv_core.log(self, dst))

  def polarToCart(angleInDegrees: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T],Mat[T]) = {
    val Seq(angle,magnitude) = self.split().take(2)
    MatMath.polarToCart(magnitude,angle,angleInDegrees)
  }

  def polarToCart()(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T],Mat[T]) = {
    val Seq(angle,magnitude) = self.split().take(2)
    MatMath.polarToCart(magnitude,angle)
  }

  def cartToPolar(angleInDegrees: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T],Mat[T]) = {
    val Seq(x,y) = self.split().take(2)
    MatMath.cartToPolar(x,y,angleInDegrees)
  }

  def cartToPolar()(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T],Mat[T]) = {
    val Seq(x,y) = self.split().take(2)
    MatMath.cartToPolar(x,y)
  }

  def phase(angleInDegrees: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val Seq(x,y) = self.split().take(2)
    MatMath.phase(x,y,angleInDegrees)
  }

  def phase()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val Seq(x, y) = self.split().take(2)
    MatMath.phase(x, y)
  }

  def magnitude()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val Seq(x, y) = self.split().take(2)
    MatMath.magnitude(x, y)
  }

  def checkRangeWithPos(quiet: Boolean, minVal: Double, maxVal: Double): (Boolean,Option[Point]) = {
    val pos = Point()
    val isValid = opencv_core.checkRange(self,quiet,pos,minVal,maxVal)

    if (isValid) (true,None)
    else (false,Some(pos))
  }

  def checkRangeWithPos(): (Boolean,Option[Point]) =
    checkRangeWithPos(quiet = true,minVal = -Double.MaxValue,maxVal = Double.MaxValue)

  def checkRange(quiet: Boolean /*=true*/, minVal: Double /*=-DBL_MAX*/ ,maxVal: Double /*=DBL_MAX*/): Boolean =
    checkRangeWithPos(quiet,minVal,maxVal)._1

  def checkRange(): Boolean =
    checkRangeWithPos(quiet = true,minVal = -Double.MaxValue,maxVal = Double.MaxValue)._1

  def patchNode(value: Double): Unit =
    opencv_core.patchNaNs(self,value)

  def patchNode(): Unit =
    opencv_core.patchNaNs(self)

  def generalizedMatrixMultiply(first: Mat[T], alpha: Double, second: Mat[T], beta: Double, flags: Set[GeneralizedMatrixMultiplyFlag])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combinedFlags = flags.foldLeft(0)(_ | _.id)
    doIt[T]((dst,_) => opencv_core.gemm(self,first,alpha,second,beta,dst,combinedFlags))
  }

  def generalizedMatrixMultiply(first: Mat[T], alpha: Double, second: Mat[T], beta: Double)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    generalizedMatrixMultiply(first,alpha,second,beta,Set())

  def generalizedMatrixMultiply(first: Mat[T], alpha: Double, flags: Set[GeneralizedMatrixMultiplyFlag])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combinedFlags = flags.foldLeft(0)(_ | _.id)

    withNewMat { nop =>
      doIt[T]((dst,_) => opencv_core.gemm(self,first,alpha,nop,0,dst,combinedFlags))
    }
  }

/*
  def *(other: Mat[_ <: AnyVal])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    generalizedMatrixMultiply(other,1d)
*/

  def generalizedMatrixMultiply(first: Mat[T], alpha: Double)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    generalizedMatrixMultiply(first,alpha,Set())

  def mulTransposed(aTa: Boolean, delta: Mat[T], scale: Double /*=1*/)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
     doIt[T]((dst,mtype) => opencv_core.mulTransposed(self,dst,aTa,delta,scale,mtype))

  def mulTransposed(aTa: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.mulTransposed(self,dst,aTa))

  def transpose()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.transpose(self,dst))

  def transposeND(order: Seq[Int])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.transposeND(self,order.toArray,dst))

  def transform(m: Mat[Float])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.transform(self,dst,m))

  def perspectiveTransform(m: Mat[Float])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doItInShape[T]((dst,_) => opencv_core.perspectiveTransform(self,dst,m))

  def invert(decompositionMethod: DecompositionMethod)(implicit matable: Matable[T], tag: ClassTag[T]): (Double, Mat[T]) = {
    withNewMat { dst =>
      val result = opencv_core.invert(self,dst,decompositionMethod.id)
      (result,new Mat[T](dst))
    }
  }

  def invert()(implicit matable: Matable[T], tag: ClassTag[T]): (Double, Mat[T]) = invert(DecompositionMethods.Lu)

  def solve(src2: Mat[T], decompositionMethod: DecompositionMethod)(implicit matable: Matable[T], tag: ClassTag[T]): (Boolean, Mat[T]) = {
    withNewMat { dst =>
      val result = opencv_core.solve(self, src2, dst, decompositionMethod.id)
      (result, new Mat[T](dst))
    }
  }

  def solve(src2: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): (Boolean, Mat[T]) = solve(src2,DecompositionMethods.Lu)

  def sort(flags: Seq[SortFlag])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combined = flags.foldLeft(0)(_ | _.id)
    doItInShape[T]((dst,_) => opencv_core.sort(self,dst,combined))
  }

  def sortIdx(flags: Seq[SortFlag]): Mat[Int] = {
    import i4s.opencv.core.model.mats.syntax._

    val combined = flags.foldLeft(0)(_ | _.id)
    doItInShape[Int]((dst, _) => opencv_core.sortIdx(self, dst, combined))
  }

  def eigenValuesAndVectors()(implicit matable: Matable[T], tag: ClassTag[T]): (Boolean, Mat[T], Mat[T]) = {
    withNewMat { eValues =>
      withNewMat { eVectors =>
        val result = opencv_core.eigen(self, eValues, eVectors)
        (result, new Mat[T](eValues), new Mat[T](eVectors))
      }
    }
  }

  def eigenValues()(implicit matable: Matable[T], tag: ClassTag[T]): (Boolean, Mat[T]) = {
    withNewMat { eValues =>
      val result = opencv_core.eigen(self, eValues)
      (result, new Mat[T](eValues))
    }
  }

  def eigenNonSymmetric()(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T]) = {
    withNewMat { eValues =>
      withNewMat { eVectors =>
        opencv_core.eigen(self, eValues, eVectors)
        (new Mat[T](eValues), new Mat[T](eVectors))
      }
    }
  }

  def completeSymm(lowerToUpper: Boolean)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val dst = Mat[T](self)
    opencv_core.completeSymm(dst,lowerToUpper)
    dst
  }

  def completeSymm()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val dst = Mat[T](self)
    opencv_core.completeSymm(dst)
    dst
  }

  def setIdentity(s: Scalar): Unit = opencv_core.setIdentity(self,s)

  def setIdentity(): Unit = opencv_core.setIdentity(self)

  def determinant(): Double = opencv_core.determinant(self)

  def trace(): Scalar = opencv_core.trace(self)

  def solveCubic(): (Int, Mat[Double]) = {
    import i4s.opencv.core.model.mats.syntax._

    val roots = Mat[Double](0)
    val result = opencv_core.solveCubic(self,roots)
    (result,roots)
  }

  def solvePoly(maxIters: Int): (Double,Mat[Double]) = {
    import i4s.opencv.core.model.mats.syntax._

    val roots = Mat[Double](0)
    val result = opencv_core.solvePoly(self, roots, maxIters)
    (result, roots)
  }

  def solvePoly(): (Double,Mat[Double]) = solvePoly(300)

  def calcCovariantMatrix(flags: Seq[CovariantFlag]): (Mat[Double],Mat[Double]) = {
    import i4s.opencv.core.model.mats.syntax._

    val combined = flags.foldLeft(0)(_ | _.id)
    val covariants = Mat[Double](0)
    val means = Mat[Double](0)
    opencv_core.calcCovarMatrix(self,covariants,means,combined)
    (covariants,means)
  }

  def dft(flags: Seq[DiscreteFourierTransformFlag], nonzeroRows: Int)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combined = flags.foldLeft(0)(_ | _.id)
    doIt[T]((dst,_) => opencv_core.dft(self,dst,combined,nonzeroRows))
  }

  def dft()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.dft(self,dst))

  def idft(flags:Seq[DiscreteFourierTransformFlag], nonzeroRows: Int)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combined = flags.foldLeft(0)(_ | _.id)
    doIt[T]((dst,_) => opencv_core.idft(self,dst,combined,nonzeroRows))
  }

  def idft()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst, _) => opencv_core.idft(self, dst))

  def dct(flags: Seq[DiscreteFourierTransformFlag])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combined = flags.foldLeft(0)(_ | _.id)
    doIt[T]((dst, _) => opencv_core.dct(self,dst,combined))
  }

  def dct()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst, _) => opencv_core.dct(self, dst))

  def idct(flags: Seq[DiscreteFourierTransformFlag])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] = {
    val combined = flags.foldLeft(0)(_ | _.id)
    doIt[T]((dst, _) => opencv_core.idct(self, dst, combined))
  }

  def idct()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst, _) => opencv_core.idct(self, dst))

  def randomUniformDistribution(low: Mat[T], high: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.randu(dst,low,high))

  def randomNormalDistribution(mean: Mat[T], stddev: Mat[T])(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.randn(dst,mean,stddev))

  def randomShuffle()(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.randShuffle(dst))

  def randomShuffle(iterFactor: Double /*=1.*/ , rng: RNG)(implicit matable: Matable[T], tag: ClassTag[T]): Mat[T] =
    doIt[T]((dst,_) => opencv_core.randShuffle(dst,iterFactor,rng))

  def svDecomp(flags: Seq[SingularValueDecompositionFlag])(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T], Mat[T]) = {
    val combined = flags.foldLeft(0)(_ | _.id)

    withNewMat { w =>
      withNewMat { u =>
        withNewMat { vt =>
          opencv_core.SVDecomp(self,w,u,vt,combined)
          (new Mat[T](w),new Mat[T](u),new Mat[T](vt))
        }
      }
    }
  }

  def svDecomp()(implicit matable: Matable[T], tag: ClassTag[T]): (Mat[T], Mat[T], Mat[T]) = svDecomp(Seq.empty[SingularValueDecompositionFlag])

  /*
    def PCACompute(data: Mat, mean: Mat, eigenvectors: Mat, maxComponents: Int): Unit

    def PCACompute(data: Mat, mean: Mat, eigenvectors: Mat): Unit

    def PCACompute2(data: Mat, mean: Mat, eigenvectors: Mat, eigenvalues: Mat, maxComponents: Int): Unit

    def PCACompute2(data: Mat, mean: Mat, eigenvectors: Mat, eigenvalues: Mat): Unit

    def PCACompute(data: Mat, mean: Mat, eigenvectors: Mat, retainedVariance: Double): Unit

    def PCACompute2(data: Mat, mean: Mat, eigenvectors: Mat, eigenvalues: Mat, retainedVariance: Double): Unit

    def PCAProject(data: Mat, mean: Mat, eigenvectors: Mat, result: Mat): Unit

    def PCABackProject(data: Mat, mean: Mat, eigenvectors: Mat, result: Mat): Unit
  */

  private def doItWithMask[X <: AnyVal](fn: (org.bytedeco.opencv.opencv_core.Mat, org.bytedeco.opencv.opencv_core.Mat, Int) => Unit)(implicit matable: Matable[X], tag: ClassTag[X]): Mat[X] = {
    val shape = self.shape()
    val mat = new org.bytedeco.opencv.opencv_core.Mat(shape.toArray,MatTypes.makeType(Types(self.depth), self.channels))
    val maskMat = new org.bytedeco.opencv.opencv_core.Mat(shape.toArray,MatTypes.makeType(Types.Cv8U,1))
    maskMat.put(Scalar(1,1,1,1))

    fn(mat,maskMat,MatTypes.makeType(Types(self.depth),self.channels))
    new Mat[X](mat)
  }

  private def doItInShape[X <: AnyVal](fn: (org.bytedeco.opencv.opencv_core.Mat, Int) => Unit)(implicit matable: Matable[X], tag: ClassTag[X]): Mat[X] = {
    val shape = self.shape()
    val mtype = MatTypes.makeType(Types(self.depth),self.channels)
    val mat = new org.bytedeco.opencv.opencv_core.Mat(shape.toArray,mtype)
    fn(mat,MatTypes.makeType(matable.depth,matable.channels))
    new Mat[X](mat)

  }

  private def doIt[X <: AnyVal](fn: (org.bytedeco.opencv.opencv_core.Mat, Int) => Unit)(implicit matable: Matable[X], tag: ClassTag[X]): Mat[X] = {
    val mat = new org.bytedeco.opencv.opencv_core.Mat()
    fn(mat,MatTypes.makeType(Types(matable.depth.id),matable.channels))
    new Mat[X](mat)
  }

}
