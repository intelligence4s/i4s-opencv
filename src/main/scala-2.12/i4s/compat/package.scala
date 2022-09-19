package i4s

package object compat {
  type LazyList[T] = Stream[T]
  val LazyList = Stream
}
