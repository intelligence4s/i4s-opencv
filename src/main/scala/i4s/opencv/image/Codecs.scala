package i4s.opencv.image

import i4s.opencv.image.constants.ImageReadFlags
import i4s.opencv.image.constants.ImageReadFlags.ImageReadFlag
import i4s.opencv.image.constants.ImageWriteFlags.ImageWriteFlag
import org.bytedeco.opencv.opencv_core.{Mat, MatVector}
import org.bytedeco.opencv.global.opencv_imgcodecs._

import java.io.File

object Codecs {
  import Image._

  def readImageFromResource(resourcePath: String): Option[Image] = {
    val imageFile = new File(this.getClass.getClassLoader.getResource(resourcePath).getFile)
    readImage(imageFile)
  }

  def readImage(filePath: String): Option[Image] = {
    val imageFile = new File(filePath)
    readImage(imageFile)
  }

  def readImage(file: File): Option[Image] = readImage(file,ImageReadFlags.AnyColor)
  def readImage(file: File, readFlag: ImageReadFlag): Option[Image] = readImage(file,Set(readFlag))
  def readImage(file: File, readFlags: Set[ImageReadFlag]): Option[Image] = {
    val combined = readFlags.foldLeft(0)(_ | _.id)
    val mat = imread(file.getAbsolutePath,combined)
    if (mat.empty()) None else Some(mat)
  }

  def readImages(file: File): List[Image] = readImages(file,ImageReadFlags.AnyColor)
  def readImages(file: File, readFlag: ImageReadFlag): List[Image] = readImages(file,Set(readFlag))
  def readImages(file: File, readFlags: Set[ImageReadFlag]): List[Image] = {
    val combined = readFlags.foldLeft(0)(_ | _.id)
    val matVector = new MatVector()
    val result = imreadmulti(file.getAbsolutePath,matVector,combined)
    if (result) matVectorToList(matVector)
    else Nil
  }

  def readImages(file: File, start: Int, count: Int): List[Image] = readImages(file,start,count,ImageReadFlags.AnyColor)
  def readImages(file: File, start: Int, count: Int, readFlag: ImageReadFlag): List[Image] = readImages(file,start,count,Set(readFlag))
  def readImages(file: File, start: Int, count: Int, readFlags: Set[ImageReadFlag]): List[Image] = {
    val combined = readFlags.foldLeft(0)(_ | _.id)
    val matVector = new MatVector()
    val result = imreadmulti(file.getAbsolutePath,matVector,start,count,combined)
    if (result) matVectorToList(matVector)
    else Nil
  }


  def imageCount(file: File): Option[Long] = imageCount(file, ImageReadFlags.AnyColor)
  def imageCount(file: File, readFlag: ImageReadFlag): Option[Long] = imageCount(file,Set(readFlag))
  def imageCount(file: File, readFlags: Set[ImageReadFlag]): Option[Long] = {
    val combined = readFlags.foldLeft(0)(_ | _.id)
    val result = imcount(file.getAbsolutePath, combined)
    if (result < 0) None else Some(result)
  }

  implicit class ImageCodecs(image: Image) {
    def write(file: File): Unit = imwrite(file.getAbsolutePath,image)
  }

  private def matVectorToList(vector: MatVector): List[Image] = vector.get().toList.map(m => new Image(m))
}

