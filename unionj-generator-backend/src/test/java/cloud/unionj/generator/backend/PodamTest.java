package cloud.unionj.generator.backend;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;

public class PodamTest {

  public enum RoleType {
    PRODUCT_MEMBER;
  }

  public static void main(String[] args) {
    PodamFactory factory = new PodamFactoryImpl();
    Long aLong = factory.manufacturePojo(Long.class);
    System.out.println(aLong);

    Integer integer = factory.manufacturePojo(int.class);
    System.out.println(integer);

    @SuppressWarnings("unchecked")
    List<String> list = factory.manufacturePojo(List.class, String.class);
    System.out.println(list);

    String[] strings = factory.manufacturePojo(String[].class);
    System.out.println(strings);

    RoleType roleType = factory.manufacturePojo(RoleType.class);
    System.out.println(roleType);
  }
}
