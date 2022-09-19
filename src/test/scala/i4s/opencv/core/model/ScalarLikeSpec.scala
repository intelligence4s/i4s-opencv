package i4s.opencv.core.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ScalarLikeSpec extends AnyWordSpec with Matchers {
  "ScalarLike" should {
    "support the creation of Scalar objects" in {
      val scalar = Scalar(0,10,20,30)
      scalar.v0 shouldBe 0
      scalar.v1 shouldBe 10
      scalar.v2 shouldBe 20
      scalar.v3 shouldBe 30

      scalar.magnitude() shouldBe 37.416573867739416d
    }

    "support the creation of Scalar4i objects" in {
      val scalar = Scalar4i(0, 10, 20, 30)
      scalar.v0 shouldBe 0
      scalar.v1 shouldBe 10
      scalar.v2 shouldBe 20
      scalar.v3 shouldBe 30

    }

    "support the creation of Scalar4f objects" in {
      val scalar = Scalar4f(0, 10, 20, 30)
      scalar.v0 shouldBe 0
      scalar.v1 shouldBe 10
      scalar.v2 shouldBe 20
      scalar.v3 shouldBe 30
    }

    "support equality correctly" in {
      val pt1 = Scalar(1,1,1,1)
      val ptd1 = Scalar4i(1,1,1,1)
      val ptf1 = Scalar4f(1,1,1,1)

      pt1 == Scalar(1,1,1,1) shouldBe true
      ptd1 == Scalar(1,1,1,1) shouldBe true
      ptf1 == Scalar(1,1,1,1) shouldBe true

      pt1 == Scalar4i(1,1,1,1) shouldBe true
      ptd1 == Scalar4i(1,1,1,1) shouldBe true
      ptf1 == Scalar4i(1,1,1,1) shouldBe true

      pt1 == Scalar4f(1,1,1,1) shouldBe true
      ptd1 == Scalar4f(1,1,1,1) shouldBe true
      ptf1 == Scalar4f(1,1,1,1) shouldBe true

      pt1 == Scalar(2,1,2,2) shouldBe false
    }

    "support basic math operations" in {
      val pt1 = Scalar(1,2,3,4)
      val pt2 = Scalar(10,20,30,40)

      (pt1 + pt2) shouldBe Scalar(11,22,33,44)
      (pt1 - pt2) shouldBe Scalar(-9,-18,-27,-36)
      (pt2 * 4) shouldBe Scalar(40,80,120,160)
      (pt2 / 2) shouldBe Scalar(5,10,15,20)

      (pt1 * pt2) shouldBe Scalar(10,40,90,160)
      (pt1 / pt2) shouldBe Scalar(0.1,0.1,0.1,0.1)
    }

  }

}
