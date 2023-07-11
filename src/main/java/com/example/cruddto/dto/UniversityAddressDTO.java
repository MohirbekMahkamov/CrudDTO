package com.example.cruddto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityAddressDTO {
    private String name;
    private String city;
    private String district;
    private String street;


}
