package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Math.NumberLike
import i4s.opencv.core.model.Point2f
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type

import scala.reflect.ClassTag

class Point2fMatable[T <: AnyVal] extends MappedMatable[Point2f,T] {
  override def channels: Int = 4  // Default channel count
  override def depth: Type = Types.Cv32S

  override def getMapped(mat: BaseMat[T], indices: Int*)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Point2f = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch).map(nl.toFloat)
    Point2f(values: _*)
  }

  override def getMappedN(mat: BaseMat[T], indices: Seq[Int], n: Int)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): IndexedSeq[Point2f] = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch*n).map(nl.toFloat)
    values.sliding(ch,ch).map(s => Point2f(s:_*)).toIndexedSeq
  }

  override def putMapped(mat: BaseMat[T], indices: Seq[Int], value: Point2f)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val parts = value.asArray.take(ch).map(nl.fromFloat)
    matable.putN(mat,indices,parts)
  }

  override def putMappedN(mat: BaseMat[T], indices: Seq[Int], values: Seq[Point2f])(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val expanded = values.flatMap(_.asArray.take(ch).map(nl.fromFloat))
    matable.putN(mat,indices,expanded)
  }
}