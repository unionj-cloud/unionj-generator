package com.tsingxiao.unionj.demo.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.*;

@Data
public class ResultDTOSetString   {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private Set<Set<String>> data;
    @JsonProperty("ok")
    private boolean ok;

}
