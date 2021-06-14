package hw5.model;

import org.junit.Test;

/**
 * Abstract test class for Image function objects.
 */
public abstract class IFunctionRGBTest {

  protected abstract IFunction createFunction();

  @Test(expected = IllegalArgumentException.class)
  public void testNullImageFunction() {
    IFunction transform = createFunction();
    transform.apply(null);
  }
}
