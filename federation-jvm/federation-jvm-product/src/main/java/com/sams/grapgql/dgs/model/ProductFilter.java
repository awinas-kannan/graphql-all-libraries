package com.sams.grapgql.dgs.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductFilter {
    private String name;
    private String description;
}
