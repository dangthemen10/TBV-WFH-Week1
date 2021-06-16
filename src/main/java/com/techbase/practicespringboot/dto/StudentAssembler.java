package com.techbase.practicespringboot.dto;

import com.techbase.practicespringboot.entity.StudentEntity;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StudentAssembler {
    public StudentDTO toDTO(StudentEntity studentEntity) throws ParseException {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setName(studentEntity.getName());
        studentDTO.setAge(studentEntity.getAge());
        studentDTO.setGender(studentEntity.getGender());
        studentDTO.setAddress(studentEntity.getAddress());
        studentDTO.setScore(studentEntity.getScore());
        if(studentEntity.getDate() != null) {
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = originalFormat.parse(studentEntity.getDate().toString());
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            studentDTO.setDate(newFormat.format(date));
        }
        if(studentEntity.getTime() != null) {
            studentDTO.setTime(String.format("%02d:%02d:%02d", studentEntity.getTime()/10000, (studentEntity.getTime()%10000)/100, (studentEntity.getTime()%10000)%100));
        }
        return studentDTO;
    }

    public Page<StudentDTO> from(Page<StudentEntity> studentEntityPage) {
        List<StudentDTO> postList = new ArrayList<>();
        studentEntityPage.forEach(studentEntity -> {
            try {
                postList.add(this.toDTO(studentEntity));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return new PageImpl<>(postList, studentEntityPage.getPageable(), studentEntityPage.getTotalElements());
    }
}
