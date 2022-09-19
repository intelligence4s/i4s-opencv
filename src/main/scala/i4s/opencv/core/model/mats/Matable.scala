package i4s.opencv.core.model.mats

import scala.reflect.ClassTag

trait Matable[T <: AnyVal] extends BaseMatable[T] {
  def get(mat: BaseMat[T], indices: Int*)(implicit indexer: Indexable[T]): T = indexer.get(indices)
  def getN(mat: BaseMat[T], indices: Seq[Int], n: Int)(implicit indexer: Indexable[T], tag: ClassTag[T]): IndexedSeq[T] = {
    val buffer = new Array[T](n)
    indexer.get(indices, buffer)
    buffer.toIndexedSeq
  }

  def put(mat: BaseMat[T], indices: Seq[Int], value: T)(implicit indexer: Indexable[T]): Unit = indexer.put(indices, value)
  def putN(mat: BaseMat[T], indices: Seq[Int], values: Seq[T])(implicit indexer: Indexable[T]): Unit = indexer.put(indices, values)
}
