package com.tsingxiao.unionj.generator.openapi3.dsl;

import com.tsingxiao.unionj.generator.openapi3.model.Schema;

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
    int32.format("int32");
  });

  public static final Schema int64 = schema(int64 -> {
    int64.type("integer");
    int64.format("int32");
  });

  public static final Schema string = schema(string -> {
    string.type("string");
  });

  public static final Schema bool = schema(string -> {
    string.type("boolean");
  });

  public static final Schema floatNumber = schema(floatNumer -> {
    floatNumer.type("number");
    floatNumer.format("float");
  });

  public static final Schema doubleNumer = schema(doubleNumer -> {
    doubleNumer.type("number");
    doubleNumer.format("double");
  });

  public static final Schema dateTime = schema(dateTime -> {
    dateTime.type("string");
    dateTime.format("date-time");
  });

  public static final Schema stringArray = schema(array -> {
    array.type("array");
    array.items(string);
  });

  public static final Schema int32Array = schema(array -> {
    array.type("array");
    array.items(int32);
  });

  public static final Schema int64Array = schema(array -> {
    array.type("array");
    array.items(int64);
  });

  public static final Schema floatArray = schema(array -> {
    array.type("array");
    array.items(floatNumber);
  });

  public static final Schema doubleArray = schema(array -> {
    array.type("array");
    array.items(doubleNumer);
  });

  public static final Schema boolArray = schema(array -> {
    array.type("array");
    array.items(bool);
  });

  public static final Schema dateTimeArray = schema(array -> {
    array.type("array");
    array.items(dateTime);
  });

  public static Schema refArray(String ref) {
    return schema(array -> {
      array.type("array");
      array.items(reference(items -> {
        items.ref(ref);
      }));
    });
  }

  public static Schema int32(String description) {
    return schema(int32 -> {
      int32.type("integer");
      int32.format("int32");
      int32.description(description);
    });
  }

  public static Schema int64(String description) {
    return schema(int64 -> {
      int64.type("integer");
      int64.format("int32");
      int64.description(description);
    });
  }

  public static Schema string(String description) {
    return schema(string -> {
      string.type("string");
      string.description(description);
    });
  }

  public static Schema bool(String description) {
    return schema(string -> {
      string.type("boolean");
      string.description(description);
    });
  }

  public static Schema floatNumber(String description) {
    return schema(floatNumer -> {
      floatNumer.type("number");
      floatNumer.format("float");
      floatNumer.description(description);
    });
  }

  public static Schema doubleNumer(String description) {
    return schema(doubleNumer -> {
      doubleNumer.type("number");
      doubleNumer.format("double");
      doubleNumer.description(description);
    });
  }

  public static Schema dateTime(String description) {
    return schema(dateTime -> {
      dateTime.type("string");
      dateTime.format("date-time");
      dateTime.description(description);
    });
  }

  public static Schema stringArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(string(description));
    });
  }

  public static Schema int32Array(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int32(description));
    });
  }

  public static Schema int64Array(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int64(description));
    });
  }

  public static Schema floatArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(floatNumber(description));
    });
  }

  public static Schema doubleArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(doubleNumer(description));
    });
  }

  public static Schema boolArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(bool(description));
    });
  }

  public static Schema dateTimeArray(String description) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(dateTime(description));
    });
  }

  public static Schema refArray(String description, String ref) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(reference(items -> {
        items.ref(ref);
      }));
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
    });
  }

  public static Schema int64(String description, long example) {
    return schema(int64 -> {
      int64.type("integer");
      int64.format("int64");
      int64.description(description);
      int64.example(example);
    });
  }

  public static Schema string(String description, String example) {
    return schema(string -> {
      string.type("string");
      string.description(description);
      string.example(example);
    });
  }

  public static Schema bool(String description, boolean example) {
    return schema(bool -> {
      bool.type("boolean");
      bool.description(description);
      bool.example(example);
    });
  }

  public static Schema floatNumber(String description, float example) {
    return schema(floatNumer -> {
      floatNumer.type("number");
      floatNumer.format("float");
      floatNumer.description(description);
      floatNumer.example(example);
    });
  }

  public static Schema doubleNumer(String description, double example) {
    return schema(doubleNumer -> {
      doubleNumer.type("number");
      doubleNumer.format("double");
      doubleNumer.description(description);
      doubleNumer.example(example);
    });
  }

  public static Schema dateTime(String description, String example) {
    return schema(dateTime -> {
      dateTime.type("string");
      dateTime.format("date-time");
      dateTime.description(description);
      dateTime.example(example);
    });
  }

  public static Schema stringArray(String description, String[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(string(description));
      array.example(example);
    });
  }

  public static Schema int32Array(String description, int[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int32(description));
      array.example(example);
    });
  }

  public static Schema int64Array(String description, long[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(int64(description));
      array.example(example);
    });
  }

  public static Schema floatArray(String description, float[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(floatNumber(description));
      array.example(example);
    });
  }

  public static Schema doubleArray(String description, double[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(doubleNumer(description));
      array.example(example);
    });
  }

  public static Schema boolArray(String description, boolean[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(bool(description));
      array.example(example);
    });
  }

  public static Schema dateTimeArray(String description, String[] example) {
    return schema(array -> {
      array.type("array");
      array.description(description);
      array.items(dateTime(description));
      array.example(example);
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
    });
  }
}
