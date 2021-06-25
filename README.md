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

IMAGECONTROLLER

Image controller is the interface for a controller for a multi-layered imade model. This controller lets the client read from a readable, where each line in the readable has its own command.

IMAGECOMMAND

ImageCommand is the class that implements the ImageController interface. This class implements the read method so that each line in the readable is its own command. Read throws an error if an invalid input is given. This class is the only one that communicates with the model.

LAYEREDIMAGEMODEL

LayeredImageModel is an interface for a an image that represents a layer in a multi-layered image. This interface adapts an image and gives it the ability to toggle its visibility and take on a name for the image.

LAYEREDIMAGE2D

LayeredImage2D implements LayeredImageModel. LayeredImage2D is a class that is an adapter to Image2D and offers the functionality of toggling the image's visibility and giving it a name.

IMULTILAYERIMAGEMODEL

IMultiLayerImageModel is an interface containing methods for a multi-layered image. A multi-layered image has the ability to have multiple layers of LayeredImageModels where each image is the same size. The layered image model works by setting a current layer and then IFunctions can be applied to the current layer. The current layer can also be toggled on and off and each layer can be toggles on and off by setting it to be the current one and toggling it. Layers can also be swapped. When exporting a multi-layer image only the topmost visible layer is exported.

MULTILAYERIMAGE

MultiLayerImage is a class that implements IMultiLayerImageModel. This class is an image model with multiple layers with all the functionality as a described in the IMultiLayerImageModel interface.

IEXPORTIMAGE

IExportImage is an interface for classes that export images as ppm, jpg, or png files.

EXPORTIMAGE

ExportImage implements IExportImage and takes in an ImageModel to be exported as the given filetype.

MOSAIC

Mosaic is a class that implements IFunction and turns a given ImageModel into a "mosaiced" version of the image. A Mosaic takes in a seed which is the number of tiles the "mosaiced" image will have. When the apply method is called with a given image it works by randomly generating the number of tiles based on the given number of seeds and connecting the closest pixel to the closest seed location and then averaging the color value of all those pixels.

DOWNSIZE

Downsize is a class that implements IFunction and downsizes an image. Downsize takes in 2 arguments, how much to scale down width and height. The inputs must be a doubles greater than 0 and less than or equal to 1. When the user calls apply on a valid ImageModel a new ImageModel is returned scaled down to the correct size.

LIMEMAIN

LimeMain is the file that contains the main method.

Citations: 
The P3 File "croppedMule.ppm" used as an example is curtesy of The United States Naval Academy IC210 Website. 
https://www.usna.edu/Users/cs/choi/ic210/project/p01/index.html

The P3 File "Acadia.ppm" used as an example is from an image I took while on a trip to Acadia Nation Park this spring.

The P6 File used as a test for the curropt file is curtesy of Cornell's CS 664 - Computer Vision Website. 
http://www.cs.cornell.edu/courses/cs664/2003fa/images/



