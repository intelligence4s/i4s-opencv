package i4s.opencv

import org.bytedeco.javacpp.{IntPointer, Pointer, PointerPointer}

import java.nio.IntBuffer

object TypeConverters {

  implicit class IntConverters(self: Int) {
    def asPointer: IntPointer = new IntPointer(1L).put(self)
    def asBuffer: IntBuffer = IntBuffer.wrap(Array(self))
  }

  implicit class PointerArrayConverters[P <: Pointer](self: Array[P]) {
    def asPointer: PointerPointer[P] = {
      new PointerPointer[P](self: _*)
    }
  }
}
