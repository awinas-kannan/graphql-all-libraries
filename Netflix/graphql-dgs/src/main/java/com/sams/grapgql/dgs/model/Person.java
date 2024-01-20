package com.sams.grapgql.dgs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private Integer personId;
    private String personName;
    private PersonAddress personAddress;
}
