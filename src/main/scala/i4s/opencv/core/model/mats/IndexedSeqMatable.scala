package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Math.NumberLike
import i4s.opencv.core.model.Scalar
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type

import scala.collection.immutable.Seq
import scala.reflect.ClassTag

class IndexedSeqMatable[T <: AnyVal] extends MappedMatable[IndexedSeq[T],T] {
  override def channels: Int = 4  // Default channel count
  override def depth: Type = Types.Cv64F

  override def getMapped(mat: BaseMat[T], indices: Int*)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): IndexedSeq[T] = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch)
    Array(values: _*)
  }

  override def getMappedN(mat: BaseMat[T], indices: Seq[Int], n: Int)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): IndexedSeq[IndexedSeq[T]] = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch*n)
    values.sliding(ch,ch).map(s => IndexedSeq(s:_*)).toIndexedSeq
  }

  override def putMapped(mat: BaseMat[T], indices: Seq[Int], value: IndexedSeq[T])(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val parts = value.toArray.take(ch)
    matable.putN(mat, indices, parts)
  }

  override def putMappedN(mat: BaseMat[T], indices: Seq[Int], values: Seq[IndexedSeq[T]])(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val expanded = values.flatMap(_.toArray.take(ch))
    matable.putN(mat, indices, expanded)
  }

}