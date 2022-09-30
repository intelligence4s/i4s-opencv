package i4s.opencv.core.model.mats

import i4s.opencv.core.model.Scalar
import i4s.opencv.core.types.Types
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatExprSpec extends AnyWordSpec with Matchers {

  "Mat" should {
    import i4s.opencv.core.model.mats.syntax._

    val mat1 = Mat[Float](3, 20)

    val init = 1 to 60 map (_.toFloat)
    mat1.put(init)

    val mat2 = Mat[Float](20, 3)
    mat2.put(init)

    "support adding matrices" in {
      val sum = mat1 + mat2.t
      sum.getN(sum.total.toInt,0) shouldBe Array(2.0f, 6.0f, 10.0f, 14.0f, 18.0f, 22.0f, 26.0f, 30.0f, 34.0f, 38.0f,
        42.0f, 46.0f, 50.0f, 54.0f, 58.0f, 62.0f, 66.0f, 70.0f, 74.0f, 78.0f, 23.0f, 27.0f, 31.0f, 35.0f, 39.0f, 43.0f,
        47.0f, 51.0f, 55.0f, 59.0f, 63.0f, 67.0f, 71.0f, 75.0f, 79.0f, 83.0f, 87.0f, 91.0f, 95.0f, 99.0f, 44.0f, 48.0f,
        52.0f, 56.0f, 60.0f, 64.0f, 68.0f, 72.0f, 76.0f, 80.0f, 84.0f, 88.0f, 92.0f, 96.0f, 100.0f, 104.0f, 108.0f,
        112.0f, 116.0f, 120.0f)
       // dst = alpha * src1.t() * src2 + beta * src3.t();
    }

    "support adding literal values to matrices" in {
      val expectedValue = Array(2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f,
        15.0f, 16.0f, 17.0f, 18.0f, 19.0f, 20.0f, 21.0f, 22.0f, 23.0f, 24.0f, 25.0f, 26.0f, 27.0f, 28.0f, 29.0f, 30.0f,
        31.0f, 32.0f, 33.0f, 34.0f, 35.0f, 36.0f, 37.0f, 38.0f, 39.0f, 40.0f, 41.0f, 42.0f, 43.0f, 44.0f, 45.0f, 46.0f,
        47.0f, 48.0f, 49.0f, 50.0f, 51.0f, 52.0f, 53.0f, 54.0f, 55.0f, 56.0f, 57.0f, 58.0f, 59.0f, 60.0f, 61.0f)

      val plusOne: Mat[Float] = mat1 + Scalar(1)
      plusOne.getN(plusOne.total.toInt,0) shouldBe expectedValue

      val onePlus: Mat[Float] = Scalar(1) + mat1
      onePlus.getN(onePlus.total.toInt,0) shouldBe expectedValue

      val plusOneDouble: Mat[Float] = mat1 + 1d
      plusOneDouble.getN(plusOneDouble.total.toInt,0) shouldBe expectedValue

      val onePlusDouble: Mat[Float] = 1d + mat1
      onePlusDouble.getN(onePlusDouble.total.toInt,0) shouldBe expectedValue
    }

    "support subtracting matrices" in {
      val difference = mat2.t - mat1
      difference.getN(difference.total.toInt,0) shouldBe Array(0.0f, 2.0f, 4.0f, 6.0f, 8.0f, 10.0f, 12.0f, 14.0f, 16.0f,
        18.0f, 20.0f, 22.0f, 24.0f, 26.0f, 28.0f, 30.0f, 32.0f, 34.0f, 36.0f, 38.0f, -19.0f, -17.0f, -15.0f, -13.0f,
        -11.0f, -9.0f, -7.0f, -5.0f, -3.0f, -1.0f, 1.0f, 3.0f, 5.0f, 7.0f, 9.0f, 11.0f, 13.0f, 15.0f, 17.0f, 19.0f,
        -38.0f, -36.0f, -34.0f, -32.0f, -30.0f, -28.0f, -26.0f, -24.0f, -22.0f, -20.0f, -18.0f, -16.0f, -14.0f, -12.0f,
        -10.0f, -8.0f, -6.0f, -4.0f, -2.0f, 0.0f)
    }

    "support subtracting literal values from matrices" in {
      val minusTen: Mat[Float] = mat1 - Scalar(10)
      minusTen.getN(minusTen.total.toInt, 0) shouldBe Array(-9.0f, -8.0f, -7.0f, -6.0f, -5.0f, -4.0f, -3.0f, -2.0f,
        -1.0f, 0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f,
        16.0f, 17.0f, 18.0f, 19.0f, 20.0f, 21.0f, 22.0f, 23.0f, 24.0f, 25.0f, 26.0f, 27.0f, 28.0f, 29.0f, 30.0f, 31.0f,
        32.0f, 33.0f, 34.0f, 35.0f, 36.0f, 37.0f, 38.0f, 39.0f, 40.0f, 41.0f, 42.0f, 43.0f, 44.0f, 45.0f, 46.0f, 47.0f,
        48.0f, 49.0f, 50.0f)

      val tenMinus: Mat[Float] = Scalar(10) - mat1
      tenMinus.getN(tenMinus.total.toInt, 0) shouldBe Array(9.0f, 8.0f, 7.0f, 6.0f, 5.0f, 4.0f, 3.0f, 2.0f, 1.0f, 0.0f,
        -1.0f, -2.0f, -3.0f, -4.0f, -5.0f, -6.0f, -7.0f, -8.0f, -9.0f, -10.0f, -11.0f, -12.0f, -13.0f, -14.0f, -15.0f,
        -16.0f, -17.0f, -18.0f, -19.0f, -20.0f, -21.0f, -22.0f, -23.0f, -24.0f, -25.0f, -26.0f, -27.0f, -28.0f, -29.0f,
        -30.0f, -31.0f, -32.0f, -33.0f, -34.0f, -35.0f, -36.0f, -37.0f, -38.0f, -39.0f, -40.0f, -41.0f, -42.0f, -43.0f,
        -44.0f, -45.0f, -46.0f, -47.0f, -48.0f, -49.0f, -50.0f)

      val minusTenDouble: Mat[Float] = mat1 - 10d
      minusTenDouble.getN(minusTenDouble.total.toInt, 0) shouldBe Array(-9.0f, -8.0f, -7.0f, -6.0f, -5.0f, -4.0f, -3.0f, -2.0f,
        -1.0f, 0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f,
        16.0f, 17.0f, 18.0f, 19.0f, 20.0f, 21.0f, 22.0f, 23.0f, 24.0f, 25.0f, 26.0f, 27.0f, 28.0f, 29.0f, 30.0f, 31.0f,
        32.0f, 33.0f, 34.0f, 35.0f, 36.0f, 37.0f, 38.0f, 39.0f, 40.0f, 41.0f, 42.0f, 43.0f, 44.0f, 45.0f, 46.0f, 47.0f,
        48.0f, 49.0f, 50.0f)

      val tenMinusDouble: Mat[Float] = 10d - mat1
      tenMinusDouble.getN(tenMinusDouble.total.toInt, 0) shouldBe Array(9.0f, 8.0f, 7.0f, 6.0f, 5.0f, 4.0f, 3.0f, 2.0f, 1.0f, 0.0f,
        -1.0f, -2.0f, -3.0f, -4.0f, -5.0f, -6.0f, -7.0f, -8.0f, -9.0f, -10.0f, -11.0f, -12.0f, -13.0f, -14.0f, -15.0f,
        -16.0f, -17.0f, -18.0f, -19.0f, -20.0f, -21.0f, -22.0f, -23.0f, -24.0f, -25.0f, -26.0f, -27.0f, -28.0f, -29.0f,
        -30.0f, -31.0f, -32.0f, -33.0f, -34.0f, -35.0f, -36.0f, -37.0f, -38.0f, -39.0f, -40.0f, -41.0f, -42.0f, -43.0f,
        -44.0f, -45.0f, -46.0f, -47.0f, -48.0f, -49.0f, -50.0f)
    }

    "support matrix negation" in {
      val minusMat: Mat[Float] = -mat1
      minusMat.getN(minusMat.total.toInt,0) shouldBe Array(-1.0f, -2.0f, -3.0f, -4.0f, -5.0f, -6.0f, -7.0f, -8.0f,
        -9.0f, -10.0f, -11.0f, -12.0f, -13.0f, -14.0f, -15.0f, -16.0f, -17.0f, -18.0f, -19.0f, -20.0f, -21.0f, -22.0f,
        -23.0f, -24.0f, -25.0f, -26.0f, -27.0f, -28.0f, -29.0f, -30.0f, -31.0f, -32.0f, -33.0f, -34.0f, -35.0f, -36.0f,
        -37.0f, -38.0f, -39.0f, -40.0f, -41.0f, -42.0f, -43.0f, -44.0f, -45.0f, -46.0f, -47.0f, -48.0f, -49.0f, -50.0f,
        -51.0f, -52.0f, -53.0f, -54.0f, -55.0f, -56.0f, -57.0f, -58.0f, -59.0f, -60.0f)

      val minusMatExpr: Mat[Float] = -mat2.t()
      minusMatExpr.getN(minusMat.total.toInt,0) shouldBe Array(-1.0f, -4.0f, -7.0f, -10.0f, -13.0f, -16.0f, -19.0f,
        -22.0f, -25.0f, -28.0f, -31.0f, -34.0f, -37.0f, -40.0f, -43.0f, -46.0f, -49.0f, -52.0f, -55.0f, -58.0f, -2.0f,
        -5.0f, -8.0f, -11.0f, -14.0f, -17.0f, -20.0f, -23.0f, -26.0f, -29.0f, -32.0f, -35.0f, -38.0f, -41.0f, -44.0f,
        -47.0f, -50.0f, -53.0f, -56.0f, -59.0f, -3.0f, -6.0f, -9.0f, -12.0f, -15.0f, -18.0f, -21.0f, -24.0f, -27.0f,
        -30.0f, -33.0f, -36.0f, -39.0f, -42.0f, -45.0f, -48.0f, -51.0f, -54.0f, -57.0f, -60.0f)
    }

    "support per-element division of matrices" in {
      val quotient = mat1 / mat2.t
      quotient.getN(quotient.total.toInt,0) shouldBe Array(1.0f, 0.5f, 0.42857143f, 0.4f, 0.3846154f, 0.375f,
        0.36842105f, 0.36363637f, 0.36f, 0.35714287f, 0.3548387f, 0.3529412f, 0.35135135f, 0.35f, 0.3488372f, 0.3478261f,
        0.3469388f, 0.34615386f, 0.34545454f, 0.3448276f, 10.5f, 4.4f, 2.875f, 2.1818182f, 1.7857143f, 1.5294118f, 1.35f,
        1.2173913f, 1.1153846f, 1.0344827f, 0.96875f, 0.9142857f, 0.8684211f, 0.8292683f, 0.79545456f, 0.7659575f, 0.74f,
        0.7169811f, 0.6964286f, 0.6779661f, 13.666667f, 7.0f, 4.7777777f, 3.6666667f, 3.0f, 2.5555556f, 2.2380953f, 2.0f,
        1.8148148f, 1.6666666f, 1.5454545f, 1.4444444f, 1.3589743f, 1.2857143f, 1.2222222f, 1.1666666f, 1.117647f,
        1.074074f, 1.0350877f, 1.0f)
    }

    "support per-element division of a matrix by a literal value" in {
      val divideByTwo: Mat[Float] = mat1 / Scalar(2)
      divideByTwo.getN(divideByTwo.total.toInt,0) shouldBe Array(0.5f, 1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 4.5f,
        5.0f, 5.5f, 6.0f, 6.5f, 7.0f, 7.5f, 8.0f, 8.5f, 9.0f, 9.5f, 10.0f, 10.5f, 11.0f, 11.5f, 12.0f, 12.5f, 13.0f,
        13.5f, 14.0f, 14.5f, 15.0f, 15.5f, 16.0f, 16.5f, 17.0f, 17.5f, 18.0f, 18.5f, 19.0f, 19.5f, 20.0f, 20.5f, 21.0f,
        21.5f, 22.0f, 22.5f, 23.0f, 23.5f, 24.0f, 24.5f, 25.0f, 25.5f, 26.0f, 26.5f, 27.0f, 27.5f, 28.0f, 28.5f, 29.0f,
        29.5f, 30.0f)

      val divideByTwoDouble: Mat[Float] = mat1 / 2d
      divideByTwoDouble.getN(divideByTwoDouble.total.toInt,0) shouldBe Array(0.5f, 1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f,
        4.0f, 4.5f, 5.0f, 5.5f, 6.0f, 6.5f, 7.0f, 7.5f, 8.0f, 8.5f, 9.0f, 9.5f, 10.0f, 10.5f, 11.0f, 11.5f, 12.0f, 12.5f,
        13.0f, 13.5f, 14.0f, 14.5f, 15.0f, 15.5f, 16.0f, 16.5f, 17.0f, 17.5f, 18.0f, 18.5f, 19.0f, 19.5f, 20.0f, 20.5f,
        21.0f, 21.5f, 22.0f, 22.5f, 23.0f, 23.5f, 24.0f, 24.5f, 25.0f, 25.5f, 26.0f, 26.5f, 27.0f, 27.5f, 28.0f, 28.5f,
        29.0f, 29.5f, 30.0f)
      
      val twoDivideBy: Mat[Float] = Scalar(2) / mat1
      twoDivideBy.getN(twoDivideBy.total.toInt,0) shouldBe Array(2.0f, 1.0f, 0.6666667f, 0.5f, 0.4f, 0.33333334f,
        0.2857143f, 0.25f, 0.22222222f, 0.2f, 0.18181819f, 0.16666667f, 0.15384616f, 0.14285715f, 0.13333334f, 0.125f,
        0.11764706f, 0.11111111f, 0.10526316f, 0.1f, 0.0952381f, 0.09090909f, 0.08695652f, 0.083333336f, 0.08f,
        0.07692308f, 0.074074075f, 0.071428575f, 0.06896552f, 0.06666667f, 0.06451613f, 0.0625f, 0.060606062f,
        0.05882353f, 0.057142857f, 0.055555556f, 0.054054055f, 0.05263158f, 0.051282052f, 0.05f, 0.048780486f,
        0.04761905f, 0.046511628f, 0.045454547f, 0.044444446f, 0.04347826f, 0.04255319f, 0.041666668f, 0.040816326f,
        0.04f, 0.039215688f, 0.03846154f, 0.03773585f, 0.037037037f, 0.036363635f, 0.035714287f, 0.03508772f,
        0.03448276f, 0.033898305f, 0.033333335f)

      val twoDoubleDivideBy: Mat[Float] = 2d / mat1
      twoDoubleDivideBy.getN(twoDoubleDivideBy.total.toInt,0) shouldBe Array(2.0f, 1.0f, 0.6666667f, 0.5f, 0.4f,
        0.33333334f, 0.2857143f, 0.25f, 0.22222222f, 0.2f, 0.18181819f, 0.16666667f, 0.15384616f, 0.14285715f,
        0.13333334f, 0.125f, 0.11764706f, 0.11111111f, 0.10526316f, 0.1f, 0.0952381f, 0.09090909f, 0.08695652f,
        0.083333336f, 0.08f, 0.07692308f, 0.074074075f, 0.071428575f, 0.06896552f, 0.06666667f, 0.06451613f, 0.0625f,
        0.060606062f, 0.05882353f, 0.057142857f, 0.055555556f, 0.054054055f, 0.05263158f, 0.051282052f, 0.05f,
        0.048780486f, 0.04761905f, 0.046511628f, 0.045454547f, 0.044444446f, 0.04347826f, 0.04255319f, 0.041666668f,
        0.040816326f, 0.04f, 0.039215688f, 0.03846154f, 0.03773585f, 0.037037037f, 0.036363635f, 0.035714287f,
        0.03508772f, 0.03448276f, 0.033898305f, 0.033333335f)
    }

    "support matrix multiplication" in {
      val prod1 = mat1 * mat2
      prod1.shape() shouldBe Seq(3,3)
      prod1.total shouldBe 9
      prod1.getN(prod1.total.toInt,0) shouldBe Array(8190.0f, 8400.0f, 8610.0f, 19990.0f, 20600.0f, 21210.0f, 31790.0f, 32800.0f, 33810.0f)

      val prod2 = mat2 * mat1

      prod2.shape() shouldBe Seq(20,20)
      prod2.total shouldBe 400
      prod2.getN(prod2.total.toInt,0) shouldBe Array(
        166.0f, 172.0f, 178.0f, 184.0f, 190.0f, 196.0f, 202.0f, 208.0f, 214.0f, 220.0f, 226.0f, 232.0f, 238.0f, 244.0f,
        250.0f, 256.0f, 262.0f, 268.0f, 274.0f, 280.0f, 355.0f, 370.0f, 385.0f, 400.0f, 415.0f, 430.0f, 445.0f, 460.0f,
        475.0f, 490.0f, 505.0f, 520.0f, 535.0f, 550.0f, 565.0f, 580.0f, 595.0f, 610.0f, 625.0f, 640.0f, 544.0f, 568.0f,
        592.0f, 616.0f, 640.0f, 664.0f, 688.0f, 712.0f, 736.0f, 760.0f, 784.0f, 808.0f, 832.0f, 856.0f, 880.0f, 904.0f,
        928.0f, 952.0f, 976.0f, 1000.0f, 733.0f, 766.0f, 799.0f, 832.0f, 865.0f, 898.0f, 931.0f, 964.0f, 997.0f, 1030.0f,
        1063.0f, 1096.0f, 1129.0f, 1162.0f, 1195.0f, 1228.0f, 1261.0f, 1294.0f, 1327.0f, 1360.0f, 922.0f, 964.0f, 1006.0f,
        1048.0f, 1090.0f, 1132.0f, 1174.0f, 1216.0f, 1258.0f, 1300.0f, 1342.0f, 1384.0f, 1426.0f, 1468.0f, 1510.0f, 1552.0f,
        1594.0f, 1636.0f, 1678.0f, 1720.0f, 1111.0f, 1162.0f, 1213.0f, 1264.0f, 1315.0f, 1366.0f, 1417.0f, 1468.0f, 1519.0f,
        1570.0f, 1621.0f, 1672.0f, 1723.0f, 1774.0f, 1825.0f, 1876.0f, 1927.0f, 1978.0f, 2029.0f, 2080.0f, 1300.0f, 1360.0f,
        1420.0f, 1480.0f, 1540.0f, 1600.0f, 1660.0f, 1720.0f, 1780.0f, 1840.0f, 1900.0f, 1960.0f, 2020.0f, 2080.0f, 2140.0f,
        2200.0f, 2260.0f, 2320.0f, 2380.0f, 2440.0f, 1489.0f, 1558.0f, 1627.0f, 1696.0f, 1765.0f, 1834.0f, 1903.0f, 1972.0f,
        2041.0f, 2110.0f, 2179.0f, 2248.0f, 2317.0f, 2386.0f, 2455.0f, 2524.0f, 2593.0f, 2662.0f, 2731.0f, 2800.0f, 1678.0f,
        1756.0f, 1834.0f, 1912.0f, 1990.0f, 2068.0f, 2146.0f, 2224.0f, 2302.0f, 2380.0f, 2458.0f, 2536.0f, 2614.0f, 2692.0f,
        2770.0f, 2848.0f, 2926.0f, 3004.0f, 3082.0f, 3160.0f, 1867.0f, 1954.0f, 2041.0f, 2128.0f, 2215.0f, 2302.0f, 2389.0f,
        2476.0f, 2563.0f, 2650.0f, 2737.0f, 2824.0f, 2911.0f, 2998.0f, 3085.0f, 3172.0f, 3259.0f, 3346.0f, 3433.0f, 3520.0f,
        2056.0f, 2152.0f, 2248.0f, 2344.0f, 2440.0f, 2536.0f, 2632.0f, 2728.0f, 2824.0f, 2920.0f, 3016.0f, 3112.0f, 3208.0f,
        3304.0f, 3400.0f, 3496.0f, 3592.0f, 3688.0f, 3784.0f, 3880.0f, 2245.0f, 2350.0f, 2455.0f, 2560.0f, 2665.0f, 2770.0f,
        2875.0f, 2980.0f, 3085.0f, 3190.0f, 3295.0f, 3400.0f, 3505.0f, 3610.0f, 3715.0f, 3820.0f, 3925.0f, 4030.0f, 4135.0f,
        4240.0f, 2434.0f, 2548.0f, 2662.0f, 2776.0f, 2890.0f, 3004.0f, 3118.0f, 3232.0f, 3346.0f, 3460.0f, 3574.0f, 3688.0f,
        3802.0f, 3916.0f, 4030.0f, 4144.0f, 4258.0f, 4372.0f, 4486.0f, 4600.0f, 2623.0f, 2746.0f, 2869.0f, 2992.0f, 3115.0f,
        3238.0f, 3361.0f, 3484.0f, 3607.0f, 3730.0f, 3853.0f, 3976.0f, 4099.0f, 4222.0f, 4345.0f, 4468.0f, 4591.0f, 4714.0f,
        4837.0f, 4960.0f, 2812.0f, 2944.0f, 3076.0f, 3208.0f, 3340.0f, 3472.0f, 3604.0f, 3736.0f, 3868.0f, 4000.0f, 4132.0f,
        4264.0f, 4396.0f, 4528.0f, 4660.0f, 4792.0f, 4924.0f, 5056.0f, 5188.0f, 5320.0f, 3001.0f, 3142.0f, 3283.0f, 3424.0f,
        3565.0f, 3706.0f, 3847.0f, 3988.0f, 4129.0f, 4270.0f, 4411.0f, 4552.0f, 4693.0f, 4834.0f, 4975.0f, 5116.0f, 5257.0f,
        5398.0f, 5539.0f, 5680.0f, 3190.0f, 3340.0f, 3490.0f, 3640.0f, 3790.0f, 3940.0f, 4090.0f, 4240.0f, 4390.0f, 4540.0f,
        4690.0f, 4840.0f, 4990.0f, 5140.0f, 5290.0f, 5440.0f, 5590.0f, 5740.0f, 5890.0f, 6040.0f, 3379.0f, 3538.0f, 3697.0f,
        3856.0f, 4015.0f, 4174.0f, 4333.0f, 4492.0f, 4651.0f, 4810.0f, 4969.0f, 5128.0f, 5287.0f, 5446.0f, 5605.0f, 5764.0f,
        5923.0f, 6082.0f, 6241.0f, 6400.0f, 3568.0f, 3736.0f, 3904.0f, 4072.0f, 4240.0f, 4408.0f, 4576.0f, 4744.0f, 4912.0f,
        5080.0f, 5248.0f, 5416.0f, 5584.0f, 5752.0f, 5920.0f, 6088.0f, 6256.0f, 6424.0f, 6592.0f, 6760.0f, 3757.0f, 3934.0f,
        4111.0f, 4288.0f, 4465.0f, 4642.0f, 4819.0f, 4996.0f, 5173.0f, 5350.0f, 5527.0f, 5704.0f, 5881.0f, 6058.0f, 6235.0f,
        6412.0f, 6589.0f, 6766.0f, 6943.0f, 7120.0f
      )

      assertThrows[RuntimeException] {
        val invalid:Mat[Float] = mat1 * mat2.t
      }
    }

    "support per-element multiplication" in {
      val prod1 = mat1 x mat2.t
      
      prod1.shape() shouldBe Seq(3,20)
      prod1.total shouldBe 60
      prod1.getN(prod1.total.toInt,0) shouldBe Array(
        1.0f, 8.0f, 21.0f, 40.0f, 65.0f, 96.0f, 133.0f, 176.0f, 225.0f, 280.0f, 341.0f, 408.0f, 481.0f, 560.0f, 645.0f,
        736.0f, 833.0f, 936.0f, 1045.0f, 1160.0f, 42.0f, 110.0f, 184.0f, 264.0f, 350.0f, 442.0f, 540.0f, 644.0f, 754.0f,
        870.0f, 992.0f, 1120.0f, 1254.0f, 1394.0f, 1540.0f, 1692.0f, 1850.0f, 2014.0f, 2184.0f, 2360.0f, 123.0f, 252.0f,
        387.0f, 528.0f, 675.0f, 828.0f, 987.0f, 1152.0f, 1323.0f, 1500.0f, 1683.0f, 1872.0f, 2067.0f, 2268.0f, 2475.0f,
        2688.0f, 2907.0f, 3132.0f, 3363.0f, 3600.0f
      )
      
      val prod2 = mat2 x mat1.t
      prod2.shape() shouldBe Seq(20,3)
      prod2.total shouldBe 60
      prod2.getN(prod1.total.toInt,0) shouldBe Array(
        1.0f, 42.0f, 123.0f, 8.0f, 110.0f, 252.0f, 21.0f, 184.0f, 387.0f, 40.0f, 264.0f, 528.0f, 65.0f, 350.0f, 675.0f,
        96.0f, 442.0f, 828.0f, 133.0f, 540.0f, 987.0f, 176.0f, 644.0f, 1152.0f, 225.0f, 754.0f, 1323.0f, 280.0f, 870.0f,
        1500.0f, 341.0f, 992.0f, 1683.0f, 408.0f, 1120.0f, 1872.0f, 481.0f, 1254.0f, 2067.0f, 560.0f, 1394.0f, 2268.0f,
        645.0f, 1540.0f, 2475.0f, 736.0f, 1692.0f, 2688.0f, 833.0f, 1850.0f, 2907.0f, 936.0f, 2014.0f, 3132.0f, 1045.0f,
        2184.0f, 3363.0f, 1160.0f, 2360.0f, 3600.0f
      )

      assertThrows[RuntimeException] {
        val invalid = mat1 x mat2
      }
    }

    "support per-element multiplication of a matrix by a literal value" in {
      val expectedValue = Array(5.5f, 11.0f, 16.5f, 22.0f, 27.5f, 33.0f, 38.5f, 44.0f, 49.5f, 55.0f, 60.5f, 66.0f,
        71.5f, 77.0f, 82.5f, 88.0f, 93.5f, 99.0f, 104.5f, 110.0f, 115.5f, 121.0f, 126.5f, 132.0f, 137.5f, 143.0f,
        148.5f, 154.0f, 159.5f, 165.0f, 170.5f, 176.0f, 181.5f, 187.0f, 192.5f, 198.0f, 203.5f, 209.0f, 214.5f, 220.0f,
        225.5f, 231.0f, 236.5f, 242.0f, 247.5f, 253.0f, 258.5f, 264.0f, 269.5f, 275.0f, 280.5f, 286.0f, 291.5f, 297.0f,
        302.5f, 308.0f, 313.5f, 319.0f, 324.5f, 330.0f)
      
      val multiplyByFivePointFive: Mat[Float] = mat1 * Scalar(5.5f)
      multiplyByFivePointFive.getN(multiplyByFivePointFive.total.toInt, 0) shouldBe expectedValue

      val multiplyByFivePointFiveDouble: Mat[Float] = mat1 * 5.5d
      multiplyByFivePointFiveDouble.getN(multiplyByFivePointFiveDouble.total.toInt, 0) shouldBe expectedValue

      val fivePointFiveMultiplyBy: Mat[Float] = Scalar(5.5f) * mat1
      fivePointFiveMultiplyBy.getN(fivePointFiveMultiplyBy.total.toInt, 0) shouldBe expectedValue

      val fivePointFiveDoubleMultiplyBy: Mat[Float] = 5.5d * mat1
      fivePointFiveDoubleMultiplyBy.getN(fivePointFiveDoubleMultiplyBy.total.toInt, 0) shouldBe expectedValue
    }

    "supports boolean operators" in {
      val smaller: Mat[Float] = mat1 - 1
      val bigger: Mat[Float] = mat1 + 2

      (smaller < mat1).getN(mat1.total.toInt,0) shouldBe Array.fill(mat1.total.toInt)(255)
      (smaller > mat1).getN(mat1.total.toInt,0) shouldBe Array.fill(mat1.total.toInt)(0)

      (smaller <= mat1).getN(mat1.total.toInt,0) shouldBe Array.fill(mat1.total.toInt)(255)
      (smaller >= mat1).getN(mat1.total.toInt,0) shouldBe Array.fill(mat1.total.toInt)(0)
      (mat1 <= mat1).getN(mat1.total.toInt,0) shouldBe Array.fill(mat1.total.toInt)(255)
      (smaller >= mat1).getN(mat1.total.toInt,0) shouldBe Array.fill(mat1.total.toInt)(0)
      (mat1 >= mat1).getN(mat1.total.toInt,0) shouldBe Array.fill(mat1.total.toInt)(255)

      (smaller == mat1).getN(mat1.total.toInt, 0) shouldBe Array.fill(mat1.total.toInt)(0)
      (mat1 == mat1).getN(mat1.total.toInt, 0) shouldBe Array.fill(mat1.total.toInt)(255)

      (smaller != mat1).getN(mat1.total.toInt, 0) shouldBe Array.fill(mat1.total.toInt)(255)
      (mat1 != mat1).getN(mat1.total.toInt, 0) shouldBe Array.fill(mat1.total.toInt)(0)
    }

    "supports logical operators" in {
      val allOff = Mat[Int](Types.Cv8U, Some(1), 5,5, Scalar(0))
      val allOn = Mat[Int](Types.Cv8U, Some(1), 5,5, Scalar(255))
      val lowerOrder = Mat[Int](Types.Cv8U, Some(1), 5, 5, Scalar(15))
      val higherOrder = Mat[Int](Types.Cv8U, Some(1), 5, 5, Scalar(240))

      (allOff & allOn).getN(allOff.total.toInt,0) shouldBe Array.fill(allOn.total.toInt)(0)
      (lowerOrder & higherOrder).getN(allOff.total.toInt,0) shouldBe Array.fill(allOn.total.toInt)(0)
      (lowerOrder & allOn).getN(allOff.total.toInt,0) shouldBe Array.fill(allOn.total.toInt)(15)
      (allOff | allOn).getN(allOff.total.toInt,0) shouldBe Array.fill(allOn.total.toInt)(255)
      (lowerOrder | higherOrder).getN(allOff.total.toInt,0) shouldBe Array.fill(allOn.total.toInt)(255)
      (lowerOrder | allOn).getN(allOff.total.toInt,0) shouldBe Array.fill(allOn.total.toInt)(255)
      (lowerOrder ^ higherOrder).getN(allOff.total.toInt,0) shouldBe Array.fill(allOn.total.toInt)(255)
      (lowerOrder ^ allOn).getN(allOff.total.toInt,0) shouldBe Array.fill(allOn.total.toInt)(240)

      (~lowerOrder).getN(lowerOrder.total.toInt,0) shouldBe Array.fill(lowerOrder.total.toInt)(240)
    }

    "support min and max operations" in {
      val noMoreThanFifty: Mat[Float] = min(mat1,50d)
      noMoreThanFifty.getN(noMoreThanFifty.total.toInt,0) shouldBe Array(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f,
        9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f, 16.0f, 17.0f, 18.0f, 19.0f, 20.0f, 21.0f, 22.0f, 23.0f, 24.0f,
        25.0f, 26.0f, 27.0f, 28.0f, 29.0f, 30.0f, 31.0f, 32.0f, 33.0f, 34.0f, 35.0f, 36.0f, 37.0f, 38.0f, 39.0f, 40.0f,
        41.0f, 42.0f, 43.0f, 44.0f, 45.0f, 46.0f, 47.0f, 48.0f, 49.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f,
        50.0f, 50.0f, 50.0f, 50.0f)

      val atLeastFifty: Mat[Float] = max(50d,mat1)
      atLeastFifty.getN(atLeastFifty.total.toInt,0) shouldBe Array(50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f,
        50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f,
        50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f,
        50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 50.0f, 51.0f, 52.0f, 53.0f, 54.0f, 55.0f,
        56.0f, 57.0f, 58.0f, 59.0f, 60.0f)

      min(mat1,mat2.t).getN(mat1.total.toInt,0) shouldBe Array(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f,
        10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f, 16.0f, 17.0f, 18.0f, 19.0f, 20.0f, 2.0f, 5.0f, 8.0f, 11.0f, 14.0f,
        17.0f, 20.0f, 23.0f, 26.0f, 29.0f, 31.0f, 32.0f, 33.0f, 34.0f, 35.0f, 36.0f, 37.0f, 38.0f, 39.0f, 40.0f, 3.0f,
        6.0f, 9.0f, 12.0f, 15.0f, 18.0f, 21.0f, 24.0f, 27.0f, 30.0f, 33.0f, 36.0f, 39.0f, 42.0f, 45.0f, 48.0f, 51.0f,
        54.0f, 57.0f, 60.0f)

      max(mat1.t,mat2).getN(mat1.total.toInt,0) shouldBe Array(1.0f, 21.0f, 41.0f, 4.0f, 22.0f, 42.0f, 7.0f, 23.0f,
        43.0f, 10.0f, 24.0f, 44.0f, 13.0f, 25.0f, 45.0f, 16.0f, 26.0f, 46.0f, 19.0f, 27.0f, 47.0f, 22.0f, 28.0f, 48.0f,
        25.0f, 29.0f, 49.0f, 28.0f, 30.0f, 50.0f, 31.0f, 32.0f, 51.0f, 34.0f, 35.0f, 52.0f, 37.0f, 38.0f, 53.0f, 40.0f,
        41.0f, 54.0f, 43.0f, 44.0f, 55.0f, 46.0f, 47.0f, 56.0f, 49.0f, 50.0f, 57.0f, 52.0f, 53.0f, 58.0f, 55.0f, 56.0f,
        59.0f, 58.0f, 59.0f, 60.0f)
    }

  }
}
