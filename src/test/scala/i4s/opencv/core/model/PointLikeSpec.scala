package i4s.opencv.core.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PointLikeSpec extends AnyWordSpec with Matchers {
  "PointLike" should {
    "support the creation of Point objects" in {
      val point = Point(5,10)
      point.x shouldBe 5
      point.y shouldBe 10

      point.dot(Point(1,1)) shouldBe 15
    }

    "support the creation of Point2d objects" in {
      val point = Point2d(5,10)
      point.x shouldBe 5
      point.y shouldBe 10

      point.ddot(Point2d(1,1)) shouldBe 15.0d
    }

    "support the creation of Point2f objects" in {
      val point = Point2f(5,10)
      point.x shouldBe 5
      point.y shouldBe 10

      point.dot(Point2f(1,1)) shouldBe 15.0f
      point.ddot(Point2f(1,1)) shouldBe 15.0d
    }

    "support equality correctly" in {
      val pt1 = Point(1,1)
      val ptd1 = Point2d(1,1)
      val ptf1 = Point2f(1,1)

      pt1 == Point(1,1) shouldBe true
      ptd1 == Point(1,1) shouldBe true
      ptf1 == Point(1,1) shouldBe true

      pt1 == Point2d(1,1) shouldBe true
      ptd1 == Point2d(1,1) shouldBe true
      ptf1 == Point2d(1,1) shouldBe true

      pt1 == Point2f(1,1) shouldBe true
      ptd1 == Point2f(1,1) shouldBe true
      ptf1 == Point2f(1,1) shouldBe true

      pt1 == Point(2,1) shouldBe false
    }

    "support basic math operations" in {
      val pt1 = new Point(1, 1)
      val pt2 = Point(2, 2)

      (pt1 + pt2) shouldBe Point(3, 3)
      (pt1 - pt2) shouldBe Point(-1, -1)
      (pt2 * 4) shouldBe Point(8, 8)
      (pt2 / 2) shouldBe Point(1, 1)

      (pt1 * pt2) shouldBe Point(2, 2)
      (pt2 / pt2) shouldBe Point(1, 1)
    }

    "support javacv point operations" in {
      val pt1 = Point(5, 10)
      val pt2 = Point(2, 3)

      pt1.dot(pt2) shouldBe 40
      pt1.ddot(pt2) shouldBe 40.0

      pt1.cross(pt2) shouldBe -5.0
    }
  }

}
