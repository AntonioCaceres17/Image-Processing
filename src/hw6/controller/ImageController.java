package hw6.controller;

/**
 * This is the controller to be used to command an ImageModel class as well as a
 * Image View Class.
 * This controller interprets user input and passes them to the model, to be used.
 * Examples of commands this controller would handle are things such as: loading an image file,
 * blurring or sharpening an image, changing layers, etc.
 */
public interface ImageController {
  /**
   * PLEASE CREATE USEME FILE BASED ON OUR IMPLEMENTATION.
   */

  //Load image
  // Edit Image
  // Set Image as current
  // Remove image?
  // Output image
  //  for design... Views should still have a .render message file
  // With try-catch mania......

  /**
   * This method reads from a readable line by line, with each command on its own line. Any extra
   * inputs on a line after a valid sequence of command inputs will be discarded. If a command is
   * not found or has invalid inputs the controller will throw an error. A commend is marked on
   * a line with a '#' and everything after it on that line is automatically ignored.
   *
   * @throws IllegalStateException
   *         if the controller tries to read a command and theres nothing there
   * @throws IllegalArgumentException
   *         if the command is not found in the list of commands or if the sequence of inputs
   *         after the command is not valid.
   */
  void read() throws IllegalStateException, IllegalArgumentException;
}
