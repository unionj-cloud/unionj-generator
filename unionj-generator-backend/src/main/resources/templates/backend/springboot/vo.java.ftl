package ${packageName};

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.*;
<#if imports??>
<#list imports as import>
import ${import};
</#list>
</#if>

@Data
public class ${name}   {

<#list properties as property>
    @JsonProperty("${property.jsonProperty}")
    private ${property.type} ${property.name};
</#list>

<#if enumTypes??>
<#list enumTypes as enumType>
    public enum ${enumType.name} {
    <#list enumType.enums as enum>
        ${enum.name}("${enum.value}")<#if enum?has_next>,<#else>;</#if>
    </#list>

        private String value;

        ${enumType.name}(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static ${enumType.name} fromValue(String text) {
            for (${enumType.name} b : ${enumType.name}.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }
</#list>
</#if>
}
