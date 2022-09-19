package i4s.opencv.image

import i4s.opencv.image.constants.ColorConversionCodes.ColorConversionCode
import i4s.opencv.image.constants.ColorMaps.ColorMap
import org.bytedeco.opencv.global.opencv_imgproc
import org.bytedeco.opencv.opencv_core.Mat

object Colors extends Colors

trait Colors {

  implicit class ImageColor(image: Image) {

    def cvtColor(code: ColorConversionCode, dstCn: Int): Image = {
      val dst = new Mat()
      opencv_imgproc.cvtColor(image,dst,code.id,dstCn)
      dst
    }

    def cvtColor(code: ColorConversionCode): Image =
      cvtColor(code, dstCn = 0)

    def cvtColorTwoPlane(src2: Image, code: ColorConversionCode): Image = {
      val dst = new Mat()
      opencv_imgproc.cvtColorTwoPlane(image,src2,dst,code.id)
      dst
    }

    def demosaicing(code: ColorConversionCode, dstCn: Int): Image = {
      val dst = new Mat()
      opencv_imgproc.demosaicing(image,dst,code.id,dstCn)
      dst
    }

    def demosaicing(code: ColorConversionCode): Image =
      demosaicing(code,dstCn = 0)

    def applyColorMap(colorMap: ColorMap): Image = {
      val dst = new Mat()
      opencv_imgproc.applyColorMap(image,dst,colorMap.id)
      dst
    }

    def applyColorMap(userColor: Mat): Image = {
      val dst = new Mat()
      opencv_imgproc.applyColorMap(image,dst,userColor)
      dst
    }
  }
}
