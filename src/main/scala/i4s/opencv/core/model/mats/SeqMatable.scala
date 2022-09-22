package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Math.NumberLike
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type

import scala.reflect.ClassTag

class SeqMatable[T <: AnyVal] extends MappedMatable[Seq[T],T] {
  override def channels: Int = 4  // Default channel count
  override def depth: Type = Types.Cv64F

  override def getMapped(mat: BaseMat[T], indices: Int*)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Seq[T] = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch)
    Array(values: _*)
  }

  override def getMappedN(mat: BaseMat[T], indices: Seq[Int], n: Int)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): IndexedSeq[Seq[T]] = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch*n)
    values.sliding(ch,ch).map(s => Seq(s:_*)).toIndexedSeq
  }

  override def putMapped(mat: BaseMat[T], indices: Seq[Int], value: Seq[T])(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val parts = value.toArray.take(ch)
    matable.putN(mat, indices, parts)
  }

  override def putMappedN(mat: BaseMat[T], indices: Seq[Int], values: Seq[Seq[T]])(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val expanded = values.flatMap(_.toArray.take(ch))
    matable.putN(mat, indices, expanded)
  }

}