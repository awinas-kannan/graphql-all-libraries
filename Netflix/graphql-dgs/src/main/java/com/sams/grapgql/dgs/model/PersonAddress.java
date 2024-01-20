package com.sams.grapgql.dgs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonAddress {

    private String streetName;
    private String houseNumber;
    private String city;
    private String country;

}
