package cloud.unionj.generator.mock.schemafaker;

import com.fasterxml.jackson.databind.JsonNode;
import cloud.unionj.generator.mock.schemafaker.propertyfaker.FakerNotFoundException;
import cloud.unionj.generator.mock.schemafaker.propertyfaker.FormatConstants;
import cloud.unionj.generator.mock.schemafaker.propertyfaker.UnknownFormatException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.schemafaker
 *  date 2020/11/20
 */
public class DefaultSchemaFakerTest {

  @Test
  public void fakeFormat() {
    DefaultSchemaFaker defaultSchemaFaker = new DefaultSchemaFaker();
    JsonNode jsonNode = defaultSchemaFaker.fakeFormat(FormatConstants.INT32.getFormat());
    Assert.assertNotNull(jsonNode);
  }

  @Test(expected = UnknownFormatException.class)
  public void UnknownFormatException() {
    DefaultSchemaFaker defaultSchemaFaker = new DefaultSchemaFaker();
    defaultSchemaFaker.fakeFormat("notexistsformat");
  }

  @Test(expected = FakerNotFoundException.class)
  public void FakerNotFoundException() {
    DefaultSchemaFaker defaultSchemaFaker = new DefaultSchemaFaker();
    defaultSchemaFaker.fakeFormat("testonly");
  }

  @Test
  public void fakePrimitiveType() {
  }

  @Test
  public void fakeObject() {
  }

  @Test
  public void createRootObjectNode() {
  }
}
