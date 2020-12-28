package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.model.Schema;

import java.util.Date;

import static com.tsingxiao.unionj.generator.openapi3.dsl.Reference.reference;
import static com.tsingxiao.unionj.generator.openapi3.dsl.Schema.schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: com.tsingxiao.unionj.generator.openapi3.dsl
 * @date:2020/12/26
 */
public class SchemaHelper {

  public static final Schema int32 = schema(int32 -> {
    int32.type("integer");
    int32.title(Integer.class.getSimpleName());
    int32.format("int32");
  });

  public static final Schema int64 = schema(int64 -> {
    int64.type("integer");
    int64.title(Long.class.getSimpleName());
    int64.format("int64");
  });

  public static final Schema string = schema(string -> {
    string.type("string");
    string.title(String.class.getSimpleName());
  });

  public static final Schema bool = schema(bool -> {
    bool.type("boolean");
    bool.title(Boolean.class.getSimpleName());
  });

  public static final Schema floatNumber = schema(floatNumer -> {
    floatNumer.type("number");
    floatNumer.format("float");
    floatNumer.title(Float.class.getSimpleName());
  });

  public static final Schema doubleNumer = schema(doubleNumer -> {
    doubleNumer.type("number");
    doubleNumer.format("double");
    doubleNumer.title(Double.class.getSimpleName());
  });

  public static final Schema dateTime = schema(dateTime -> {
    dateTime.type("string");
    dateTime.format("date-time");
    dateTime.title(Date.class.getSimpleName());
  });

  public static final Schema T = schema(t -> {
    t.type("T");
  });

  public static final Schema list = schema(a -> {
    a.title("List");
  });

  public static final Schema set = schema(a -> {
    a.title("Set");
  });

  public static final Schema stringArray = schema(array -> {
    array.type("array");
    array.items(string);
    array.title("List<String>");
  });

  public static final Schema int32Array = schema(array -> {
    array.type("array");
    array.items(int32);
    array.title("List<Integer>");
  });

  public static final Schema int64Array = schema(array -> {
    array.type("array");
    array.items(int64);
    array.title("List<Long>");
  });

  public static final Schema floatArray = schema(array -> {
    array.type("array");
    array.items(floatNumber);
    array.title("List<Float>");
  });

  public static final Schema doubleArray = schema(array -> {
    array.type("array");
    array.items(doubleNumer);
    array.title("List<Double>");
  });

  public static final Schema boolArray = schema(array -> {
    array.type("array");
    array.items(bool);
    array.title("List<Boolean>");
  });

  public static final Schema dateTimeArray = schema(array -> {
    array.type("array");
    array.items(dateTime);
    array.title("List<Date>");
  });

  public static Schema refArray(String ref) {
    return schema(array -> {
      array.type("array");
      array.items(reference(items -> {
        items.ref(ref);
      }));
      array.title("List<" + ref + ">");
    });
  }

  public static Schema int32(String description) {
    return schema(int32 -> {
      int32.type("integer");
      int32.format("int32");
      int32.description(description);
      int32.title(Integer.class.getSimpleName());
    });
  }

  public static Schema int64(String description) {
    return schema(int64 -> {
      int64.type("integer");
      int64.format("int64");
      int64.description(description);
      int64.title(Long.class.getSimpleName());
    });
  }

  public static Schema string(String description) {
    return schema(string -> {
      string.type("string");
      string.description(description);
      string.title(String.class.getSimpleName());
    });
  }

  public static Schema bool(String description) {
    return schema(bool -> {
      bool.type("boolean");
      bool.description(description);
      bool.title(Boolean.class.getSimpleName());
    });
  }

  public static Schema floatNumber(String description) {
    return schema(floatNumer -> {
      floatNumer.type("number");
      floatNumer.format("float");
      floatNumer.description(description);
      floatNumer.title(Float.class.getSimpleName());
    });
  }

  public static Schema doubleNumer(String description) {
    return schema(doubleNumer -> {
      doubleNumer.type("number");
      doubleNumer.format("double");
      doubleNumer.description(description);
      doubleNumer.title(Double.class.getSimpleName());
    });
  }

