package i4s.opencv.core.model.mats

import i4s.opencv.core.types.MatTypes.MatType
import i4s.opencv.core.types.Types.Type
import i4s.opencv.core.types.{MatTypes, Types}
import org.bytedeco.javacpp.IntPointer
import org.bytedeco.opencv.opencv_core

import java.nio.IntBuffer
import scala.annotation.tailrec
import scala.reflect.ClassTag

class BaseMat[T <: AnyVal : ClassTag](wrapped: org.bytedeco.opencv.opencv_core.Mat)(implicit matable: Matable[T])
  extends org.bytedeco.opencv.opencv_core.Mat(wrapped.getPointer(0))
{
  implicit val indexable: Indexable[T] = matable.indexer(this)

  def matType: MatType = MatTypes(MatTypes.makeType(Types(depth()),channels()))

  def shape(): Seq[Int] = (0 until dims()).map(this.size)

  protected def totalShape: List[Int] = channels() match {
    case 1 => shape().toList
    case _ => shape().toList :+ channels()
  }

  @tailrec
  protected final def toIndices(offset: Int, is: List[Int], shape: List[Int]): List[Int] = {
    if (shape.isEmpty) (offset :: is).reverse.tail
    else {
      val prod = shape.product
      toIndices(offset % prod, (offset / prod) :: is, shape.tail)
    }
  }

  protected def valueStream: i4s.compat.LazyList[T] = {
    val topOffset = total() * channels()

    val s = totalShape

    def elemAt(offset: Int): i4s.compat.LazyList[T] =
      if (offset < topOffset) matable.get(this, toIndices(offset, Nil, s): _*) #:: elemAt(offset + 1)
      else i4s.compat.LazyList.empty[T]

    elemAt(0)
  }


  // Disallow calls to underlaying Mat object that will cause side-effects...
  override def create(size: opencv_core.Size, `type`: Int): Unit =
    throw new UnsupportedOperationException(s"Side effects changing the size, shape or allocation of the underlaying Mat object are not allowed from the Scala wrapper")

  override def create(ndims: Int, sizes: Array[Int], `type`: Int): Unit =
    throw new UnsupportedOperationException(s"Side effects changing the size, shape or allocation of the underlaying Mat object are not allowed from the Scala wrapper")

  override def create(ndims: Int, sizes: IntPointer, `type`: Int): Unit =
    throw new UnsupportedOperationException(s"Side effects changing the size, shape or allocation of the underlaying Mat object are not allowed from the Scala wrapper")

  override def create(rows: Int, cols: Int, `type`: Int): Unit =
    throw new UnsupportedOperationException(s"Side effects changing the size, shape or allocation of the underlaying Mat object are not allowed from the Scala wrapper")

  override def create(sizes: Array[Int], `type`: Int): Unit =
    throw new UnsupportedOperationException(s"Side effects changing the size, shape or allocation of the underlaying Mat object are not allowed from the Scala wrapper")

  override def create(sizes: IntBuffer, `type`: Int): Unit =
    throw new UnsupportedOperationException(s"Side effects changing the size, shape or allocation of the underlaying Mat object are not allowed from the Scala wrapper")

  override def create(sizes: IntPointer, `type`: Int): Unit =
    throw new UnsupportedOperationException(s"Side effects changing the size, shape or allocation of the underlaying Mat object are not allowed from the Scala wrapper")

  override def create(ndims: Int, sizes: IntBuffer, `type`: Int): Unit =
    throw new UnsupportedOperationException(s"Side effects changing the size, shape or allocation of the underlaying Mat object are not allowed from the Scala wrapper")

}
