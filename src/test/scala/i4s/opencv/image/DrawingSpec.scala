package i4s.opencv.image

import com.google.common.hash.{HashCode, Hashing}
import i4s.opencv.core.model.{Point, Point2f, Rect, RotatedRect, Scalar, Size2f}
import i4s.opencv.image.constants.{HersheyFonts, LineTypes, MarkerTypes}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.concurrent.duration._
import java.io.File
import scala.concurrent.Await

class DrawingSpec extends AnyWordSpec with Matchers {
  val redCircleImage: Image = {
    val file = new File(getClass.getClassLoader.getResource("red-circle.png").getFile)
    Codecs.readImage(file).get
  }

  "Drawing" should {
    import Drawing._
    import i4s.opencv.image.ui.ViewMaster._

    "draw a rectangle" in {
      val expectedHash = HashCode.fromString("481edfc4ecea1d71a842818435266bb7a43777eb6d2bdc0e4ec5e0c67c337756")
      val image = Image(300,300,3,Scalar.White)

      val red: Scalar = Scalar.Red
      val rectangle = Rect(Point(30,30),Point(270,270))
      image.rectangle(rectangle,red,LineTypes.Line4,-1,0)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe expectedHash

//      image.write(new File("red-square.png"))
//      val closed = image.show("Red box")
//      Await.ready(closed,1.minute)
    }

    "draw a circle" in {
      val expected = HashCode.fromString("8b62391244665e3d57fa2d2425c54aa7f5418b1fbad6c4566146a62dd85833ae")
      val image = Image(150,150,3,Scalar.Black)
      image.circle(Point(75,75),30,Scalar.Red)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe expected

//      image.write(new File("red-circle.png"))
//
//      val imageWillClose = image.show("actual")
//      val expectedWillClose = redCircleImage.show("expected")
//      val bothClosed = Future.sequence(List(imageWillClose,expectedWillClose))
//      Await.ready(bothClosed,1.minute)
    }

    "draw an ellipse" in {
      val image = Image(150,150,3,Scalar.Black)

      val box = RotatedRect(Point2f(75,75),Size2f(40,60),45)
      image.ellipse(box,Scalar.Green,-1,LineTypes.Line4)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("c08dc5e582c0709b94f0f783ccb4c80571ba4c9f528fe3b0e42680a7b992a07a")

//      val closed = image.show("Green ellipse")
//      Await.ready(closed,1.minute)
    }

    "draw a marker" in {
      val image = Image(150, 150, 3, Scalar.Black)

      image.drawMarker(Point(30,10),Scalar.Red)
      image.drawMarker(Point(100,50),Scalar.White,MarkerTypes.TriangleUp,10,1,LineTypes.LineAntiAliased)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("a1ba8de844eb61ecca48e95216734b5fcb8fbbaac3a31ee56d9ee9b7b42ad402")

//      val closed = image.show("Markers")
//      Await.ready(closed, 1.minute)
    }

    "draw text" in {
      val image = Image(150, 300, 3, Scalar.Black)

      image.putText("Luke, I AM your father",Point(10,50),HersheyFonts.Plain,1.2d,false,Scalar.White)
      image.putText("NO!!!",Point(10,100),HersheyFonts.Complex,1d,true,Scalar.Red)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("007233f5e774056ad7f2fb95684aacfcf8945a2daad0b37de56f35366ab11aed")

//      val closed = image.show("Iconic dialog")
//      Await.ready(closed, 1.minute)
    }

    "fill a convex poly" in {
      val image = Image(350,200,3,Scalar.Black)

      val polyPoints = List(
        Point(20,20), Point(330,20),
        Point(20,70), Point(330,70),
        Point(20,120), Point(330,120),
        Point(20,170), Point(330,170)
      )

      image.fillConvexPoly(polyPoints,Scalar.Yellow,LineTypes.LineAntiAliased,0)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("5c61c6d6c1ee8faa33a3da089d94558025fbf25504ebde401906d357785338b2")

//      val closed = image.show("Convex poly")
//      Await.ready(closed, 1.minute)
    }

    "fill a poly" in {
      val image = Image(220, 220, 3, Scalar.Black)

      val polyPoints = List(Point(20, 170), Point(50, 120), Point(80, 70), Point(130, 120), Point(200, 170))
      image.fillPoly(polyPoints, Scalar.Cyan, LineTypes.LineAntiAliased, 0, Point(0,0))

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("fdfeaa2119b578cf69bb10dba635c021ba40fd26e94e3be667132db771680f49")

//      val closed = image.show("Poly")
//      Await.ready(closed, 1.minute)
    }

    "fill a poly list" in {
      val image = Image(220, 220, 3, Scalar.Black)

      val poly1Points = List(Point(20, 170), Point(50, 120), Point(80, 70), Point(130, 120), Point(200, 170))
      val poly2Points = List(Point(170,20), Point(120,50), Point(70,80), Point(120, 130), Point(170, 200))
      image.fillPolyList(List(poly1Points,poly2Points), Scalar.Magenta, LineTypes.LineAntiAliased, 0, Point(0, 0))

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("43875d150c932b0bb1174a233fe74162c09d9bca18f1b367b6e8aa21cc28c046")

//      val closed = image.show("Red box")
//      Await.ready(closed, 1.minute)
    }

    "draw a poly line" in {
      val image = Image(220, 220, 3, Scalar.Black)

      val poly1Points = List(Point(20, 170), Point(50, 120), Point(80, 70), Point(130, 120), Point(200, 170))
      val poly2Points = List(Point(170, 20), Point(120, 50), Point(70, 80), Point(120, 130), Point(170, 200))

      image.polyline(poly1Points,true,Scalar.White)
      image.polyline(poly2Points,false,Scalar.Red)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("30ca3ba28674b6015dfe02bc88ac14ec5b6524a189d241f7a1b942f8d713d91d")

//      val closed = image.show("Lines")
//      Await.ready(closed, 1.minute)
    }

    "draw poly lines" in {
      val image = Image(220, 220, 3, Scalar.Black)

      val poly1Points = List(Point(20, 170), Point(50, 120), Point(80, 70), Point(130, 120), Point(200, 170))
      val poly2Points = List(Point(170, 20), Point(120, 50), Point(70, 80), Point(120, 130), Point(170, 200))
      image.polylines(List(poly1Points,poly2Points), true, Scalar.Blue)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("8a8713dd66ea1bdc1f064371f4c7b58a57b2c30ff6b5b7bd27dd7debd9902f2e")

//      val closed = image.show("Lines")
//      Await.ready(closed, 1.minute)
    }

    "draw a contour" in {
      val image = Image(220, 220, 3, Scalar.Black)

      val poly1Points = List(Point(20, 170), Point(50, 120), Point(80, 70), Point(130, 120), Point(200, 170))
      image.drawContour(poly1Points,Scalar.Gray)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("2e218f57a8594510f08bf98fa8ce703ddb69011278d6d25fd842a94099ac29d0")

//      val closed = image.show("Contour")
//      Await.ready(closed, 1.minute)
    }

    "draw contours" in {
      val image = Image(350, 220, 3, Scalar.Black)

      val poly1Points = List(Point(20, 170), Point(50, 120), Point(80, 70), Point(130, 120), Point(200, 170))
      val poly2Points = List(Point(170, 20), Point(120, 50), Point(70, 80), Point(120, 130), Point(170, 200))

      val poly3Points = List(
        Point(20, 20), Point(330, 20),
        Point(20, 70), Point(330, 70),
        Point(20, 120), Point(330, 120),
        Point(20, 170), Point(330, 170)
      )

      image.drawContours(List(poly1Points,poly2Points,poly3Points),Scalar.White)

      val hash = image.rawValues.foldLeft(Hashing.sha256.newHasher)(_.putInt(_)).hash
      hash shouldBe HashCode.fromString("f3cdbc01330a4ddd188c4024a93d54d75d9245160133209fbab3899f2090d90a")

//      val closed = image.show("Contours")
//      Await.ready(closed, 1.minute)
    }
  }
}