  public static Schema dateTime(String description) {
    return schema(dateTime -> {
      dateTime.type("string");
      dateTime.format("date-time");
      dateTime.description(description);
      dateTime.title(Date.class.getSimpleName());
    });
  }

  public static Schema stringArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(string(description));
      array.title("List<String>");
    });
  }

  public static Schema int32Array(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int32(description));
      array.title("List<Integer>");
    });
  }

  public static Schema int64Array(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int64(description));
      array.title("List<Long>");
    });
  }

  public static Schema floatArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(floatNumber(description));
      array.title("List<Float>");
    });
  }

  public static Schema doubleArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(doubleNumer(description));
      array.title("List<Double>");
    });
  }

  public static Schema boolArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(bool(description));
      array.title("List<Boolean>");
    });
  }

  public static Schema dateTimeArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(dateTime(description));
      array.title("List<Date>");
    });
  }

  public static Schema refArray(String description, String ref) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(reference(items -> {
        items.ref(ref);
      }));
      array.title("List<" + ref + ">");
    });
  }

  public static Schema enums(String description, String[] values) {
    return schema(enums -> {
      enums.type("string");
      enums.description(description);
      for (String value : values) {
        enums.enumValue(value);
      }
    });
  }

  public static Schema int32(String description, int example) {
    return schema(int32 -> {
      int32.type("integer");
      int32.format("int32");
      int32.description(description);
      int32.example(example);
      int32.title(Integer.class.getSimpleName());
    });
  }

  public static Schema int64(String description, long example) {
    return schema(int64 -> {
      int64.type("integer");
      int64.format("int64");
      int64.description(description);
      int64.example(example);
      int64.title(Long.class.getSimpleName());
    });
  }

  public static Schema string(String description, String example) {
    return schema(string -> {
      string.type("string");
      string.description(description);
      string.example(example);
      string.title(String.class.getSimpleName());
    });
  }

  public static Schema bool(String description, boolean example) {
    return schema(bool -> {
      bool.type("boolean");
      bool.description(description);
      bool.example(example);
      bool.title(Boolean.class.getSimpleName());
    });
  }

  public static Schema floatNumber(String description, float example) {
    return schema(floatNumer -> {
      floatNumer.type("number");
      floatNumer.format("float");
      floatNumer.description(description);
      floatNumer.example(example);
      floatNumer.title(Float.class.getSimpleName());
    });
  }

  public static Schema doubleNumer(String description, double example) {
    return schema(doubleNumer -> {
      doubleNumer.type("number");
      doubleNumer.format("double");
      doubleNumer.description(description);
      doubleNumer.example(example);
      doubleNumer.title(Double.class.getSimpleName());
    });
  }

  public static Schema dateTime(String description, String example) {
    return schema(dateTime -> {
      dateTime.type("string");
      dateTime.format("date-time");
      dateTime.description(description);
      dateTime.example(example);
      dateTime.title(Date.class.getSimpleName());
    });
  }

  public static Schema stringArray(String description, String[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(string(description));
      array.example(example);
      array.title("List<String>");
    });
  }

  public static Schema int32Array(String description, int[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int32(description));
      array.example(example);
      array.title("List<Integer>");
    });
  }

  public static Schema int64Array(String description, long[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int64(description));
      array.example(example);
      array.title("List<Long>");
    });
  }

  public static Schema floatArray(String description, float[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(floatNumber(description));
      array.example(example);
      array.title("List<Float>");
    });
  }

  public static Schema doubleArray(String description, double[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(doubleNumer(description));
      array.example(example);
      array.title("List<Double>");
    });
  }

  public static Schema boolArray(String description, boolean[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(bool(description));
      array.example(example);
      array.title("List<Boolean>");
    });
  }

  public static Schema dateTimeArray(String description, String[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(dateTime(description));
      array.example(example);
      array.title("List<Date>");
    });
  }

  public static Schema refArray(String description, String ref, Object[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(reference(items -> {
        items.ref(ref);
      }));
      array.example(example);
      array.title("List<" + ref + ">");
    });
  }
}
