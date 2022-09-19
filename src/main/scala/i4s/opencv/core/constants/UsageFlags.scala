package i4s.opencv.core.constants

object UsageFlags extends Enumeration {
  type UsageFlag = Value

  val UsageFlags32Bit = 0x7fffffff; // Binary compatibility hint

  val Default: Value = Value(0)

  // buffer allocation policy is platform and usage specific
  val AllocateHostMemory: Value = Value(1)

  val AllocateDeviceMemory: Value = Value(2)

  val AllocateSharedMemory: Value = Value(4)  // It is not equal to: USAGE_ALLOCATE_HOST_MEMORY | USAGE_ALLOCATE_DEVICE_MEMORY

}
