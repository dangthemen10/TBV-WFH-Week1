package com.techbase.practicespringboot.dto;

import lombok.Data;
import java.util.List;

@Data
public class SearchFormDTO {
    private List<String> fname;
    private List<String> fage;
    private String fdate;
}
