package cloud.unionj.generator.mock.schemafaker.propertyfaker;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.docparser.entity
 * @date 2020/11/19
 */
@Getter
public enum FormatConstants {

  @Faker(IntFaker.class)
  INT32("int32"),

  @Faker(IntFaker.class)
  INT64("int64"),

  @Faker(DoubleFaker.class)
  FLOAT("float"),

  @Faker(DoubleFaker.class)
  DOUBLE("double"),

  @Faker(FileFaker.class)
  BASE64("byte"),

  @Faker(FileFaker.class)
  FILE("binary"),

  @Faker(DateFaker.class)
  DATE("date"),

  @Faker(DateTimeFaker.class)
  DATETIME("date-time"),

  @Faker(PasswordFaker.class)
  PASSWORD("password"),

  @Faker(EmailFaker.class)
  EMAIL("email"),

  @Faker(IDFaker.class)
  ID("id"),

  @Faker(AddressFaker.class)
  ADDRESS("address"),

  @Faker(NameFaker.class)
  NAME("name"),

  @Faker(TitleFaker.class)
  TITLE("title"),

  @Faker(TagFaker.class)
  TAG("tag"),

  @Faker(SentenceFaker.class)
  SENTENCE("sentence"),

  @Faker(PhoneFaker.class)
  PHONE("phone"),

  @Faker(AgeFaker.class)
  AGE("age"),

  @Faker(IntFaker.class)
  BIGDECIMAL("bigdecimal"),

  @Faker(CodeFaker.class)
  CODE("code"),

  @Faker(AvatarFaker.class)
  AVATAR("avatar"),

  @Faker(ImageFaker.class)
  IMAGE("image"),

  @Faker(AnimalFaker.class)
  ANIMAL("animal"),

  @Faker(DogFaker.class)
  DOG("dog"),

  @Faker(CatFaker.class)
  CAT("cat"),

  TESTONLY("testonly");

  private String format;

  FormatConstants(String format) {
    this.format = format;
  }

  public static FormatConstants fromFormat(String format) throws UnknownFormatException {
    Optional<FormatConstants> any = Arrays.stream(FormatConstants.values()).filter(formatConstants -> formatConstants.getFormat().equals(format)).findAny();
    if (any.isPresent()) {
      return any.get();
    }
    throw new UnknownFormatException("unknown format: " + format);
  }


}
