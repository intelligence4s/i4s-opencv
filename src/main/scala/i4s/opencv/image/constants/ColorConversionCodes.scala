package i4s.opencv.image.constants

object ColorConversionCodes extends Enumeration {
  type ColorConversionCode = Value
  /** add alpha channel to RGB or BGR image */
  val BGR2BGRA: Value = Value(0)
  val RGB2RGBA: Value = BGR2BGRA

  /** remove alpha channel from RGB or BGR image */
  val BGRA2BGR: Value = Value(1)
  val RGBA2RGB: Value = BGRA2BGR

  /** convert between RGB and BGR color spaces (with or without alpha channel) */
  val BGR2RGBA: Value = Value(2)
  val RGB2BGRA: Value = BGR2RGBA
  

  val RGBA2BGR: Value = Value(3)
  val BGRA2RGB: Value = RGBA2BGR

  val BGR2RGB: Value = Value(4)
  val RGB2BGR: Value = BGR2RGB

  val BGRA2RGBA: Value = Value(5)
  val RGBA2BGRA: Value = BGRA2RGBA

  /** convert between RGB/BGR and grayscale \ref color_convert_rgb_gray "color conversions" */
  val BGR2Gray: Value = Value(6)
  val RGB2Gray: Value = Value(7)
  val Gray2BGR: Value = Value(8)
  val Gray2RGB: Value = Gray2BGR
  val Gray2BGRA: Value = Value(9)
  val Gray2RGBA: Value = Gray2BGRA
  val BGRA2Gray: Value = Value(10)
  val RGBA2Gray: Value = Value(11)
  

  /** convert between RGB/BGR and BGR565 (16-bit images) */
  val BGR2BGR565: Value = Value(12)
  val RGB2BGR565: Value = Value(13)
  val BGR5652BGR: Value = Value(14)
  val BGR5652RGB: Value = Value(15)
  val BGRA2BGR565: Value = Value(16)
  val RGBA2BGR565: Value = Value(17)
  val BGR5652BGRA: Value = Value(18)
  val BGR5652RGBA: Value = Value(19)
  

  /** convert between grayscale to BGR565 (16-bit images) */
  val Gray2BGR565: Value = Value(20)
  val BGR5652Gray: Value = Value(21)

  /** convert between RGB/BGR and BGR555 (16-bit images) */
  val BGR2BGR555: Value = Value(22)
  val RGB2BGR555: Value = Value(23)
  val BGR5552BGR: Value = Value(24)
  val BGR5552RGB: Value = Value(25)
  val BGRA2BGR555: Value = Value(26)
  val RGBA2BGR555: Value = Value(27)
  val BGR5552BGRA: Value = Value(28)
  val BGR5552RGBA: Value = Value(29)
  

  /** convert between grayscale and BGR555 (16-bit images) */
  val Gray2BGR555: Value = Value(30)
  val BGR5552Gray: Value = Value(31)
  

  /** convert RGB/BGR to CIE XYZ \ref color_convert_rgb_xyz "color conversions" */
  val BGR2XYZ: Value = Value(32)
  val RGB2XYZ: Value = Value(33)
  val XYZ2BGR: Value = Value(34)
  val XYZ2RGB: Value = Value(35)

  /** convert RGB/BGR to luma-chroma (aka YCC) \ref color_convert_rgb_ycrcb "color conversions" */
  val BGR2YCrCb: Value = Value(36)
  val RGB2YCrCb: Value = Value(37)
  val YCrCb2BGR: Value = Value(38)
  val YCrCb2RGB: Value = Value(39)

  /** convert RGB/BGR to HSV (hue saturation value) with H range 0..180 if 8 bit image \ref color_convert_rgb_hsv "color conversions" */
  val BGR2HSV: Value = Value(40)
  val RGB2HSV: Value = Value(41)

  /** convert RGB/BGR to CIE Lab \ref color_convert_rgb_lab "color conversions" */
  val BGR2Lab: Value = Value(44)
  val RGB2Lab: Value = Value(45)

  /** convert RGB/BGR to CIE Luv \ref color_convert_rgb_luv "color conversions" */
  val BGR2Luv: Value = Value(50)
  val RGB2Luv: Value = Value(51)
  

  /** convert RGB/BGR to HLS (hue lightness saturation) with H range 0..180 if 8 bit image \ref color_convert_rgb_hls "color conversions" */
  val BGR2HLS: Value = Value(52)
  val RGB2HLS: Value = Value(53)

  /** backward conversions HSV to RGB/BGR with H range 0..180 if 8 bit image */
  val HSV2BGR: Value = Value(54)
  val HSV2RGB: Value = Value(55)

