package i4s.opencv.image.ui

import i4s.opencv.image.Image
import i4s.opencv.image.Image.umat2Image
import org.bytedeco.javacv.CanvasFrame

import java.awt.event.WindowAdapter
import javax.swing.WindowConstants
import scala.concurrent.{Future, Promise}
import scala.util.Try

object ViewMaster {

  implicit class ImageView(image: Image) {
    def show(title: String): Future[Boolean] = {
      val canvas = new CanvasFrame(title,1)
      canvas.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)

      val willClose = Promise[Boolean]()

      canvas.addWindowListener(new WindowAdapter() {
        override def windowClosing(windowEvent: _root_.java.awt.event.WindowEvent): Unit = {
          println(s"Closing window!")
          willClose.complete(Try(true))
          super.windowClosed(windowEvent)
        }
      })

      canvas.showImage(image.toBufferedImage)
      willClose.future
    }
  }


}
