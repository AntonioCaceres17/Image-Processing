# Image-Processing
CS3500 

The goal of this program was to be able to take in, READ, CREATE, and REPRODUCE an image, specifically a P3 file.

To start,

IPIXEL
We made and IPixel interface that stores and can ouput the standard RGB properties, as well as the specific channels properties. 

RGBPIXEL
For HW5, we are using a SRGB Pixel, hence the name of the class that implements IPixel, RGBPixel. It follows all the required aspects of IPixel, as well as an overwritten Hashcode and Equals method for testing purposes. 

IMAGEMODEL
We made an ImageModel as an interface to be able to appropriately read and reproduce an image. For the reading aspect, the any class that implements should be able to store the properites of an iamge, such as height, width, min and max values of pixels, as well as be able to CREATE an image given a grid of pixels(CopyProperties).

IMAGE2D; IMAGEREADER
Image2D is our implementation of the ImageModel. As shown in the class diagram is fufills any of the requirements of the ImageModel interface with an nested static class that is able to READ and scan and store the properties of a PPM P3 File.

IFUNCTION 

This is the function interface, which sole purpose is the apply method, which to stores and REPRODUCES the said image with the updates properties.

TRANSFORMATION

The transformation method is an Abstract class, that should be extended for any type of specific transformation to eliminate duplicate code. It implements the apply method which creates a copy of the given image and calls a different a different method to do Matrix calculations on the RGB each indivdual pixel. 

RGBTRANSFORMATION

The RGBTransformation class extends the abstract transformation class and implements the protected makePixel method by returning a new IPixel made from RGB values. This class transforms any RGB image by the matrix provided to it.

SEPIA

The Sepia class extends RGBTransformation and applies a sepia tone to any image made from RGB Pixels by calling apply and passing in a valid ImageModel.

MONOCHROME

The Monochrome class extends RGBTransformation and applies a monochrome tone to any image made from RGB Pixels by calling apply and passing in a valid ImageModel. The new ImageModel returned by apply has a grayscale image where all pixels now have the same RGB values.

FILTER

The Filter class is an abstract class that extends IFunction takes in a given filter, which is a 2D matrix of double values and its kernal, which is a Point.java, and applies the filter to every pixel in the image. It applies the filter by moving the kernel along every pixel in the image and adding the values of the RGB values of each channel of the pixel multiplied with the overlayed filter. The filter is used by creating a new non abstract filter object  and calling apply on a given ImageModel.

RGBFILTER

RGBFilter is an abstract class that extends Filter and overrides the protected method makePixel to return a new RGBPixel. This class is used whenever the ImageModel is made from RGBPixels.

BLURIMAGE

BlurImage extends RGBFilter and is a function object that blurs the given image when the user calls apply and passes in a valid imagemodel.

SHARPENIMAGE

SharpenImage extends RGBFilter and is a function object that sharpens the given image when the user calls apply and passes in a valid imagemodel.

Citations: 
The P3 File "croppedMule.ppm" used as an example is curtesy of The United States Naval Academy IC210 Website. 
https://www.usna.edu/Users/cs/choi/ic210/project/p01/index.html

The P3 File "Acadia.ppm" used as an example is from an image I took while on a trip to Acadia Nation Park this spring.

The P6 File used as a test for the curropt file is curtesy of Cornell's CS 664 - Computer Vision Website. 
http://www.cs.cornell.edu/courses/cs664/2003fa/images/