  val Lab2BGR: Value = Value(56)
  val Lab2RGB: Value = Value(57)
  val Luv2BGR: Value = Value(58)
  val Luv2RGB: Value = Value(59)

  /** backward conversions HLS to RGB/BGR with H range 0..180 if 8 bit image */
  val HLS2BGR: Value = Value(60)
  val HLS2RGB: Value = Value(61)

  /** convert RGB/BGR to HSV (hue saturation value) with H range 0..255 if 8 bit image \ref color_convert_rgb_hsv "color conversions" */
  val BGR2HSVFull: Value = Value(66)
  val RGB2HSVFull: Value = Value(67)

  /** convert RGB/BGR to HLS (hue lightness saturation) with H range 0..255 if 8 bit image \ref color_convert_rgb_hls "color conversions" */
  val BGR2HLSFull: Value = Value(68)
  val RGB2HLSFull: Value = Value(69)

  /** backward conversions HSV to RGB/BGR with H range 0..255 if 8 bit image */
  val HSV2BGRFull: Value = Value(70)
  val HSV2RGBFull: Value = Value(71)

  /** backward conversions HLS to RGB/BGR with H range 0..255 if 8 bit image */
  val HLS2BGRFull: Value = Value(72)
  val HLS2RGBFull: Value = Value(73)

  val LBGR2Lab: Value = Value(74)
  val LRGB2Lab: Value = Value(75)
  val LBGR2Luv: Value = Value(76)
  val LRGB2Luv: Value = Value(77)

  val Lab2LBGR: Value = Value(78)
  val Lab2LRGB: Value = Value(79)
  val Luv2LBGR: Value = Value(80)
  val Luv2LRGB: Value = Value(81)

  /** convert between RGB/BGR and YUV */
  val BGR2YUV: Value = Value(82)
  val RGB2YUV: Value = Value(83)
  val YUV2BGR: Value = Value(84)
  val YUV2RGB: Value = Value(85)

  /** YUV 4:2:0 family to RGB */
  val YUV2RGB_NV12: Value = Value(90)
  val YUV2BGR_NV12: Value = Value(91)
  val YUV2RGB_NV21: Value = Value(92)
  val YUV2BGR_NV21: Value = Value(93)
  
  val YUV420sp2RGB: Value = YUV2RGB_NV21
  val YUV420sp2BGR: Value = YUV2BGR_NV21
  val YUV2RGBA_NV12: Value = Value(94)
  val YUV2BGRA_NV12: Value = Value(95)
  val YUV2RGBA_NV21: Value = Value(96)
  val YUV2BGRA_NV21: Value = Value(97)
  val YUV420sp2RGBA: Value = YUV2RGBA_NV21
  val YUV420sp2BGRA: Value = YUV2BGRA_NV21

  val YUV2RGB_YV12: Value = Value(98)
  val YUV2BGR_YV12: Value = Value(99)
  val YUV2RGB_IYUV: Value = Value(100)
  val YUV2BGR_IYUV: Value = Value(101)
  val YUV2RGB_I420: Value = YUV2RGB_IYUV
  val YUV2BGR_I420: Value = YUV2BGR_IYUV
  val YUV420p2RGB: Value = YUV2RGB_YV12
  val YUV420p2BGR: Value = YUV2BGR_YV12

  val YUV2RGBA_YV12: Value = Value(102)
  val YUV2BGRA_YV12: Value = Value(103)
  val YUV2RGBA_IYUV: Value = Value(104)
  val YUV2BGRA_IYUV: Value = Value(105)
  val YUV2RGBA_I420: Value = YUV2RGBA_IYUV
  val YUV2BGRA_I420: Value = YUV2BGRA_IYUV
  val YUV420p2RGBA: Value = YUV2RGBA_YV12
  val YUV420p2BGRA: Value = YUV2BGRA_YV12

  val YUV2Gray_420: Value = Value(106)
  val YUV2Gray_NV21: Value = YUV2Gray_420
  val YUV2Gray_NV12: Value = YUV2Gray_420
  val YUV2Gray_YV12: Value = YUV2Gray_420
  val YUV2Gray_IYUV: Value = YUV2Gray_420
  val YUV2Gray_I420: Value = YUV2Gray_420
  val YUV420sp2Gray: Value = YUV2Gray_420
  val YUV420p2Gray: Value = YUV2Gray_420

  /** YUV 4:2:2 family to RGB */
  val YUV2RGB_UYVY: Value = Value(107)
  val YUV2BGR_UYVY: Value = Value(108)
  val YUV2RGB_VYUY: Value = Value(109)
  val YUV2BGR_VYUY: Value = Value(110)
  val YUV2RGB_Y422: Value = YUV2RGB_UYVY
  
