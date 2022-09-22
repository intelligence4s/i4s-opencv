package i4s.opencv.image.constants

object LineSegmentDetectorModes extends Enumeration {
  type LineSegmentDetectorMode = Value

  val NoRefinement: LineSegmentDetectorMode = Value(0)
  val StandardRefinement: LineSegmentDetectorMode = Value(1)
  val AdvancedRefinement: LineSegmentDetectorMode = Value(2)
}
