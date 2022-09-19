package i4s.opencv.core.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SizeLikeSpec extends AnyWordSpec with Matchers {
  "SizeLIke" should {
    "support the creation of Size objects" in {
      val size = Size(5,10)
      size.width shouldBe 5
      size.height shouldBe 10

      size.area shouldBe 50
      size.aspectRatio shouldBe(0.5d)
      size.empty shouldBe false
    }

    "support the creation of Size2d objects" in {
      val size = Size2d(5, 10)
      size.width shouldBe 5
      size.height shouldBe 10

      size.area shouldBe 50
      size.aspectRatio shouldBe (0.5d)
      size.empty shouldBe false
    }

    "support the creation of Size2f objects" in {
      val size = Size2f(5, 10)
      size.width shouldBe 5
      size.height shouldBe 10

      size.area shouldBe 50
      size.aspectRatio shouldBe (0.5d)
      size.empty shouldBe false
    }

    "support equality correctly" in {
      val sz1 = Size(1, 1)
      val szd1 = Size2d(1, 1)
      val szf1 = Size2f(1, 1)

      sz1 == Size(1, 1) shouldBe true
      szd1 == Size(1, 1) shouldBe true
      szf1 == Size(1, 1) shouldBe true

      sz1 == Size2d(1, 1) shouldBe true
      szd1 == Size2d(1, 1) shouldBe true
      szf1 == Size2d(1, 1) shouldBe true

      sz1 == Size2f(1, 1) shouldBe true
      szd1 == Size2f(1, 1) shouldBe true
      szf1 == Size2f(1, 1) shouldBe true

      sz1 == Size(2, 1) shouldBe false
    }

    "support basic math operations for Size" in {
      val sz1 = new Size(1, 1)
      val sz2 = Size(2, 2)

      (sz1 + sz2) shouldBe Size(3, 3)
      (sz1 - sz2) shouldBe Size(-1, -1)
      (sz2 * 4) shouldBe Size(8, 8)
      (sz2 / 2) shouldBe Size(1, 1)

      (sz1 * sz2) shouldBe Size(2, 2)
      (sz2 / sz2) shouldBe Size(1, 1)
    }

    "support basic math operations for Size2d" in {
      val sz1 = new Size2d(1, 1)
      val sz2 = Size2d(2, 2)

      (sz1 + sz2) shouldBe Size2d(3, 3)
      (sz1 - sz2) shouldBe Size2d(-1, -1)
      (sz2 * 4) shouldBe Size2d(8, 8)
      (sz2 / 2) shouldBe Size2d(1, 1)

      (sz1 * sz2) shouldBe Size2d(2, 2)
      (sz2 / sz2) shouldBe Size2d(1, 1)
    }

    "support basic math operations for Size2f" in {
      val sz1 = new Size2f(1, 1)
      val sz2 = Size2f(2, 2)

      (sz1 + sz2) shouldBe Size2f(3, 3)
      (sz1 - sz2) shouldBe Size2f(-1, -1)
      (sz2 * 4) shouldBe Size2f(8, 8)
      (sz2 / 2) shouldBe Size2f(1, 1)

      (sz1 * sz2) shouldBe Size2f(2, 2)
      (sz2 / sz2) shouldBe Size2f(1, 1)
    }

    "support javacv point operations" in {
      val sz1 = Size(5, 10)

      sz1.area shouldBe 50
      sz1.aspectRatio shouldBe 0.5d
    }
  }
}