  val YUV2BGR_Y422: Value = YUV2BGR_UYVY
  val YUV2RGB_UYNV: Value = YUV2RGB_UYVY
  val YUV2BGR_UYNV: Value = YUV2BGR_UYVY

  val YUV2RGBA_UYVY: Value = Value(111)
  val YUV2BGRA_UYVY: Value = Value(112)
  val YUV2RGBA_VYUY: Value = Value(113)
  val YUV2BGRA_VYUY: Value = Value(114)
  val YUV2RGBA_Y422: Value = YUV2RGBA_UYVY
  val YUV2BGRA_Y422: Value = YUV2BGRA_UYVY
  val YUV2RGBA_UYNV: Value = YUV2RGBA_UYVY
  val YUV2BGRA_UYNV: Value = YUV2BGRA_UYVY

  val YUV2RGB_YUY2: Value = Value(115)
  val YUV2BGR_YUY2: Value = Value(116)
  val YUV2RGB_YVYU: Value = Value(117)
  val YUV2BGR_YVYU: Value = Value(118)
  val YUV2RGB_YUYV: Value = YUV2RGB_YUY2
  val YUV2BGR_YUYV: Value = YUV2BGR_YUY2
  val YUV2RGB_YUNV: Value = YUV2RGB_YUY2
  val YUV2BGR_YUNV: Value = YUV2BGR_YUY2

  val YUV2RGBA_YUY2: Value = Value(119)
  val YUV2BGRA_YUY2: Value = Value(120)
  val YUV2RGBA_YVYU: Value = Value(121)
  val YUV2BGRA_YVYU: Value = Value(122)
  val YUV2RGBA_YUYV: Value = YUV2RGBA_YUY2
  val YUV2BGRA_YUYV: Value = YUV2BGRA_YUY2
  val YUV2RGBA_YUNV: Value = YUV2RGBA_YUY2
  val YUV2BGRA_YUNV: Value = YUV2BGRA_YUY2
  val YUV2Gray_UYVY: Value = Value(123)
  val YUV2Gray_YUY2: Value = Value(124)
  
  //CV_YUV2Gray_VYUY    = CV_YUV2Gray_UYVY
  val YUV2Gray_Y422: Value = YUV2Gray_UYVY
  val YUV2Gray_UYNV: Value = YUV2Gray_UYVY
  val YUV2Gray_YVYU: Value = YUV2Gray_YUY2
  val YUV2Gray_YUYV: Value = YUV2Gray_YUY2
  val YUV2Gray_YUNV: Value = YUV2Gray_YUY2

  /** alpha premultiplication */
  val RGBA2mRGBA: Value = Value(125)
  val mRGBA2RGBA: Value = Value(126)

  /** RGB to YUV 4:2:0 family */
  val RGB2YUV_I420: Value = Value(127)
  val BGR2YUV_I420: Value = Value(128)
  val RGB2YUV_IYUV: Value = RGB2YUV_I420
  val BGR2YUV_IYUV: Value = BGR2YUV_I420

  val RGBA2YUV_I420: Value = Value(129)
  val BGRA2YUV_I420: Value = Value(130)
  val RGBA2YUV_IYUV: Value = RGBA2YUV_I420
  val BGRA2YUV_IYUV: Value = BGRA2YUV_I420
  val RGB2YUV_YV12: Value = Value(131)
  val BGR2YUV_YV12: Value = Value(132)
  val RGBA2YUV_YV12: Value = Value(133)
  val BGRA2YUV_YV12: Value = Value(134)

  /** Demosaicing see \ref color_convert_bayer "color conversions" for additional information */
  /** equivalent to RGGB Bayer pattern */
  val BayerBG2BGR: Value = Value(46)

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2BGR: Value = Value(47)

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2BGR: Value = Value(48)

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2BGR: Value = Value(49)

  val BayerRGGB2BGR: Value = BayerBG2BGR
  val BayerGRBG2BGR: Value = BayerGB2BGR
  val BayerBGGR2BGR: Value = BayerRG2BGR
  val BayerGBRG2BGR: Value = BayerGR2BGR

  val BayerRGGB2RGB: Value = BayerBGGR2BGR
  val BayerGRBG2RGB: Value = BayerGBRG2BGR
  val BayerBGGR2RGB: Value = BayerRGGB2BGR
  val BayerGBRG2RGB: Value = BayerGRBG2BGR

  /** equivalent to RGGB Bayer pattern */
  val BayerBG2RGB: Value = BayerRG2BGR

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2RGB: Value = BayerGR2BGR

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2RGB: Value = BayerBG2BGR

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2RGB: Value = BayerGB2BGR

