package com.techbase.practicespringboot.service;

import com.techbase.practicespringboot.dto.SearchFormDTO;
import com.techbase.practicespringboot.dto.StudentDTO;
import com.techbase.practicespringboot.entity.StudentEntity;
import org.springframework.data.domain.Page;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Set;

public interface StudentService {

    Page<StudentDTO> findAllStudent(int pageNo, int pageSize);

    StudentEntity findOneStudent(long id);

    Set<String> allName();

    void saveStudent(StudentEntity studentEntity);

    void deleteStudent(StudentEntity studentEntity);

    ByteArrayInputStream writeDataToCsv();

    Page<StudentDTO> search(SearchFormDTO searchFormDTO, int pageNo, int pageSize);
}
