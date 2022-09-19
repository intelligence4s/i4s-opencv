package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Math.NumberLike
import i4s.opencv.core.model.Rect
import i4s.opencv.core.types.Types
import i4s.opencv.core.types.Types.Type

import scala.reflect.ClassTag

class RectMatable[T <: AnyVal] extends MappedMatable[Rect,T] {
  override def channels: Int = 4  // Default channel count
  override def depth: Type = Types.Cv32S

  override def getMapped(mat: BaseMat[T], indices: Int*)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Rect = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch).map(nl.toInt)
    Rect(values: _*)
  }

  override def getMappedN(mat: BaseMat[T], indices: Seq[Int], n: Int)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): IndexedSeq[Rect] = {
    val ch = mat.channels
    val values = matable.getN(mat, indices, ch*n).map(nl.toInt)
    values.sliding(ch,ch).map(s => Rect(s:_*)).toIndexedSeq
  }

  override def putMapped(mat: BaseMat[T], indices: Seq[Int], value: Rect)(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val parts = value.asArray.take(ch).map(nl.fromInt)
    matable.putN(mat,indices,parts)
  }

  override def putMappedN(mat: BaseMat[T], indices: Seq[Int], values: Seq[Rect])(implicit indexer: Indexable[T], matable: Matable[T], nl: NumberLike[T], tag: ClassTag[T]): Unit = {
    val ch = mat.channels
    val expanded = values.flatMap(_.asArray.take(ch).map(nl.fromInt))
    matable.putN(mat,indices,expanded)
  }
}