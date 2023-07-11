package com.example.cruddto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String fullName;
    private Date birthday;
    private Integer group_id;


}
