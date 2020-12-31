package cloud.unionj.generator.openapi3.dsl;

import cloud.unionj.generator.openapi3.dsl.components.Components;
import cloud.unionj.generator.openapi3.model.Schema;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.dsl
 * @date:2020/12/26
 */
public class SchemaHelper {

  public static final String LEFT_ARROW = "«";
  public static final String RIGHT_ARROW = "»";

  public static final cloud.unionj.generator.openapi3.model.Schema int32 = Schema.schema(int32 -> {
    int32.type("integer");
    int32.format("int32");
  });

  public static final cloud.unionj.generator.openapi3.model.Schema int64 = Schema.schema(int64 -> {
    int64.type("integer");
    int64.format("int64");
  });

  public static final cloud.unionj.generator.openapi3.model.Schema string = Schema.schema(string -> {
    string.type("string");
  });

  public static final cloud.unionj.generator.openapi3.model.Schema bool = Schema.schema(bool -> {
    bool.type("boolean");
  });

  public static final cloud.unionj.generator.openapi3.model.Schema floatNumber = Schema.schema(floatNumer -> {
    floatNumer.type("number");
    floatNumer.format("float");
  });

  public static final cloud.unionj.generator.openapi3.model.Schema doubleNumer = Schema.schema(doubleNumer -> {
    doubleNumer.type("number");
    doubleNumer.format("double");
  });

  public static final cloud.unionj.generator.openapi3.model.Schema dateTime = Schema.schema(dateTime -> {
    dateTime.type("string");
    dateTime.format("date-time");
  });

  public static final cloud.unionj.generator.openapi3.model.Schema T = Schema.schema(t -> {
    t.type("object");
    t.format("T");
  });

  public static final cloud.unionj.generator.openapi3.model.Schema ListT = Schema.schema(t -> {
    t.type("array");
    t.items(T);
  });

  public static final cloud.unionj.generator.openapi3.model.Schema SetT = Schema.schema(t -> {
    t.type("array");
    t.items(T);
    t.uniqueItems(true);
  });

  public static final cloud.unionj.generator.openapi3.model.Schema stringArray = Schema.schema(array -> {
    array.type("array");
    array.items(string);
  });

  public static final cloud.unionj.generator.openapi3.model.Schema int32Array = Schema.schema(array -> {
    array.type("array");
    array.items(int32);
  });

  public static final cloud.unionj.generator.openapi3.model.Schema int64Array = Schema.schema(array -> {
    array.type("array");
    array.items(int64);
  });

  public static final cloud.unionj.generator.openapi3.model.Schema floatArray = Schema.schema(array -> {
    array.type("array");
    array.items(floatNumber);
  });

  public static final cloud.unionj.generator.openapi3.model.Schema doubleArray = Schema.schema(array -> {
    array.type("array");
    array.items(doubleNumer);
  });

  public static final cloud.unionj.generator.openapi3.model.Schema boolArray = Schema.schema(array -> {
    array.type("array");
    array.items(bool);
  });

  public static final cloud.unionj.generator.openapi3.model.Schema dateTimeArray = Schema.schema(array -> {
    array.type("array");
    array.items(dateTime);
  });

