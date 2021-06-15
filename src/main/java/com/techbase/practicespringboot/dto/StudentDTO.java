package com.techbase.practicespringboot.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;

    private String name;

    private Integer age;

    private String gender;

    private String address;

    private Double score;

    private String date;

    private String time;
}
