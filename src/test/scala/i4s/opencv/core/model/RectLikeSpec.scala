package i4s.opencv.core.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RectLikeSpec extends AnyWordSpec with Matchers {
  "RectLIkeSpec" should {
    "support the creation of Scalar objects" in {
      val rect = Rect(x = 0, y = 10, width = 20, height = 30)
      rect.x shouldBe 0
      rect.y shouldBe 10
      rect.width shouldBe 20
      rect.height shouldBe 30

      rect.tl shouldBe Point(0,10)
      rect.br shouldBe Point(20,40)
    }

    "support javacv Rect methods" in {
      val rect = Rect(x = 0, y = 10, width = 20, height = 30)

      rect.area shouldBe 600
      rect.empty shouldBe false
      rect.contains(Point(0,0)) shouldBe false
      rect.contains(Point(15,15)) shouldBe true
    }

    "supports creating new rects" in {
      val rect = Rect(x = 0, y = 10, width = 20, height = 30)
      val copy = Rect(rect)

      rect shouldBe copy
    }

    "supports operations to manipulate rects" in {
      val rect = Rect(x = 0, y = 10, width = 20, height = 30)
      val moved = rect.moveTo(Point(25,25))

      rect.area shouldBe 600
      rect.contains(Point(25,45)) shouldBe false
      moved.contains(Point(25,45)) shouldBe true

      val scaled = rect.scaleBy(Size(2,2))
      scaled.area shouldBe(2400)

      val shifted = rect.shiftBy(Size(20,10))
      shifted.tl shouldBe Point(20,20)
    }

    "support Rect of Double values" in {
      val rect2d = Rect4d(x = 0, y = 10, width = 20, height = 30)
      val moved2d = rect2d.moveTo(Point2d(25, 25))

      rect2d.area shouldBe 600
      rect2d.contains(Point2d(25, 45)) shouldBe false
      moved2d.contains(Point2d(25, 45)) shouldBe true

      val scaled2d = rect2d.scaleBy(Size2d(0.5d, 0.5d))
      scaled2d.area shouldBe (150)

      val shifted2d = rect2d.shiftBy(Size2d(20, 10))
      shifted2d.tl shouldBe Point(20, 20)
    }

    "support Rect of Float values" in {
      val rect2f = Rect4f(x = 0, y = 10, width = 20, height = 30)
      val moved2f = rect2f.moveTo(Point2f(25, 25))

      rect2f.area shouldBe 600
      rect2f.contains(Point2f(25, 45)) shouldBe false
      moved2f.contains(Point2f(25, 45)) shouldBe true

      val scaled2f = rect2f.scaleBy(Size2f(0.5f, 0.5f))
      scaled2f.area shouldBe (150)

      val shifted2f = rect2f.shiftBy(Size2f(20, 10))
      shifted2f.tl shouldBe Point(20, 20)
    }
  }
}
