package HW05.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Abstract test class for Image function objects
 */
public abstract class IFunctionRGBTest {

  protected abstract IFunction createFunction();

  @Test(expected = IllegalArgumentException.class)
  public void testNullImageFunction() {
    IFunction transform = createFunction();
    transform.apply(null);
  }
}
