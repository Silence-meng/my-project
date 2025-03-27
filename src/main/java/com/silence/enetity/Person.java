package com.silence.enetity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author silence
 * @since 2025/3/21 16:38
 **/
@Data
public class Person {

    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("birth_date")
    private String birthDate;
}