  public static cloud.unionj.generator.openapi3.model.Schema refArray(String ref) {
    return Schema.schema(array -> {
      array.type("array");
      array.items(Reference.reference(items -> {
        items.ref(ref);
      }));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema uniqueRefArray(String ref) {
    return Schema.schema(array -> {
      array.type("array");
      array.uniqueItems(true);
      array.items(Reference.reference(items -> {
        items.ref(ref);
      }));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema int32(String description) {
    return Schema.schema(int32 -> {
      int32.type("integer");
      int32.format("int32");
      int32.description(description);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema int64(String description) {
    return Schema.schema(int64 -> {
      int64.type("integer");
      int64.format("int64");
      int64.description(description);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema string(String description) {
    return Schema.schema(string -> {
      string.type("string");
      string.description(description);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema bool(String description) {
    return Schema.schema(bool -> {
      bool.type("boolean");
      bool.description(description);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema floatNumber(String description) {
    return Schema.schema(floatNumer -> {
      floatNumer.type("number");
      floatNumer.format("float");
      floatNumer.description(description);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema doubleNumer(String description) {
    return Schema.schema(doubleNumer -> {
      doubleNumer.type("number");
      doubleNumer.format("double");
      doubleNumer.description(description);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema dateTime(String description) {
    return Schema.schema(dateTime -> {
      dateTime.type("string");
      dateTime.format("date-time");
      dateTime.description(description);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema stringArray(String description) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(string(description));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema int32Array(String description) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int32(description));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema int64Array(String description) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int64(description));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema floatArray(String description) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(floatNumber(description));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema doubleArray(String description) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(doubleNumer(description));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema boolArray(String description) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(bool(description));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema dateTimeArray(String description) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(dateTime(description));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema refArray(String description, String ref) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(Reference.reference(items -> {
        items.ref(ref);
      }));
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema enums(String description, String[] values) {
    return Schema.schema(enums -> {
      enums.type("string");
      enums.description(description);
      for (String value : values) {
        enums.enumValue(value);
      }
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema int32(String description, int example) {
    return Schema.schema(int32 -> {
      int32.type("integer");
      int32.format("int32");
      int32.description(description);
      int32.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema int64(String description, long example) {
    return Schema.schema(int64 -> {
      int64.type("integer");
      int64.format("int64");
      int64.description(description);
      int64.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema string(String description, String example) {
    return Schema.schema(string -> {
      string.type("string");
      string.description(description);
      string.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema bool(String description, boolean example) {
    return Schema.schema(bool -> {
      bool.type("boolean");
      bool.description(description);
      bool.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema floatNumber(String description, float example) {
    return Schema.schema(floatNumer -> {
      floatNumer.type("number");
      floatNumer.format("float");
      floatNumer.description(description);
      floatNumer.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema doubleNumer(String description, double example) {
    return Schema.schema(doubleNumer -> {
      doubleNumer.type("number");
      doubleNumer.format("double");
      doubleNumer.description(description);
      doubleNumer.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema dateTime(String description, String example) {
    return Schema.schema(dateTime -> {
      dateTime.type("string");
      dateTime.format("date-time");
      dateTime.description(description);
      dateTime.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema stringArray(String description, String[] example) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(string(description));
      array.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema int32Array(String description, int[] example) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int32(description));
      array.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema int64Array(String description, long[] example) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int64(description));
      array.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema floatArray(String description, float[] example) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(floatNumber(description));
      array.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema doubleArray(String description, double[] example) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(doubleNumer(description));
      array.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema boolArray(String description, boolean[] example) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(bool(description));
      array.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema dateTimeArray(String description, String[] example) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(dateTime(description));
      array.example(example);
    });
  }

  public static cloud.unionj.generator.openapi3.model.Schema refArray(String description, String ref, Object[] example) {
    return Schema.schema(array -> {
      array.type("array");
      array.description(description);
      array.items(Reference.reference(items -> {
        items.ref(ref);
      }));
      array.example(example);
    });
  }

  public static void batchImport(Class<?> clazz) {
    Field[] declaredFields = clazz.getDeclaredFields();
    List<Field> staticPublicFields = new ArrayList<>();
    for (Field field : declaredFields) {
      if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) && java.lang.reflect.Modifier.isPublic(field.getModifiers())) {
        staticPublicFields.add(field);
      }
    }
    Components.components(cb -> {
      for (Field field : staticPublicFields) {
        try {
          cloud.unionj.generator.openapi3.model.Schema schema = (cloud.unionj.generator.openapi3.model.Schema) field.get(null);
          String key = schema.getTitle();
          if (StringUtils.isEmpty(key)) {
            key = field.getName();
          }
          cb.schemas(key, schema);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
