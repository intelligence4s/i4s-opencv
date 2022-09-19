package i4s.opencv.image.constants

object ShapeMatchModes extends Enumeration {
  type ShapeMatchMode = Value

  /** <pre>\[I_1(A,B) =  \sum _{i=1...7}  \left |  \frac{1}{m^A_i} -  \frac{1}{m^B_i} \right |\]</pre> */
  val I1: Value = Value(1)

  /** <pre>\[I_2(A,B) =  \sum _{i=1...7}  \left | m^A_i - m^B_i  \right |\]</pre> */
  val I2: Value = Value(2)

  /** <pre>\[I_3(A,B) =  \max _{i=1...7}  \frac{ \left| m^A_i - m^B_i \right| }{ \left| m^A_i \right| }\]</pre> */
  val I3: Value = Value(3)

}
