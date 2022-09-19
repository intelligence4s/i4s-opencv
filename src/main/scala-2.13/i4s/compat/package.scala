package i4s

package object compat {
  type LazyList[T] = scala.LazyList[T]
  val LazyList = scala.LazyList

  val Using = scala.util.Using
}
