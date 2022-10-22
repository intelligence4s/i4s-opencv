package i4s.opencv.core.model.mats

import i4s.opencv.core.constants.{CovariantFlags, CvPi, DecompositionMethods, DiscreteFourierTransformFlags, FlipCodes, GeneralizedMatrixMultiplyFlags, NormTypes, ReduceTypes, RotateCodes, SortFlags}
import i4s.opencv.core.model.{Point, Scalar}
import i4s.opencv.core.types.Types
import i4s.opencv.image.Codecs
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.immutable.Seq

class MatMathSpec extends AnyWordSpec with Matchers {
  "Mat" should {
    import syntax._

    val mask = Mat[Int](Types.Cv8U, Some(1), 5, 5)
    val maskValues = Array.fill(12)(1).appendedAll(Array.fill(13)(0))
    mask.put(maskValues)

    val matOfFives = Mat[Int](3,20, Scalar(5))
    val matOfTens = Mat[Int](3,20, Scalar(10))

    "support adding, subtracting to Mat of a new type" in {
      val matOfTwentiesF = matOfTens.addTo[Float](matOfTens)
      matOfTwentiesF.getN(matOfTwentiesF.total.toInt,0) shouldBe Array.fill(matOfTwentiesF.total.toInt)(20.0)

      val matOfFifteensF = (matOfTens + matOfTens).subtractTo[Float](matOfFives)
      matOfFifteensF.getN(matOfFifteensF.total.toInt,0) shouldBe Array.fill(matOfFifteensF.total.toInt)(15.0)
    }

    "support adding, subtracting to Mat of a new type with a mask" in {
      val mask = Mat[Int](Types.Cv8U,Some(1),3,20)
      val maskValues = Array.fill(30)(1).appendedAll(Array.fill(30)(0))
      mask.put(maskValues)

      val matOfTwentiesF = matOfTens.addTo[Float](matOfTens,mask)
      matOfTwentiesF.getN(matOfTwentiesF.total.toInt,0) shouldBe Array(20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0,
        20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0,
        20.0, 20.0, 20.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

      val matOfFifteensF = (matOfTens + matOfTens).subtractTo[Float](matOfFives,mask)
      matOfFifteensF.getN(matOfFifteensF.total.toInt,0) shouldBe Array(15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0,
        15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0, 15.0,
        15.0, 15.0, 15.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }


    "support per element multiply and divide to Mat of a new type" in {
      val matOfHundreds = matOfTens.multiplyTo[Float](matOfTens)
      matOfHundreds.getN(matOfHundreds.total.toInt, 0) shouldBe Array.fill(matOfHundreds.total.toInt)(100.0)

      val matOfTwos = matOfTens.divideTo[Float](matOfFives)
      matOfTwos.getN(matOfTwos.total.toInt, 0) shouldBe Array.fill(matOfTwos.total.toInt)(2.0)
    }

    "support addScaled values" in {
      val scaled = matOfTens.addScaled(matOfFives,3.0)
      scaled.getN(scaled.total.toInt,0) shouldBe Array.fill(scaled.total.toInt)(35)
    }

    "support addWeighted values" in {
      val weighted = matOfTens.addWeighted(4.0,matOfFives,3.0,0)
      weighted.getN(weighted.total.toInt,0) shouldBe Array.fill(weighted.total.toInt)(55)

      val weightedF = matOfTens.addWeightedTo[Float](2.5,matOfFives,0.5,0.75)
      weightedF.getN(weightedF.total.toInt,0) shouldBe Array.fill(weightedF.total.toInt)(28.25)
    }

    "supports random content generation" in {
      val uniformDist = Mat.randomUniformDistribution[Double](10,10,Scalar(-100),Scalar(100))
      val udValues = uniformDist.values
      udValues.foreach(_ === (0 +- 100))
      udValues.toArray === Array(6.056558665340636, -60.14815992538976, -19.788111388772556, 62.87701567047149,
        -12.573404176865488, -50.24205823853827, 54.62101133196215, 52.41874574054194, -38.44110387125254,
        40.48633993525674, -4.310558773749438, 58.438002082929806, -82.83137756943043, -84.98795132301272,
        -67.31532143626148, -40.04415461591756, 81.13078394790924, 41.937166889820695, -70.05750102906786,
        53.087985312102234, -75.14371735707849, -99.25428090592024, 10.302717587373884, 99.63986296439327,
        -68.2015692590218, -67.76439041648824, -92.36213282490355, -14.827734067948, 69.2255242214456,
        77.5208963237538, -47.05752104633177, -45.84339094620409, 90.53327891730896, 35.02625960106768,
        62.043272106434586, -63.207958857490574, -81.10875940587148, 63.18065880688161, 4.903532252942066,
        -23.83223003597114, -17.60104802184903, -48.892932483722596, 88.97918047069831, 80.02469574247596,
        -8.421687065250863, 68.26139385480825, -43.93997432985477, -22.912860300546956, -9.619707900163615,
        75.08670994653717, -68.11698696743397, 29.857318785015778, -14.908620987796887, 35.99008330597689,
        -30.09411398440129, 20.143338284632346, -44.4270629390298, 29.964040580871927, -12.848267697483152,
        57.52453784958559, -39.220481539195745, -84.48398598565005, 27.17918709665953, 33.46987148429177,
        49.55070266324475, -34.77623806147599, -25.104720247858133, 8.129885832220074, -92.97196138466623,
        -37.29512740304067, 63.198773133982364, 33.9739960295346, 29.30986486211074, -81.3959750746325,
        61.375807578445496, 63.68051019020313, 74.06195827334935, -96.83123802025356, 64.3525195540964,
        -45.31713801845821, 48.573874530847796, 75.66738767714773, -49.36046803524346, -57.89050354015135,
        -5.910761808556033, -62.99294536276449, 20.353376384092982, 10.091038192561133, 4.726487959219481,
        -57.60988243748821, 55.77794023279058, -99.66968259446118, -17.16215491823572, 55.63468243398262,
        -33.35846158351053, 59.747512210531895, 78.8579104137463, 69.65592505328121, 87.09009710409366,
        -6.656530666059636)
      udValues.sum shouldBe 64.58531323825017d

      val normalDist = Mat.randomNormalDistribution[Double](10,10,Scalar(0),Scalar(5))
      val ndValues = normalDist.values
      ndValues.sum === -30.84817017428577d
      ndValues.toArray === Array(-0.4922444373369217, 5.908437967300415, -9.90954041481018, 5.970410108566284,
        0.6264198571443558, 7.140477895736694, -7.183814644813538, 0.2517993561923504, 4.655517935752869,
        -3.4665656089782715, -7.742627263069153, -6.915076971054077, -6.279186010360718, -5.581269860267639,
        0.3669945150613785, 1.7700198292732239, -1.8625232577323914, -0.7839272916316986, 1.6761316359043121,
        2.3936592042446136, 1.2067954987287521, 1.8682049214839935, 0.5720062553882599, 5.054940581321716,
        0.399613194167614, -11.175884008407593, -6.896740794181824, -6.3250041007995605, -1.367371529340744,
        3.249098062515259, 0.8042486757040024, -4.265278875827789, 0.9353643655776978, 1.3748082518577576,
        -7.18575119972229, 1.324622631072998, -3.431974947452545, 6.3394564390182495, -0.45531656593084335,
        2.721099853515625, 0.14302141964435577, 1.241058111190796, 2.8188803791999817, -8.649121522903442,
        3.9939674735069275, -0.37254612892866135, -8.137972950935364, -11.156303882598877, 1.9285909831523895,
        2.2327688336372375, 6.755228042602539, 2.0610737800598145, -1.4968173205852509, -11.815143823623657,
        -0.4054369032382965, -6.229048371315002, 8.832156658172607, 1.7935384809970856, -1.139388531446457,
        1.4220717549324036, -3.26736181974411, 6.379001140594482, -2.191244214773178, -9.151541590690613,
        -1.7435985803604126, 5.616224408149719, 3.87893944978714, 0.4023740068078041, -1.4959609508514404,
        -5.736977458000183, -6.596702933311462, 6.802476644515991, 0.8455868810415268, 2.996855080127716,
        2.5118431448936462, -1.4003883302211761, 1.9259528815746307, 0.513404943048954, -0.12838524766266346,
        1.9899709522724152, -2.2465454041957855, -2.0878735184669495, -6.83595597743988, 9.252771735191345,
        -12.209683656692505, -3.3776915073394775, -1.3938288390636444, 9.542054533958435, 1.5145877003669739,
        1.107773631811142, -2.589849531650543, 4.728054106235504, -5.236791968345642, 3.3118709921836853,
        2.586655020713806, -11.40949010848999, 10.736961364746094, 3.6412593722343445, 12.223222255706787,
        6.633255481719971)
    }

    "supports convertScaleAbs" in {
      val normalDist = Mat.randomNormalDistribution[Double](4,4,Scalar(0),Scalar(2))

      val expected = normalDist.values.map(v => math.abs(math.round(v).toInt))
      val converted = normalDist.convertScaleAbs().values
      converted.zip(expected).foreach { case (v,e) => v === e }

      val scaledExpected = normalDist.values.map(v => math.abs(math.round(v * 2.0 + 0.5).toInt))
      val scaled = normalDist.convertScaleAbs(2,0.5).values

      scaled.zip(scaledExpected).foreach { case (v,e) => v === e}
    }

    "support lookupTransform" in {
      val lut = Mat[Double](256)
      lut.put((0 to 255).map(_ * 1.5d))

      val map = Mat[Byte](depth = Types.Cv8S,Some(4),5,5)
      map.put((255 until 155 by -1).map(_.toByte))
      val transformed = map.lookupTransform(lut)

      transformed.values.size shouldBe 100
      transformed.channels shouldBe 4
      transformed.values.zip(transformed.values.tail).foreach { case (elem,next) => elem - next shouldBe 1.5 }
    }

    "counts nonzero elements" in {
      mask.countNonZero === 12
    }

    "sums elements" in {
      mask.sumElements === 12
    }

    "finds nonzero elements" in {
      val mat3d = Mat[Float](init = Scalar(0.25),3,3,3)
      mat3d.put(1,0,Seq(0f,0f,0f,0f))

      assertThrows[RuntimeException] {
        val tooManyDimmensions = mat3d.findNonZero()
      }

      val mat2d = Mat[Float](init = Scalar(0.25),5,5)
      mat2d.put(1,0,Seq(0f,0f,0f,0f,0f,0f,0f,0f))

      val nonZeroIndexes = mat2d.findNonZero()
      val nonZeros = nonZeroIndexes.map(pt => mat2d.get(pt))
      !nonZeros.contains(0) shouldBe true
    }

    "support calculating a mean" in {
      val mat2d = Mat[Float](Types.Cv32F,Some(4),5,5,5)
      mat2d.put((0 until 500).map(_.toFloat))
      mat2d.mean() shouldBe Scalar(248,249,250,251)

      val mask = Mat[Int](Types.Cv8U,Some(1),Scalar(0),5,5,5)
      mask.put(Array.fill(50)(1))
      mat2d.mean(mask) shouldBe Scalar(98,99,100,101)
    }

    "support calculating the mean and standard deviation" in {
      val mat2d = Mat[Float](Types.Cv32F, Some(4), 5, 5, 5)
      mat2d.put((0 until 500).map(_.toFloat))

      val (means, stdDevs) = mat2d.meansWithStdDeviation()
      means shouldBe Scalar(248.0,249.0,250.0,251.0)
      stdDevs shouldBe Scalar(144.33294842134973,144.33294842134973,144.33294842134973,144.33294842134973)

      val mask = Mat[Int](Types.Cv8U, Some(1), Scalar(0), 5, 5, 5)
      mask.put(Array.fill(50)(1))
      val (maskedMeans,maskedStdDevs) = mat2d.meansWithStdDeviation(mask)
      maskedMeans shouldBe Scalar(98.0,99.0,100.0,101.0)
      maskedStdDevs shouldBe Scalar(57.723478758647246,57.723478758647246,57.723478758647246,57.723478758647246)
    }

    "support calculating norm" in {
      val firstPoints = Mat[Float](Types.Cv32F,Some(1),3,3)
      firstPoints.put((1 to 9).map(_.toFloat))
      val secondPoints = Mat[Float](Types.Cv32F,Some(1),3,3)
      secondPoints.put(List[Float](2,4,5,6,8,10,12,13,1))

      firstPoints.norm(NormTypes.Inf) shouldBe 9
      firstPoints.norm(NormTypes.L1) shouldBe 45
      firstPoints.norm() shouldBe 16.881943016134134
      firstPoints.norm(NormTypes.L2) shouldBe 16.881943016134134
      firstPoints.norm(NormTypes.L2Square) shouldBe 285

      firstPoints.norm(secondPoints,NormTypes.Inf) shouldBe 8
      firstPoints.norm(secondPoints,NormTypes.L1) shouldBe 32
      firstPoints.norm(secondPoints) shouldBe 12.328828005937952
      firstPoints.norm(secondPoints,NormTypes.L2) shouldBe 12.328828005937952
      firstPoints.norm(secondPoints,NormTypes.L2Square) shouldBe 152
    }

    "support calculating peak signal to noise ratio" in {
      val original = Codecs.readImageFromResource("test-image-org.jpg")
      val compressed = Codecs.readImageFromResource("test-image-copy.jpg")

      original.isDefined === true
      compressed.isDefined === true

      val imageMat = original.get.toMat
      val compressedMat = compressed.get.toMat

      imageMat.peakSignalToNoiseRatio(compressedMat) shouldBe 28.080971567122695
      imageMat.peakSignalToNoiseRatio(imageMat) shouldBe 361.20199909921956
    }

    "support calculating batch distances" in {
      val twoGroups = Mat[Float](10,2, Scalar(0))
      (0 until twoGroups.rows) foreach(i => twoGroups.put(i,Seq((i+1) * 10f, 100 - i*10f)))
      val testValues = Mat[Float](2,2,Scalar(0))
      testValues.put(0,Seq(50f,90f))
      testValues.put(1,Seq(90f,50f))

      val (ns,is) = twoGroups.batchDistance(testValues)
      ns.values.toArray shouldBe Array(41.231056f, 30.0f, 22.36068f, 22.36068f, 30.0f, 30.0f, 22.36068f, 22.36068f, 30.0f, 41.231056f)
      is.values.toArray shouldBe Array(0,0,0,0,0,1,1,1,1,1)
    }

    "support normalization of values" in {
      val values = Mat[Float](3, 1)
      values.put(0,Seq(2f, 8f, 10f))

      values.normalize(1.0,0.0,NormTypes.L1) === Array(0.1f, 0.4f, 0.5f)
      values.normalize(1.0,0.0,NormTypes.L2) === Array(0.15430336f, 0.6172134f, 0.7715168f)
      values.normalize(1.0,0.0,NormTypes.Inf) === Array(0.2f, 0.8f, 1.0f)
      values.normalize(1.0,0.0,NormTypes.MinMax) === Array(0.0f, 0.75f, 1.0f)
    }

    "support searching for min and max values" in {
      val values = Mat[Float](3,3)
      values.put(0f, 1f, 10f, 2f, -1f, 5f, -1f, 10f, 8f)

      values.minMax() === (-1f,10f)
      values.minMaxWithLocation() === (-1f,10f,Point(1,2),Point(0,2))
    }

    "support reduction to min value indexes" in {
      val values = Mat[Float](3, 3)
      values.put(0f, 1f, 10f, 2f, -1f, 5f, -1f, 10f, 8f)

      values.reduceArgMin(0).values.toArray === Array(2,1,1)
      values.reduceArgMin(1).values.toArray === Array(0,1,0)
    }

    "support reduction to max value indexes" in {
      val values = Mat[Float](3, 3)
      values.put(0f, 1f, 10f, 2f, -1f, 5f, -1f, 10f, 8f)

      values.reduceArgMax(0).values.toArray === Array(1,2,0)
      values.reduceArgMax(1).values.toArray === Array(2,2,1)
    }

    "support finding min and max values with indexes" in {
      val values = Mat[Float](3, 3, 3)

      val seed = Seq(0f, 1f, 10f, 2f, -1f, 5f, -1f, 10f, 8f)
      (0 until 3).foreach(i => values.put(i,seed.map(_ + i)))

      values.minMaxWithIndexes() === (-1,12,Array(0,1,1),Array(2,0,2))
    }

    "support reduce" in {
      val values = Mat[Float](3, 3)
      values.put(0f, 1f, 10f, 2f, -1f, 5f, -1f, 10f, 8f)

      values.reduce(0,ReduceTypes.Average).values.toArray === Array(0.33333334, 3.3333335, 7.666667)
      values.reduce(1,ReduceTypes.Average).values.toArray === Array(3.6666667, 2.0, 5.666667)

      values.reduceTo[Double](0,ReduceTypes.Average) === Array(0.3333333333333333d, 3.333333333333333d, 7.666666666666666d)
    }

    "support merging channels" in {
      val row1 = Mat[Float](1, 3)
      row1.put(0f, 1f, 10f)
      val row2 = Mat[Float](1, 3)
      row2.put(2f, -1f, 5f)
      val row3 = Mat[Float](1, 3)
      row3.put(-1f, 10f, 8f)
      val rows = Mat[Float](Types.Cv32F,Some(2),1,3)
      rows.put(-5f, 19f, -1f, 2f, 5f, -10f)

      val values = row1.merge(row2,row3,rows)
      values.shape() === Seq(1,3)
      values.channels() === 5
      values.values.toArray === Array(0.0, 2.0, -1.0, 1.0, -1.0, 10.0, 10.0, 5.0, 8.0, -5.0, 19.0, -1.0, 2.0, 5.0, -10.0)
    }

    "support splitting out channels" in {
      val values = Mat[Float](Types.Cv32F,Some(3),1,3)
      values.put(0f, 1f, 10f, 2f, -1f, 5f, -1f, 10f, 8f)

      val byChannel = values.split()
      byChannel.size shouldBe 3
      byChannel.map(_.values.toArray) === Seq(Array(0.0, 2.0, -1.0),Array(1.0, -1.0, 10.0),Array(10.0, 5.0, 8.0))
    }

    "support extracting a channel" in {
      val values = Mat[Float](Types.Cv32F, Some(3), 1, 3)
      values.put(0f, 1f, 10f, 2f, -1f, 5f, -1f, 10f, 8f)
      values.extractChannel(1).values.toArray === Array(1,-1,10)
    }

    "support inserting channel" in {
      val values = Mat[Float](Types.Cv32F, Some(3), 1, 3)
      values.put(0f, 1f, 10f, 2f, -1f, 5f, -1f, 10f, 8f)

      val inserted = Mat[Float](1,3)
      inserted.put(40f,41f,42f)

      values.insertChannel(1,inserted).values.toArray === Array(0, 40, 10, 2, 41, 5, -1, 42, 8)
    }

    "support flipping matrix values" in {
      val values = Mat[Float](3, 4)
      values.put((1 to 12).map(_.toFloat))

      values.flip(FlipCodes.Vertical).values.toArray === Array(9.0, 10.0, 11.0, 12.0, 5.0, 6.0, 7.0, 8.0, 1.0, 2.0, 3.0, 4.0)
      values.flip(FlipCodes.Horizontal).values.toArray === Array(4.0, 3.0, 2.0, 1.0, 8.0, 7.0, 6.0, 5.0, 12.0, 11.0, 10.0, 9.0)
      values.flip(FlipCodes.Both).values.toArray === Array(12.0, 11.0, 10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0)
    }

    "support rotating matrix values" in {
      val values = Mat[Float](3, 4)
      values.put((1 to 12).map(_.toFloat))

      values.rotate(RotateCodes.OneEighty).values.toArray === Array(12.0, 11.0, 10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0)
      values.rotate(RotateCodes.ClockwiseNinety).values.toArray === Array(9.0, 5.0, 1.0, 10.0, 6.0, 2.0, 11.0, 7.0, 3.0, 12.0, 8.0, 4.0)
      values.rotate(RotateCodes.CounterClockwiseNinety).values.toArray === Array(4.0, 8.0, 12.0, 3.0, 7.0, 11.0, 2.0, 6.0, 10.0, 1.0, 5.0, 9.0)
    }

    "support repeat values" in {
      val values = Mat[Float](3, 4)
      values.put((1 to 12).map(_.toFloat))

      val byFour = values.repeat(2,2)
      byFour.rows shouldBe 6
      byFour.cols shouldBe 8
      byFour.values.toArray shouldBe Array(1.0, 2.0, 3.0, 4.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 5.0, 6.0, 7.0, 8.0,
        9.0, 10.0, 11.0, 12.0, 9.0, 10.0, 11.0, 12.0, 1.0, 2.0, 3.0, 4.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 5.0, 6.0,
        7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 9.0, 10.0, 11.0, 12.0)
    }

    "support vertical concat of matrices" in {
      val row1 = Mat[Float](1, 3)
      row1.put((1 to 3).map(_.toFloat))
      val row2 = Mat[Float](1, 3)
      row2.put((4 to 6).map(_.toFloat))
      val row3 = Mat[Float](1, 3)
      row3.put((7 to 9).map(_.toFloat))

      row1.vconcat(row2,row3).values.toArray === Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
    }

    "support horizontal concat of matrices" in {
      val col1 = Mat[Float](3, 1)
      col1.put((1 to 3).map(_.toFloat))
      val col2 = Mat[Float](3, 1)
      col2.put((4 to 6).map(_.toFloat))
      val col3 = Mat[Float](3, 1)
      col3.put((7 to 9).map(_.toFloat))

      col1.hconcat(col2, col3).values.toArray === Array(1.0, 4.0, 7.0, 2.0, 5.0, 8.0, 3.0, 6.0, 9.0)
    }

    "support bitwise operations" in {
      val allOff = Mat[Int](Types.Cv8U, Some(1), 5, 5, Scalar(0))
      val allOn = Mat[Int](Types.Cv8U, Some(1), 5, 5, Scalar(255))
      val lowerOrder = Mat[Int](Types.Cv8U, Some(1), 5, 5, Scalar(15))
      val higherOrder = Mat[Int](Types.Cv8U, Some(1), 5, 5, Scalar(240))

      allOff.bitwiseAnd(allOn).values.toArray === Array.fill(allOn.total.toInt)(0)
      lowerOrder.bitwiseAnd(higherOrder).values.toArray === Array.fill(allOn.total.toInt)(0)
      lowerOrder.bitwiseAnd(allOn).values.toArray === Array.fill(allOn.total.toInt)(15)

      allOff.bitwiseOr(allOn).values.toArray === Array.fill(allOn.total.toInt)(255)
      lowerOrder.bitwiseOr(higherOrder).values.toArray === Array.fill(allOn.total.toInt)(255)
      lowerOrder.bitwiseOr(allOn).values.toArray === Array.fill(allOn.total.toInt)(255)

      lowerOrder.bitwiseXor(higherOrder).values.toArray === Array.fill(allOn.total.toInt)(255)
      lowerOrder.bitwiseXor(allOn).values.toArray === Array.fill(allOn.total.toInt)(240)

      lowerOrder.bitwiseNot.values.toArray === Array.fill(lowerOrder.total.toInt)(240)
    }

    "support absolute diffing" in {
      val mat1 = Mat[Int](5,5)
      mat1.put(1 to 25)

      val mat2 = Mat[Int](5,5)
      mat2.put(2 to 26)

      mat1.absoluteDiff(mat2).values.toArray === Array.fill(mat1.total.toInt)(1)
      mat2.absoluteDiff(mat1).values.toArray === Array.fill(mat1.total.toInt)(1)
    }

    "support copying to another matrix" in {
      val mat1 = Mat[Int](5, 5)
      mat1.put(1 to 25)

      val mask = Mat[Int](Types.Cv8U,Some(1),5,5,Scalar(0))
      mask.put(Array.fill(12)(1))
      val mat2 = mat1.copy(mask)
      mat2.values.toArray === Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    }

    "support inRange" in {
      val values = Mat[Float](5,5)
      values.put((1 to 25).map(_.toFloat))

      values.inRange(Scalar(5),Scalar(15)).values.toArray === Array(0, 0, 0, 0, 255, 255, 255, 255, 255, 255, 255, 255,
        255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    }

    "support min and max evaluations" in {
      val mat1 = Mat[Float](5,5)
      mat1.put((1 to 25).map(_.toFloat))

      val mat2 = Mat[Float](5,5)
      mat2.put((25 to 1 by -1).map(_.toFloat))

      mat1.min(mat2).values.toArray === Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 12.0,
        11.0, 10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0)
      mat1.max(mat2).values.toArray === Array(25.0, 24.0, 23.0, 22.0, 21.0, 20.0, 19.0, 18.0, 17.0, 16.0, 15.0, 14.0,
        13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0)
    }

    "support sqrt operations" in {
      val mat1 = Mat[Float](5, 5)
      mat1.put((1 to 25).map(_.toFloat))

      mat1.sqrt().values.mkString(", ").toArray === Array(1.0, 1.4142135, 1.7320508, 2.0, 2.236068, 2.4494898, 2.6457512,
        2.828427, 3.0, 3.1622777, 3.3166249, 3.4641016, 3.6055512, 3.7416575, 3.8729835, 4.0, 4.1231055, 4.2426405,
        4.358899, 4.472136, 4.582576, 4.690416, 4.7958317, 4.8989797, 5.0)
    }

    "support pow operations" in {
      val mat1 = Mat[Float](5, 5)
      mat1.put((1 to 25).map(_.toFloat))

      mat1.pow(3).values.toArray === Array(1.0, 8.0, 27.0, 64.0, 125.0, 216.0, 343.0, 512.0, 729.0, 1000.0,
        1331.0, 1728.0, 2197.0, 2744.0, 3375.0, 4096.0, 4913.0, 5832.0, 6859.0, 8000.0, 9261.0, 10648.0, 12167.0,
        13824.0, 15625.0)
    }

    "support exp operations" in {
      val mat1 = Mat[Float](5, 5)
      mat1.put((1 to 25).map(_.toFloat))

      mat1.exp().values.toArray === Array(2.7182817, 7.3890553, 20.085533, 54.59815, 148.41316, 403.42868, 1096.6333,
        2980.9578, 8103.0806, 22026.467, 59874.13, 162754.7, 442413.38, 1202604.8, 3269015.5, 8886109.0, 2.4154958E7,
        6.5659916E7, 1.7848224E8, 4.85165216E8, 1.31881446E9, 3.5849111E9, 9.7447895E9, 2.64890962E10, 7.2004854E10)
    }

    "support log operations" in {
      val mat1 = Mat[Float](5, 5)
      mat1.put((1 to 25).map(_.toFloat))

      mat1.log().values.toArray === Array(0.0, 0.6931472, 1.0986123, 1.3862944, 1.609438, 1.7917595, 1.9459102,
        2.0794415, 2.1972246, 2.3025851, 2.3978953, 2.4849067, 2.5649493, 2.6390574, 2.7080503, 2.7725887, 2.8332133,
        2.8903718, 2.944439, 2.9957323, 3.0445225, 3.0910425, 3.1354942, 3.1780539, 3.218876)
    }

    "support polar to cartesian conversion" in {
      val polar = Mat[Double](Types.Cv64F,Some(2),5,5)
      val angles = (0 to 24).map(_.toDouble * CvPi / 16)
      val lengths = (0 to 24).map(_.toDouble)

      polar.put(angles.zip(lengths).flatMap(t => List(t._1,t._2)))
      val cartesian = polar.polarToCartesian(angleInDegrees = false)

      cartesian.channels === 2
      cartesian.shape === Seq(5,5)

      cartesian.values.sliding(2,2).map(t => (t.head,t.last)).toList === List((0.0,0.0),
        (0.9807852506637573,0.19509032368659973), (1.8477590084075928,0.7653669118881226),
        (2.4944087862968445,1.6667107343673706), (2.8284270763397217,2.8284270763397217),
        (2.7778512239456177,4.1573479771614075), (2.2961005568504333,5.543277025222778),
        (1.3656324744224548,6.865496754646301), (-3.4969110629390343E-7,8.0), (-1.7558129131793976,8.827067255973816),
        (-3.826833963394165,9.238795638084412), (-6.111272037029266,9.146166205406189),
        (-8.485281229019165,8.485281229019165), (-10.809105515480042,7.222412407398224),
        (-12.93431305885315,5.357568800449371), (-14.711779654026031,2.926354631781578),
        (-16.0,-1.3987644251756137E-6), (-16.673350274562836,-3.316534236073494), (-16.629831075668335,-6.8883016705513),
        (-15.797921180725098,-10.555835783481598), (-14.142136573791504,-14.142134189605713),
        (-11.666978895664215,-17.46085900068283), (-8.419038653373718,-20.32534909248352),
        (-4.487078815698624,-22.55806076526642), (2.861971353240733E-7,-24.0))
    }

    "support cartseian to polar conversion" in {
      val cartesian = Mat[Double](Types.Cv64F, Some(2), 5, 5)
      val xs = (0 to 24).map(_.toDouble)
      val ys = (24 to 0 by -1).map(_.toDouble)

      cartesian.put(xs.zip(ys).flatMap(t => List(t._1,t._2)))
      val polar = cartesian.cartesianToPolar(angleInDegrees = false)

      polar.channels === 2
      polar.shape === Seq(5,5)

      polar.values.sliding(2,2).map(t => (t.head,t.last)).toList === List((24.0,1.5707963705062866),
        (23.021728866442675,1.5273540019989014), (22.090722034374522,1.4801503419876099),
        (21.213203435596427,1.4289101362228394), (20.396078054371138,1.3733960390090942),
        (19.6468827043885,1.3134393692016602), (18.973665961010276,1.2489807605743408),
        (18.384776310850235,1.180112600326538), (17.88854381999832,1.1071128845214844),
        (17.4928556845359,1.0304412841796875), (17.204650534085253,0.9506745338439941),
        (17.029386365926403,0.8684878945350647), (16.97056274847714,0.7852315902709961),
        (17.029386365926403,0.7023084163665771), (17.204650534085253,0.6201217770576477),
        (17.4928556845359,0.5403550267219543), (17.88854381999832,0.46368342638015747),
        (18.384776310850235,0.39068377017974854), (18.973665961010276,0.3218156397342682),
        (19.6468827043885,0.2573569416999817), (20.396078054371138,0.19740034639835358),
        (21.213203435596427,0.1418861597776413), (22.090722034374522,0.09064599126577377),
        (23.021728866442675,0.04344228655099869), (24.0,0.0))
    }

    "support calculating rotation angle of 2d vectors" in {
      val cartesian = Mat[Double](Types.Cv64F, Some(2), 5, 5)
      val xs = (0 to 24).map(_.toDouble)
      val ys = (24 to 0 by -1).map(_.toDouble)

      cartesian.put(xs.zip(ys).flatMap(t => List(t._1, t._2)))
      val angles = cartesian.phase(angleInDegrees = false)

      angles.channels === 1
      angles.shape === Seq(5, 5)
      angles.values.toArray === Array(1.5707963705062866, 1.5273540019989014, 1.4801503419876099, 1.4289101362228394,
        1.3733960390090942, 1.3134393692016602, 1.2489807605743408, 1.180112600326538, 1.1071128845214844,
        1.0304412841796875, 0.9506745338439941, 0.8684878945350647, 0.7852315902709961, 0.7023084163665771,
        0.6201217770576477, 0.5403550267219543, 0.46368342638015747, 0.39068377017974854, 0.3218156397342682,
        0.2573569416999817, 0.19740034639835358, 0.1418861597776413, 0.09064599126577377, 0.04344228655099869, 0.0)
    }

    "support calculating magnitude of 2d vectors" in {
      val cartesian = Mat[Double](Types.Cv64F, Some(2), 5, 5)
      val xs = (0 to 24).map(_.toDouble)
      val ys = (24 to 0 by -1).map(_.toDouble)

      cartesian.put(xs.zip(ys).flatMap(t => List(t._1, t._2)))
      val magnitudes = cartesian.magnitude()

      magnitudes.channels === 1
      magnitudes.shape === Seq(5, 5)

      magnitudes.values.toArray === Array(24.0, 23.021728866442675, 22.090722034374522, 21.213203435596427,
        20.396078054371138, 19.6468827043885, 18.973665961010276, 18.384776310850235, 17.88854381999832,
        17.4928556845359, 17.204650534085253, 17.029386365926403, 16.97056274847714, 17.029386365926403,
        17.204650534085253, 17.4928556845359, 17.88854381999832, 18.384776310850235, 18.973665961010276,
        19.6468827043885, 20.396078054371138, 21.213203435596427, 22.090722034374522, 23.021728866442675, 24.0)
    }

    "support range checking" in {
      val mat = Mat[Float](5,5)
      mat.put((1 to 25).map(_.toFloat))

      mat.checkRange(quiet = true,0,26) shouldBe true
      mat.checkRange(quiet = true,1,25) shouldBe false
      assertThrows[RuntimeException] {
        mat.checkRange(quiet = false,1,25)
      }
    }

    "support range checking with result" in {
      val mat = Mat[Float](5,5)
      mat.put((1 to 25).map(_.toFloat))

      mat.checkRangeWithPos(quiet = true,0,26) shouldBe (true,None)
      mat.checkRangeWithPos(quiet = true,0,10) shouldBe (false,Some(Point(4,1)))

      assertThrows[RuntimeException] {
        mat.checkRangeWithPos(quiet = false,1,25)
      }
    }

    "support patching NaNs in a matrix" in {
      val mat = Mat[Float](5,5, Scalar(Float.NaN))
      mat.pathNaNs(CvPi)
      mat.values.mkString(", ").toArray === Array.fill(25)(CvPi)
    }

    "support generalized matrix multiplication" in {
      val mat1 = Mat[Float](2,5)
      mat1.put((1 to 10).map(_.toFloat))

      val mat2 = Mat[Float](2,5,Scalar(2))
      val mat3 = Mat[Float](2,2,Scalar(3))

      mat1.generalizedMatrixMultiply(mat2,2, Set(GeneralizedMatrixMultiplyFlags.TransposeFirst))
        .values.toArray === Array(60.0, 60.0, 160.0, 160.0)
      mat1.generalizedMatrixMultiply(mat2,2,mat3,0.5,Set(GeneralizedMatrixMultiplyFlags.TransposeFirst))
        .values.toArray === Array(61.5, 61.5, 161.5, 161.5)
    }

    "support multiplying a matrix with the transposed version of itself" in {
      val mat1 = Mat[Float](2, 5)
      mat1.put((1 to 10).map(_.toFloat))

      val delta = Mat[Float](2,5, Scalar(2))

      mat1.multiplyTransposed(transposeFirst = false).values.toArray === Array(55.0, 130.0, 130.0, 330.0)
      mat1.multiplyTransposed(transposeFirst = true).values.toArray === Array(37.0, 44.0, 51.0, 58.0, 65.0, 44.0, 53.0,
        62.0, 71.0, 80.0, 51.0, 62.0, 73.0, 84.0, 95.0, 58.0, 71.0, 84.0, 97.0, 110.0, 65.0, 80.0, 95.0, 110.0, 125.0)

      mat1.multiplyTransposed(transposeFirst = false, delta, scale = 0.5).values.toArray === Array(7.5, 20.0, 20.0, 95.0)
      mat1.multiplyTransposed(transposeFirst = true, delta, scale = 0.5).values.toArray === Array(8.5, 10.0, 11.5, 13.0,
        14.5, 10.0, 12.5, 15.0, 17.5, 20.0, 11.5, 15.0, 18.5, 22.0, 25.5, 13.0, 17.5, 22.0, 26.5, 31.0, 14.5, 20.0,
        25.5, 31.0, 36.5)
    }

    "transpose a matrix" in {
      val mat = Mat[Float](2, 5)
      mat.put((1 to 10).map(_.toFloat))

      val matT = mat.transpose()
      matT.shape === Seq(5,2)
      matT.values.toArray === Array(1.0, 6.0, 2.0, 7.0, 3.0, 8.0, 4.0, 9.0, 5.0, 10.0)

      val bigMat = Mat[Float](2,3,4)
      bigMat.put((1 to 24).map(_.toFloat))

      val bigMatT = bigMat.nDimensionalTranspose(Seq(2,1,0))
      bigMatT.shape() === Seq(4,3,2)
      bigMatT.values.toArray === Array(1.0, 13.0, 5.0, 17.0, 9.0, 21.0, 2.0, 14.0, 6.0, 18.0, 10.0, 22.0, 3.0, 15.0,
        7.0, 19.0, 11.0, 23.0, 4.0, 16.0, 8.0, 20.0, 12.0, 24.0)
    }

    "perform transformation" in {
      val mat = Mat[Float](Types.Cv32F, Some(3), 2, 5)
      mat.put((1 to 30).map(_.toFloat))

      val m = Mat[Float](3,3)
      m.put((1 to 9).map(_.toFloat))

      mat.transform(m).values.toArray === Array(14.0, 32.0, 50.0, 32.0, 77.0, 122.0, 50.0, 122.0, 194.0,
        68.0, 167.0, 266.0, 86.0, 212.0, 338.0, 104.0, 257.0, 410.0, 122.0, 302.0, 482.0, 140.0, 347.0, 554.0, 158.0,
        392.0, 626.0, 176.0, 437.0, 698.0)
    }

    "perform perspective transformation" in {
      val mat = Mat[Float](Types.Cv32F, Some(3), 5)
      mat.put((1 to 5).flatMap(v => List(v.toFloat,v.toFloat,v.toFloat)))

      val m = Mat[Float](4,4)
      m.put(1f,10f,20f,30f,2f,12f,22f,33f,3f,14f,25f,35f,4f,16f,25f,34f)
      mat.perspectiveTransform(m).values.toArray === Array(0.7721519, 0.87341774, 0.9746835, 0.7419355, 0.8467742,
        0.9596774, 0.7278106, 0.83431953, 0.9526627, 0.7196262, 0.8271028, 0.94859815, 0.71428573, 0.82239383,
        0.9459459)
    }

    "inverts a matrix " in {
      val mat = Mat[Float](2, 2, Scalar(0))
      mat.put(4f,3f,3f,2f)

      val (result, inverted) = mat.invert()
      result === 1
      inverted.values.toArray === Array(-2.0, 3.0, 3.0, -4.0)

      val mat2 = Mat[Float](2,3, Scalar(0))
      mat2.put(4f,3f,0f,3f,2f)

      val (result2, inverted2) = mat2.invert(DecompositionMethods.SVD)
      result2 === 0.13164648413658142
      inverted2.values.toArray === Array(-2.0000005, 3.0000005, 3.0000005, -4.0000005, 0.0, 0.0)

      assertThrows[RuntimeException](mat2.invert())
    }

    "support solving a linear system" in {
      val mat = Mat[Float](2, 2, Scalar(0))
      mat.put(4f, 3f, 3f, 2f)

      val mat2 = Mat[Float](2, 2, Scalar(0))
      mat2.put(3f, 2f, 2f, 5f)

      val (result,solution) = mat.solve(mat2)
      result === true
      solution.values.toArray === Array(0.0, 11.0, 1.0, -14.0)
    }

    "support sorting" in {
      val mat = Mat[Float](3, 3, Scalar(0))
      mat.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f)

      mat.sort(Set(SortFlags.Ascending)).values.toList.sliding(3,3) ===
        List(
          List( 3.0, 3.0, 4.0),
          List( 1.0, 2.0, 9.0),
          List(-1.0, 3.0, 5.0)
        )

      mat.sort(Set(SortFlags.Descending)).values.toList.sliding(3,3) ===
        List(
          List(4.0, 3.0,  3.0),
          List(9.0, 2.0,  1.0),
          List(5.0, 3.0, -1.0)
        )

      mat.sort(Set(SortFlags.Ascending, SortFlags.Columns)).values.toList.sliding(3,3) ===
        List(
          List(2.0, -1.0, 3.0),
          List(4.0,  1.0, 3.0),
          List(5.0,  3.0, 9.0)
        )

      mat.sort(Set(SortFlags.Descending, SortFlags.Columns)).values.toList.sliding(3,3) ===
        List(
          List(5.0,  3.0, 9.0),
          List(4.0,  1.0, 3.0),
          List(2.0, -1.0, 3.0)
        )
    }

    "support sorting to indexes" in {
      val mat = Mat[Float](3, 3, Scalar(0))
      mat.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f)

      mat.sortIdx(Set(SortFlags.Ascending)).values.toList.sliding(3,3) ===
        List(
          List(1,2,0),
          List(1,0,2),
          List(1,2,0)
        )

      mat.sortIdx(Set(SortFlags.Descending)).values.toList.sliding(3,3) ===
        List(
          List(0,2,1),
          List(2,0,1),
          List(0,2,1)
        )

      mat.sortIdx(Set(SortFlags.Ascending, SortFlags.Columns)).values.toList.sliding(3,3) ===
        List(
          List(1,2,0),
          List(0,1,2),
          List(2,0,1)
      )

      mat.sortIdx(Set(SortFlags.Descending, SortFlags.Columns)).values.toList.sliding(3,3) ===
        List(
          List(2,0,1),
          List(0,1,2),
          List(1,2,0)
        )
    }

    "computes Eigenvalues and Vectors" in {
      val mat = Mat[Float](3, 3, Scalar(0))
      mat.put(4f, 3f, 3f, 3f, 2f, 1f, 3f, 1f, 5f)

      val (solved, eValues, eVectors) = mat.eigenValuesAndVectors()
      solved === true
      eValues.values.toArray === Array(13.040859, 2.0199919, -7.0608506)
      eVectors.values.toArray === Array(0.42439002, 0.6075664, 0.6713837, 0.9051798, -0.26559404, -0.33182704, -0.023291493, 0.7485471, -0.6626724)

      val (solved2, eValues2) = mat.eigenValues()
      solved2 === true
      eValues2.values.toArray === Array(13.040859, 2.0199919, -7.0608506)
    }

    "computes non-symmetric eigenvaulues" in {
      val mat = Mat[Float](3, 3, Scalar(0))
      mat.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f)

      val (eValues, eVectors) = mat.eigenNonSymmetric()

      eValues.values.toArray === Array(9.0, -0.5, -0.5)
      eVectors.values.toArray === Array(-0.6396021, -0.6396021, -0.42640144, -0.47414026, 0.95588785, 0.24877799, 0.37301725, 0.68652284, -0.61882013)
    }

