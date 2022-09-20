# i4s-opencv
A reasonably well-typed interface for JavaCPP + OpenCV
* Extends the [JavaCV](https://github.com/bytedeco/javacv) project with more idiomatic Scala type wrappers. 
* The first guiding principle is to embrace, wherever reasonable, the variable and type immutability and type safety that 
makes Scala a powerful language.
* Attempts to retain the benefits of the underlaying [OpenCV](https://github.com/opencv/opencv) native C++ libraries wherever
doing so doesnot interfere with our first guiding principle.
## Resources
* [OpenCV](https://opencv.org) / [Github](https://github.com/opencv/opencv) 
* [JavaCv](https://github.com/bytedeco/javacv)
* [JavaCPP Presets](https://github.com/bytedeco/javacpp-presets)
## Getting Started
The currently available version is `0.1.0-SNAPSHOT` on the `https://s01.oss.sonatype.org/` repository.
### Maven
```xml
<dependency>
  <groupId>io.github.intelligence4s</groupId>
  <artifactId>i4s-opencv_2.12</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```
### SBT
```scala
resolvers ++= Seq("Sonatype OSS Snapshots" at "https://s01.oss.sonatype.org/content/repositories/snapshots")

depdendencies += "io.github.intelligence4s" %% "i4s-opencv" % "0.1.0-SNAPSHOT"
```

## Examples

### Mat

The [Mat](https://docs.opencv.org/4.x/d3/d63/classcv_1_1Mat.html#a385c09827713dc3e6d713bfad8460706) class one of the cornerstone 
types defined in the OpenCV library. It encapsulates an `n-dimensional dense array` of numerical values. Values contained in the matrix
may be single or multi-channel (a collection of values). In native OpenCV, `Mat` encapsulates functionality for allocation, reallocation
and computational manipulation of values in a dense memory matrix. In our Scala wrapper, we wanted to preserve the performance 
characteristics of that encapsulation while maintaining as many of the Scala principles of immutability and type safety as is 
reasonable.

`Mat` objects may contain primitive values (single-channel) or higher-level abstractions like `Point` or `Scalar` that are collections
of primitive values (multi-channel). To support this in a type-safe way, we define two parameterized types; `Mat[T]` and `MappedMat[M,T]` 
as shown below.

#### Wrapping a native matrix

```scala
// Initialize a new javacv Mat, this allocates a 3-channel 150 x 150 matrix of unsigned bytes and initializes values to Scalar.Red 
val nativeMat = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv8U, 3), Scalar.Red)
// Using the Mat class constructor "wraps" the allocated object (so no additional memory is allocated and no copies are made)
val pixels = new Mat[Int](nativeMat)

// Now we can access values directly using a matrix index (x,y,c), where c is the channel index.
pixels.get(0,0,0) shouldBe Scalar.Red.v0

// Using the JavaCV Indexer we can change the underlying matrix and see that it is reflected in our wrapper.
val indexer = matOfPixels.createIndexer().asInstanceOf[UByteIndexer]
indexer.put(Array[Long](0,0,0), 128)
pixels.get(0,0,0,0) shouldBe 128
```

In the above example, we use the Mat types constructor to wrap an existing matrix that has already been allocated. We show that 
changes to the underlying matrix are reflected in our wrapper. There are two things to note about this example: 
1. Wrapping existing memory that is "owned" by a separate thread or earlier processing is not idiomatic to Scala. While it is not 
reasonable to consider making a matrix immutable for native processing under the covers, it would be preferable to have it allocated
and managed in a single scope. 
2. We allow for the constructor to wrap matrices because the JavaCV / OpenCV systems have many functions that generate large 
matrices, and providing an efficient method to "take over" these pre-allocated matrices seems important.  
2. We show a 3-channel matrix where each value is represented by 3 unsigned bytes (a pixel), but our `Mat[T]` type's `get()` and `put()` 
methods work on individual bytes. It would be more convenient to allow for pixels to be manipulated as a unit, instead of with their 
individual components.

#### Allocating a new matrix

```scala
// Allocates a native single-channel matrix of Doubles and initializes them to 255
val nativeMat = new org.bytedeco.opencv.opencv_core.Mat(150, 150, MatTypes.makeType(Types.Cv64F, 1), Scalar.White)
// Utilizes the companion object factory -- creates a fresh copy of the native matrix!
val doubles = Mat[Double](nativeMat)
// First value is 255
doubles.get(0, 0) shouldBe Scalar.White.v0
// Set the native matrix to 128
val indexer = matOfDoubles.createIndexer().asInstanceOf[DoubleIndexer]
indexer.put(0L, 0L, 128d)
// Observe that doubles matrix is unchanged
doubles.get(0, 0) shouldBe Scalar.White.v0
```

Above, we can see how a brand new `Mat[T]` can be constructed. We generally recommend that i4s-opencv users use the companion
object factories unless you are leveraging a system-manufactured matrix.

#### Matrices of higher order structures

```scala
// Create a 150 x 150 matrix of Scalar values representing pixels
val pixels = MappedMat[Scalar,Int](150, 150, MatTypes.makeType(Types.Cv8U,3), Scalar.White)
pixels.get(0,0) shouldBe Scalar.White
// We can also use the put method to change whole pixles...
pixels.put(0,0,Scalar(0,0,0,0))
// Or a whole bunch of them...
pixles.put(0,0,List(Scalar(0,0,0,0),Scalar(0,0,0,0),Scalar(0,0,0,0),Scalar(0,0,0,0),Scalar(0,0,0,0)))
```

In this case access methods for `MappedMat[M,T]` will return whole Scalar objects. The default `MappedMat[M,T]` support includes
type class definitions for `Scalar`, `Point`, and `Rect` objects. It should be noted that the `MappedMat[M,T]` type is a type 
class, and other higher order, composable types may be supported.

#### Images

In `JavaCV` & `OpenCV` image operations are defined against the Mat object. In designing our wrappers, we found this to be 
problematic in terms of type safety. To address this, we have defined an `Image` type that extends `MappedMat[Scalar,Int]`. We then 
split the *many* image utilies accross several modules that are implemented as implicit extensions of the Image type.

```scala
// Create a new, image and initialize it to blue...
val image = Image(150,150,3,Scalar.Blue)

// make a red rectangle in the center...
import Drawing._

val rectangle = Rect(Point(30,30),Point(120,120))
image.rectangle(rectangle,Scalar.Red,LineTypes.Line4,-1,0)

// with a white circle in it... 
image.circle(Point(75,75),45,Scalar.Red,LineTypes.Line4,-1,0)

// convert it to gray scale...
import Colors._
val gray = image.cvtColor(ColorConversionCodes.BGR2Gray)

// and save it out...
import Codecs._
gray.write(new File("black-and-white.png"))
```

The current version of i4s-opencv supports the following image processing modules:
* **Codecs** - loading and writing images of various file types and formats
* **Colors** - conversion, translation and assignment of color palettes
* **Drawing** - tools for drawing lines and shapes
* **Filters** - various blurring and effect filters
* **Shapes** - shape and contour detection and segmentation tools
* **Transforms** - threshold manipulation, warping, shifting and filling utilities

# Attribution and Notes
* This project takes advantage of the [JavaCV](https://github.com/bytedeco/javacv) project, lead by Samuel Audet 
* Care has been taken to choose [JavaCPP Presets](https://github.com/bytedeco/javacpp-presets) that will work with Apple Silicon, primarily because that is the hardware I code on :) 
* Contributors are welcome. Submit pull requests or drop me a line at `thebrenthaines at yahoo.com` if you are interested in or need any extensions or improvements. Issues may be opened as well.  