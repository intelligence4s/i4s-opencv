package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Math.NumberLike
import i4s.opencv.core.model.Point
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type

import scala.reflect.ClassTag

class PointMatable[T <: AnyVal] extends MappedMatable[Point,T] {
  override def channels: Int = 4  // Default channel count
  override def depth: Type = Types.Cv32S

  override def getMapped(mat: BaseMat[T], indices: Int*)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Point = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch).map(nl.toInt)
    Point(values: _*)
  }

  override def getMappedN(mat: BaseMat[T], indices: Seq[Int], n: Int)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): IndexedSeq[Point] = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch*n).map(nl.toInt)
    values.sliding(ch,ch).map(s => Point(s:_*)).toIndexedSeq
  }

  override def putMapped(mat: BaseMat[T], indices: Seq[Int], value: Point)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val parts = value.asArray.take(ch).map(nl.fromInt)
    matable.putN(mat,indices,parts)
  }

  override def putMappedN(mat: BaseMat[T], indices: Seq[Int], values: Seq[Point])(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val expanded = values.flatMap(_.asArray.take(ch).map(nl.fromInt))
    matable.putN(mat,indices,expanded)
  }
}