    "complete a square matrix symmetrically" in {
      val halfMat = Mat[Float](4, 4, Scalar(0))
      halfMat.put((1 to 16).map(_.toFloat))

      halfMat.completeSymm().values.toArray === Array(1.0, 2.0, 3.0, 4.0, 2.0, 6.0, 7.0, 8.0, 3.0, 7.0, 11.0, 12.0, 4.0, 8.0, 12.0, 16.0)
      halfMat.completeSymm(lowerToUpper = true).values.toArray === Array(1.0, 2.0, 3.0, 4.0, 2.0, 6.0, 7.0, 8.0, 3.0, 7.0, 11.0, 12.0, 4.0, 8.0, 12.0, 16.0)
    }

    "set identity" in {
      val id = Mat[Float](3,3).setIdentity(Scalar(5))
      id.values.toArray === Array(5.0, 0.0, 0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 5.0)
    }

    "calculates determinant" in {
      val mat = Mat[Float](3, 3, Scalar(0))
      mat.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f)

      mat.determinant() === 144
    }

    "calculates trace" in {
      val mat = Mat[Float](3, 3, Scalar(0))
      mat.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f)
      mat.trace() === Scalar(8)
    }

    "solve cubic equation" in {
      val  coeffs: Mat[Double] = Mat(Scalar(6,-19,-52,-15))
      val (rootCount, roots) = coeffs.solveCubic()

      rootCount === 3
      roots.values.toArray === Array(-1.5d,5d,-0.3333333333333315d)
    }

    "solve complex polynomial" in {
      val coeffs: Mat[Double] = Mat[Double](6)
      coeffs.put(1, 2, 6, -19, -52, -15)
      val (error, roots) = coeffs.solvePoly()
      error === 2.42057414671307E-16
      roots.values.toArray === Array(0.40332451203570135, 0.0, -0.11081454516104569, -0.2705754524281875, -0.11081454516104568, 0.2705754524281875, -0.6434263612552581, 0.0, -3.0049357271250186, 0.0)
    }


    "calculate covariant matrix" in {
      val samples = Mat[Float](18)
      samples.put(1,3,2,5,8,7,12,2,4,8,6,9,4,3,3,2,7,7)

      val (covariants, means) = samples.calcCovariantMatrix(Set(CovariantFlags.Rows))
      covariants.shape === Seq(18,18)
      covariants.values.toArray === Array(17.361111111111107, 9.027777777777773, 13.19444444444444, 0.6944444444444419,
        -11.805555555555557, -7.63888888888889, -28.47222222222222, 13.19444444444444, 4.861111111111108,
        -11.805555555555557, -3.472222222222224, -15.972222222222223, 4.861111111111108, 9.027777777777773,
        9.027777777777773, 13.19444444444444, -7.63888888888889, -7.63888888888889, 9.027777777777773,
        4.694444444444442, 6.861111111111108, 0.3611111111111097, -6.138888888888888, -3.9722222222222223,
        -14.805555555555554, 6.861111111111108, 2.527777777777776, -6.138888888888888, -1.8055555555555562,
        -8.305555555555555, 2.527777777777776, 4.694444444444442, 4.694444444444442, 6.861111111111108,
        -3.9722222222222223, -3.9722222222222223, 13.19444444444444, 6.861111111111108, 10.027777777777773,
        0.5277777777777758, -8.972222222222223, -5.805555555555556, -21.638888888888886, 10.027777777777773,
        3.694444444444442, -8.972222222222223, -2.63888888888889, -12.13888888888889, 3.694444444444442,
        6.861111111111108, 6.861111111111108, 10.027777777777773, -5.805555555555556, -5.805555555555556,
        0.6944444444444419, 0.3611111111111097, 0.5277777777777758, 0.027777777777777582, -0.47222222222222066,
        -0.3055555555555546, -1.1388888888888848, 0.5277777777777758, 0.19444444444444364, -0.47222222222222066,
        -0.1388888888888885, -0.6388888888888867, 0.19444444444444364, 0.3611111111111097, 0.3611111111111097,
        0.5277777777777758, -0.3055555555555546, -0.3055555555555546, -11.805555555555557, -6.138888888888888,
        -8.972222222222223, -0.47222222222222066, 8.02777777777778, 5.194444444444447, 19.361111111111118,
        -8.972222222222223, -3.3055555555555545, 8.02777777777778, 2.3611111111111134, 10.861111111111114,
        -3.3055555555555545, -6.138888888888888, -6.138888888888888, -8.972222222222223, 5.194444444444447,
        5.194444444444447, -7.63888888888889, -3.9722222222222223, -5.805555555555556, -0.3055555555555546,
        5.194444444444447, 3.3611111111111134, 12.527777777777782, -5.805555555555556, -2.1388888888888884,
        5.194444444444447, 1.5277777777777795, 7.027777777777781, -2.1388888888888884, -3.9722222222222223,
        -3.9722222222222223, -5.805555555555556, 3.3611111111111134, 3.3611111111111134, -28.47222222222222,
        -14.805555555555554, -21.638888888888886, -1.1388888888888848, 19.361111111111118, 12.527777777777782,
        46.69444444444445, -21.638888888888886, -7.972222222222219, 19.361111111111118, 5.694444444444449,
        26.19444444444445, -7.972222222222219, -14.805555555555554, -14.805555555555554, -21.638888888888886,
        12.527777777777782, 12.527777777777782, 13.19444444444444, 6.861111111111108, 10.027777777777773,
        0.5277777777777758, -8.972222222222223, -5.805555555555556, -21.638888888888886, 10.027777777777773,
        3.694444444444442, -8.972222222222223, -2.63888888888889, -12.13888888888889, 3.694444444444442,
        6.861111111111108, 6.861111111111108, 10.027777777777773, -5.805555555555556, -5.805555555555556,
        4.861111111111108, 2.527777777777776, 3.694444444444442, 0.19444444444444364, -3.3055555555555545,
        -2.1388888888888884, -7.972222222222219, 3.694444444444442, 1.3611111111111098, -3.3055555555555545,
        -0.9722222222222224, -4.4722222222222205, 1.3611111111111098, 2.527777777777776, 2.527777777777776,
        3.694444444444442, -2.1388888888888884, -2.1388888888888884, -11.805555555555557, -6.138888888888888,
        -8.972222222222223, -0.47222222222222066, 8.02777777777778, 5.194444444444447, 19.361111111111118,
        -8.972222222222223, -3.3055555555555545, 8.02777777777778, 2.3611111111111134, 10.861111111111114,
        -3.3055555555555545, -6.138888888888888, -6.138888888888888, -8.972222222222223, 5.194444444444447,
        5.194444444444447, -3.472222222222224, -1.8055555555555562, -2.63888888888889, -0.1388888888888885,
        2.3611111111111134, 1.5277777777777795, 5.694444444444449, -2.63888888888889, -0.9722222222222224,
        2.3611111111111134, 0.6944444444444454, 3.1944444444444473, -0.9722222222222224, -1.8055555555555562,
        -1.8055555555555562, -2.63888888888889, 1.5277777777777795, 1.5277777777777795, -15.972222222222223,
        -8.305555555555555, -12.13888888888889, -0.6388888888888867, 10.861111111111114, 7.027777777777781,
        26.19444444444445, -12.13888888888889, -4.4722222222222205, 10.861111111111114, 3.1944444444444473,
        14.694444444444448, -4.4722222222222205, -8.305555555555555, -8.305555555555555, -12.13888888888889,
        7.027777777777781, 7.027777777777781, 4.861111111111108, 2.527777777777776, 3.694444444444442,
        0.19444444444444364, -3.3055555555555545, -2.1388888888888884, -7.972222222222219, 3.694444444444442,
        1.3611111111111098, -3.3055555555555545, -0.9722222222222224, -4.4722222222222205, 1.3611111111111098,
        2.527777777777776, 2.527777777777776, 3.694444444444442, -2.1388888888888884, -2.1388888888888884,
        9.027777777777773, 4.694444444444442, 6.861111111111108, 0.3611111111111097, -6.138888888888888,
        -3.9722222222222223, -14.805555555555554, 6.861111111111108, 2.527777777777776, -6.138888888888888,
        -1.8055555555555562, -8.305555555555555, 2.527777777777776, 4.694444444444442, 4.694444444444442,
        6.861111111111108, -3.9722222222222223, -3.9722222222222223, 9.027777777777773, 4.694444444444442,
        6.861111111111108, 0.3611111111111097, -6.138888888888888, -3.9722222222222223, -14.805555555555554,
        6.861111111111108, 2.527777777777776, -6.138888888888888, -1.8055555555555562, -8.305555555555555,
        2.527777777777776, 4.694444444444442, 4.694444444444442, 6.861111111111108, -3.9722222222222223,
        -3.9722222222222223, 13.19444444444444, 6.861111111111108, 10.027777777777773, 0.5277777777777758,
        -8.972222222222223, -5.805555555555556, -21.638888888888886, 10.027777777777773, 3.694444444444442,
        -8.972222222222223, -2.63888888888889, -12.13888888888889, 3.694444444444442, 6.861111111111108,
        6.861111111111108, 10.027777777777773, -5.805555555555556, -5.805555555555556, -7.63888888888889,
        -3.9722222222222223, -5.805555555555556, -0.3055555555555546, 5.194444444444447, 3.3611111111111134,
        12.527777777777782, -5.805555555555556, -2.1388888888888884, 5.194444444444447, 1.5277777777777795,
        7.027777777777781, -2.1388888888888884, -3.9722222222222223, -3.9722222222222223, -5.805555555555556,
        3.3611111111111134, 3.3611111111111134, -7.63888888888889, -3.9722222222222223, -5.805555555555556,
        -0.3055555555555546, 5.194444444444447, 3.3611111111111134, 12.527777777777782, -5.805555555555556,
        -2.1388888888888884, 5.194444444444447, 1.5277777777777795, 7.027777777777781, -2.1388888888888884,
        -3.9722222222222223, -3.9722222222222223, -5.805555555555556, 3.3611111111111134, 3.3611111111111134)

      means.values.toArray === Array(5.1666665)
    }

    "calculate a DFT" in {
      val mat = Mat[Float](3, 3, Scalar(0))
      mat.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f)
      mat.dft().values.toArray === Array(29.0, 2.0, 10.392304, 0.5, 3.5, 0.86602545, -4.330127, -2.5, -11.258329)
    }

    "calculate an inverse DFT" in {
      val mat = Mat[Float](3,3)
      mat.put(29.0f, 2.0f, 10.392304f, 0.5f, 3.5f, 0.86602545f, -4.330127f, -2.5f, -11.258329f)
      mat.idft().values.toArray === Array(36.0, 27.0, 27.0, 18.0, 9.0, 81.0, 45.0, -9.0, 27.0)
    }

    "calculate DCT" in {
      val mat = Mat[Float](4, 4, Scalar(0))
      mat.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f, 8f, -3f, 0f, 1f, -2f, 3f, 0)
      mat.dct().values.toArray === Array(9.0, 3.9661162, -4.0, -2.1840153, 4.0782013, 0.6213202, -1.6235883, -0.7928932,
        -2.0, -3.3592622, 4.0, 6.2622166, -0.6068543, 2.2071068, 3.9196887, -3.62132)
    }

    "calculate n inverse DCT" in {
      val mat = Mat[Float](4, 4, Scalar(0))
      mat.put(9.0f, 3.9661162f, -4.0f, -2.1840153f, 4.0782013f, 0.6213202f, -1.6235883f, -0.7928932f, -2.0f,
        -3.3592622f, 4.0f, 6.2622166f, -0.6068543f, 2.2071068f, 3.9196887f, -3.62132f)
      mat.idct().values.toArray === Array(4.0f, 3.0f, 3.0f, 2.0f, 1.0f, 9.0f, 5.0f, -1.0f, 3.0f, 8.0, -3.0f, -3.0-7,
        1.0f, -2.0f, 3.0f, 5.9604645E-8)
    }

    "rondomizes a given Mat" in {
      val seed = Mat[Float](3,3)
      seed.randomUniformDistribution(Scalar(50),Scalar(150)).values.toArray === Array(78.85909, 98.32198, 99.12618,
        57.01957, 132.76843, 89.832306, 141.47408, 90.63376, 95.4782)

      val normalDistribution = seed.randomNormalDistribution(Scalar(50),Scalar(5))
      normalDistribution.values.toArray === Array(49.349464, 52.664497, 56.31855,
        51.50454, 54.852783, 47.042717, 51.31661, 48.33009, 50.3002)

      normalDistribution.randomShuffle().values.toArray === Array(56.31855, 51.31661, 50.3002, 52.664497, 48.33009,
        51.50454, 54.852783, 47.042717, 49.349464)
    }

    "compute SVDecomposition" in {
      val r = Mat[Float](3,3)
      r.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f)

      val (u,vt,w) = r.svDecomp()

      u.shape === Seq(1,3)
      u.values.toArray === Array(11.27441, 4.420231, 2.8895073)
      vt.shape === Seq(3,3)
      vt.values.toArray === Array(0.44155857, -0.47221413, -0.7629153, 0.7863958, 0.61306906, 0.075683326, 0.43198103, -0.633372, 0.6420531)
      w.shape === Seq(3,3)
      w.values.toArray === Array(0.48773566, 0.14892936, 0.86019415, -0.86637527, -0.03850505, 0.49790692, 0.10727479, -0.9880979, 0.11024842)
    }

  }

  "MatMath" should {
    import syntax._

    "support mixing channels" in {
      val row1 = Mat[Float](1, 3)
      row1.put(0f, 1f, 10f)
      val row2 = Mat[Float](1, 3)
      row2.put(2f, -1f, 5f)
      val rows = Mat[Float](Types.Cv32F, Some(3), 1, 3)
      rows.put(-5f, 19f, -1f, 2f, 5f, -10f, 10f, 2f, -1f)

      val mixed1 = Mat[Float](Types.Cv32F, Some(2), 1, 3)
      val mixed2 = Mat[Float](Types.Cv32F, Some(2), 1, 3)

      MatMath.mixChannels(Array(row1, row2, rows), Array(mixed1, mixed2), Array((0, 2), (1, 0), (2, 1), (4, 3)))
      mixed1.values.toArray === Array(2.0, -5.0, -1.0, 2.0, 5.0, 10.0)
      mixed2.values.toArray === Array(0.0, -1.0, 1.0, -10.0, 10.0, -1.0)
    }

    "multiply Spectrums" in {
      val mat = Mat[Float](3, 3, Scalar(0))
      mat.put(4f, 3f, 3f, 2f, 1f, 9f, 5f, -1f, 3f)

      val i = mat.dft()
      val ii = i

      val mags = MatMath.multiplySpectrums(i,ii,Set(DiscreteFourierTransformFlags.Inverse, DiscreteFourierTransformFlags.RealOutput))
      mags.shape === Seq(3,3)
      mags.values.toArray === Array(841.0, -103.99999, 41.569218, -18.499998, 11.5, 6.062178, -4.330127, -120.49998, 56.29165)
    }

    "calculate Mahalanobis distances" in {
      val cluster = Mat[Float](5,3)
      cluster.put(1,100,10,2,300,15,4,200,20,2,600,10,5,100,30)

      val (covar,means) = cluster.calcCovariantMatrix(Set(CovariantFlags.Rows,CovariantFlags.Normal))
      val icovar = covar.inv(DecompositionMethods.SVD)

      val x = Mat[Float](1,3)
      x.put(4f,500f,40f)

      MatMath.mahalanobis(means,x,icovar) === 4.378927263670372
    }
  }

}
