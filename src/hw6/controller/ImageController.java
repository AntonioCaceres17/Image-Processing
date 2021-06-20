package hw6.controller;

public interface ImageController {
  /**
   * This is the controller to be used to command an ImageModel class as well as a
   * Image View Class.
   * This controller should be able to interpret user input, such as loading an image file
   * and being able to do basic functions  on Images.
   * PLEASE CREATE USEME FILE BASED ON OUR IMPLEMENTATION.
   */

  //Load image
  // Edit Image
  // Set Image as current
  // Remove image?
  // Output image
  //  for design... Views should still have a .render message file
  // With try-catch mania......

  void read() throws IllegalStateException, IllegalArgumentException;
}
