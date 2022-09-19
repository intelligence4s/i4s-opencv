package i4s.opencv.image.constants

object RetrievalModes extends Enumeration {
  type RetrievalMode = Value

  /** retrieves only the extreme outer contours. It sets {@code hierarchy[i][2]=hierarchy[i][3]=-1} for
   * all the contours. */
  val External = Value(0)

  /** retrieves all of the contours without establishing any hierarchical relationships. */
  val List = Value(1)

  /** retrieves all of the contours and organizes them into a two-level hierarchy. At the top
   * level, there are external boundaries of the components. At the second level, there are
   * boundaries of the holes. If there is another contour inside a hole of a connected component, it
   * is still put at the top level. */
  val ConnectedComponent = Value(2)

  /** retrieves all of the contours and reconstructs a full hierarchy of nested contours. */
  val Tree = Value(3)

  //!<
  val FloodFill = Value(4)

}
