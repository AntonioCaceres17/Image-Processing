
This is the USEME File to go along with our JAR File and the ImageCommand class.

To import an image : import [name] (must be a valid image file name) :   IMPORT ex.png
This will automatically create a new layer. 

To make an image in the stack as current image : current [insex(0-size -1)] : CURRENT 1

To toggle the current layers visibility: toggle 

To swap two layers in the stack: swap [index 1] [index 2] : SWAP 0 1 
To Export an image : export [Image name] (Must be valid : export foo.jpg

To blur the current image : blur 
To sharpen the current image : sharpen
To add a sepia filter to the current image : sepia
To add a greyscale filter to the current image : greyscale
