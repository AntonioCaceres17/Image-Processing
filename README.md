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

IFUNCTION 

This is the function interface, which sole purpose is the apply method, which to stores and REPRODUCES the said image with the updates properties.

TRANSFORMATION

The transformation method is an Abstract class, that should be extended for any type of specific transformation to eliminate duplicate code. It implements the apply method which creates a copy of the given image and calls a different a different method to do Matrix calculations on the RGB each indivdual pixel. 

RGBTransfomation



This class.....




Citations: 
The P3 File used as an example is curtesy of The United States Naval Academy IC210 Website. 
https://www.usna.edu/Users/cs/choi/ic210/project/p01/index.html

The P6 File used as a test for the curropt file is curtesy of Cornell's CS 664 - Computer Vision Website. 
http://www.cs.cornell.edu/courses/cs664/2003fa/images/



