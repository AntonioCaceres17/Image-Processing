package hw7.view;



public interface IView {

  /**
   * This method is reserved for secondary inputs requested from the client. This
   * Method will create a visual drop down menu and will return said information to the recipient
   * @param key the given action to preform by the view.
   * @return the given information requested by the recipient of the method.
   */
  String dropDown(String key);

  void updateImage(String imageFilePath);

  void updateView();
}
