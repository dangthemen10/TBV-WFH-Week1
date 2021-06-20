package com.techbase.practicespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFormDTO {
    private List<String> fname;
    private List<String> fage;
    private String fdate;
}
