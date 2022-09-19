package i4s.opencv.core.model.mats

import org.bytedeco.javacpp.indexer._

import scala.reflect.ClassTag

object Indexable {
  def apply[T <: AnyVal](indexer: Indexer)(implicit tag: ClassTag[T]): Indexable[T] = {
    val indexable = indexer match {
      case indexer: ByteIndexer =>
        assert(tag.runtimeClass == classOf[Byte], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of Bytes")
        byteIndexer(indexer)

      case indexer: UByteIndexer =>
        assert(tag.runtimeClass == classOf[Int], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of UBytes")
        ubyteIndexer(indexer)

      case indexer: CharIndexer =>
        assert(tag.runtimeClass == classOf[Char], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of Char")
        charIndexer(indexer)

      case indexer: ShortIndexer =>
        assert(tag.runtimeClass == classOf[Short], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of Short")
        shortIndexer(indexer)

      case indexer: UShortIndexer =>
        assert(tag.runtimeClass == classOf[Int], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of UShort")
        ushortIndexer(indexer)

      case indexer: IntIndexer =>
        assert(tag.runtimeClass == classOf[Int], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of Int")
        intIndexer(indexer)

      case indexer: FloatIndexer =>
        assert(tag.runtimeClass == classOf[Float], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of Float")
        floatIndexer(indexer)

      case indexer: LongIndexer =>
        assert(tag.runtimeClass == classOf[Long], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of Long")
        longIndexer(indexer)

      case indexer: DoubleIndexer =>
        assert(tag.runtimeClass == classOf[Double], s"Declared type ${tag.runtimeClass.getSimpleName} is not compatible with Mat of Double")
        doubleIndexer(indexer)
    }

    indexable.asInstanceOf[Indexable[T]]
  }

  def byteIndexer(idxr: ByteIndexer): Indexable[Byte] = new Indexable[Byte]() {
    override def get(indices: Seq[Int]): Byte = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Byte]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Byte): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Byte]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }

  def ubyteIndexer(idxr: UByteIndexer): Indexable[Int] = new Indexable[Int]() {
    override def get(indices: Seq[Int]): Int = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Int]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Int): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Int]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }

  def charIndexer(idxr: CharIndexer): Indexable[Char] = new Indexable[Char]() {
    override def get(indices: Seq[Int]): Char = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Char]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Char): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Char]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }

  def shortIndexer(idxr: ShortIndexer): Indexable[Short] = new Indexable[Short]() {
    override def get(indices: Seq[Int]): Short = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Short]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Short): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Short]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }

  def ushortIndexer(idxr: UShortIndexer): Indexable[Int] = new Indexable[Int]() {
    override def get(indices: Seq[Int]): Int = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Int]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Int): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Int]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }

  def intIndexer(idxr: IntIndexer): Indexable[Int] = new Indexable[Int]() {
    override def get(indices: Seq[Int]): Int = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Int]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Int): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Int]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }

  def floatIndexer(idxr: FloatIndexer): Indexable[Float] = new Indexable[Float]() {
    override def get(indices: Seq[Int]): Float = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Float]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Float): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Float]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }

  def longIndexer(idxr: LongIndexer): Indexable[Long] = new Indexable[Long]() {
    override def get(indices: Seq[Int]): Long = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Long]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Long): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Long]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }

  def doubleIndexer(idxr: DoubleIndexer): Indexable[Double] = new Indexable[Double]() {
    override def get(indices: Seq[Int]): Double = idxr.get(indices.map(_.toLong): _*)
    override def get(indices: Seq[Int], buffer: Array[Double]): Unit = idxr.get(indices.map(_.toLong).toArray, buffer, 0, buffer.length)
    override def put(indices: Seq[Int], value: Double): Unit = idxr.put(indices.map(_.toLong).toArray, value)
    override def put(indices: Seq[Int], values: Seq[Double]): Unit = idxr.put(indices.map(_.toLong).toArray, values.toArray, 0, values.length)
  }
}

trait Indexable[T <: AnyVal] {
  def get(indices: Seq[Int]): T
  def get(indices: Seq[Int], buffer: Array[T]): Unit

  def put(indices: Seq[Int], value: T): Unit
  def put(indices: Seq[Int], values: Seq[T]): Unit
}


