package HW05.Model;

/**
 * This class represents a general filter to be applied to an image
 */
// NOT SURE ABOUT THE GENERIC TYPES HERE
public abstract class Filter<K> implements IFunction<K> {

  @Override
  public K apply() {
    return null;
  }
}
