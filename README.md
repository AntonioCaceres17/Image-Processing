# Image-Processing
CS3500 

The goal of this program was to be able to take in, READ, CREATE, and REPRODUCE an image, specifically a P3 file.

*********DELETE THIS********* FOR REFENCE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
A text README file explaining your design. Your README file should give the graders an overview of what the purposes are for every class, interface, etc. that you include in your model, so that they can quickly get a high-level overview of your code

To start,

IPIXEL
We made and IPixel interface that stores and can ouput the standard RGB properties, as well as the specific channels properties. 

RGBPIXEL
For HW5, we are using a SRGB Pixel, hence the name of the class that implements IPixel, RGBPixel. It follows all the required aspects of IPixel, as well as an overwritten Hashcode and Equals method for testing purposes. 

IMAGEMODEL
We made an ImageModel as an interface to be able to appropriately read and reproduce an image. For the reading aspect, the any class that implements should be able to store the properites of an iamge, such as height, width, min and max values of pixels, as well as be able to CREATE an image given a grid of pixels(CopyProperties).

IMAGE2D; IMAGEREADER
Image2D is our implementation of the ImageModel. As shown in the class diagram is fufills any of the requirements of the ImageModel interface with an nested static class that is able to READ and scan and store the properties of a PPM P3 File.

EXPORTIMAGE
ExportImage is our class we used to export our 2DImage ImageModel objects as P3 or .ppm files. This class works by creating an exportimage and passing an ImageModel to the constructor. The ImageModel cannot be null.

IFUNCTION 

This is the function interface, which sole purpose is the apply method, which to stores and REPRODUCES the said image with the updates properties.

TRANSFORMATION

The transformation method is an Abstract class, that should be extended for any type of specific transformation to eliminate duplicate code. It implements the apply method which creates a copy of the given image and calls a different a different method to do Matrix calculations on the RGB each indivdual pixel. 

RGBTRANSTORMATION

RGBTransformation is an abstract class and extends Transformation and is used for Transformations where the pixels are RGBPixels. The method makePixel returns an RGBPixel. This class is used so that if the Pixel Type changes we only need to create a new class with makePixel returning a different type of IPixel.

SEPIA

Sepia is an RGBTransformation and uses a matrix to give an ImageModel a sepia tone. It is used the same as any IFunction by calling apply and passing in a valid ImageModel.

MONOCHROME

Monochrome is an RGBTransformation and uses a matrix to give an ImageModel a monochrome or greyscale tone. The monochrome matrix makes it so that each IPixel in the given ImageModel has the same value for red, green, and blue after it has been transformed. It is used the same as any IFunction by calling apply and passing in a valid ImageModel. 

FILTER

The filter function object is an abstract class that implements IFunction. Any 2D matrix with double values can be used to filter an image. The image is filtered by taking the matrix and applying the kernel to every pixel in the image and adding adding the multiple of the overlaying pixel with the value of the image and the filter. 

RGBFILTER

RGBFilter is an abstract class and extends Filter and is used for Filters where the pixels are RGBPixels. The method makePixel returns an RGBPixel. This class is used so that if the Pixel type changes we only need to create a new class with makePixel returning a different type of IPixel.

BLURIMAGE

Blur is a function object that extends RBGFilter and applies a 3x3 filter to the image that blurs it. It is used by calling apply on a valid ImageModel.

SHARPENIMAGE

Sharpen is a function object that extends RBGFilter and applies a 5x5 filter to the image that blurs it. It is used by calling apply on a valid ImageModel.

Citations: 
The P3 File used as an example is curtesy of The United States Naval Academy IC210 Website. 
https://www.usna.edu/Users/cs/choi/ic210/project/p01/index.html

The P6 File used as a test for the curropt file is curtesy of Cornell's CS 664 - Computer Vision Website. 
http://www.cs.cornell.edu/courses/cs664/2003fa/images/