  /** equivalent to RGGB Bayer pattern */
  val BayerBG2Gray: Value = Value(86)

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2Gray: Value = Value(87)

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2Gray: Value = Value(88)

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2Gray: Value = Value(89)

  val BayerRGGB2Gray: Value = BayerBG2Gray
  val BayerGRBG2Gray: Value = BayerGB2Gray
  val BayerBGGR2Gray: Value = BayerRG2Gray
  val BayerGBRG2Gray: Value = BayerGR2Gray

  /** Demosaicing using Variable Number of Gradients */
  /** equivalent to RGGB Bayer pattern */
  val BayerBG2BGR_VNG: Value = Value(62)

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2BGR_VNG: Value = Value(63)

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2BGR_VNG: Value = Value(64)

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2BGR_VNG: Value = Value(65)

  val BayerRGGB2BGR_VNG: Value = BayerBG2BGR_VNG
  val BayerGRBG2BGR_VNG: Value = BayerGB2BGR_VNG
  val BayerBGGR2BGR_VNG: Value = BayerRG2BGR_VNG
  val BayerGBRG2BGR_VNG: Value = BayerGR2BGR_VNG

  val BayerRGGB2RGB_VNG: Value = BayerBGGR2BGR_VNG
  val BayerGRBG2RGB_VNG: Value = BayerGBRG2BGR_VNG
  val BayerBGGR2RGB_VNG: Value = BayerRGGB2BGR_VNG
  val BayerGBRG2RGB_VNG: Value = BayerGRBG2BGR_VNG

  /** equivalent to RGGB Bayer pattern */
  val BayerBG2RGB_VNG: Value = BayerRG2BGR_VNG

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2RGB_VNG: Value = BayerGR2BGR_VNG

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2RGB_VNG: Value = BayerBG2BGR_VNG

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2RGB_VNG: Value = BayerGB2BGR_VNG

  /** Edge-Aware Demosaicing */
  /** equivalent to RGGB Bayer pattern */
  val BayerBG2BGR_EA: Value = Value(135)

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2BGR_EA: Value = Value(136)

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2BGR_EA: Value = Value(137)

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2BGR_EA: Value = Value(138)

  val BayerRGGB2BGR_EA: Value = BayerBG2BGR_EA
  val BayerGRBG2BGR_EA: Value = BayerGB2BGR_EA
  val BayerBGGR2BGR_EA: Value = BayerRG2BGR_EA
  val BayerGBRG2BGR_EA: Value = BayerGR2BGR_EA

  val BayerRGGB2RGB_EA: Value = BayerBGGR2BGR_EA
  val BayerGRBG2RGB_EA: Value = BayerGBRG2BGR_EA
  val BayerBGGR2RGB_EA: Value = BayerRGGB2BGR_EA
  val BayerGBRG2RGB_EA: Value = BayerGRBG2BGR_EA

  /** equivalent to RGGB Bayer pattern */
  val BayerBG2RGB_EA: Value = BayerRG2BGR_EA

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2RGB_EA: Value = BayerGR2BGR_EA

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2RGB_EA: Value = BayerBG2BGR_EA

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2RGB_EA: Value = BayerGB2BGR_EA

  /** Demosaicing with alpha channel */
  /** equivalent to RGGB Bayer pattern */
  val BayerBG2BGRA: Value = Value(139)

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2BGRA: Value = Value(140)

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2BGRA: Value = Value(141)

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2BGRA: Value = Value(142)

  val BayerRGGB2BGRA: Value = BayerBG2BGRA
  val BayerGRBG2BGRA: Value = BayerGB2BGRA
  val BayerBGGR2BGRA: Value = BayerRG2BGRA
  val BayerGBRG2BGRA: Value = BayerGR2BGRA

  val BayerRGGB2RGBA: Value = BayerBGGR2BGRA
  val BayerGRBG2RGBA: Value = BayerGBRG2BGRA
  val BayerBGGR2RGBA: Value = BayerRGGB2BGRA
  val BayerGBRG2RGBA: Value = BayerGRBG2BGRA

  /** equivalent to RGGB Bayer pattern */
  val BayerBG2RGBA: Value = BayerRG2BGRA

  /** equivalent to GRBG Bayer pattern */
  val BayerGB2RGBA: Value = BayerGR2BGRA

  /** equivalent to BGGR Bayer pattern */
  val BayerRG2RGBA: Value = BayerBG2BGRA

  /** equivalent to GBRG Bayer pattern */
  val BayerGR2RGBA: Value = BayerGB2BGRA

  val COLORCVTMax: Value = Value(143)
}
