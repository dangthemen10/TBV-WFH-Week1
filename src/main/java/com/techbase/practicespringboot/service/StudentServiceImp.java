package com.techbase.practicespringboot.service;

import com.techbase.practicespringboot.dto.StudentAssembler;
import com.techbase.practicespringboot.dto.StudentDTO;
import com.techbase.practicespringboot.entity.StudentEntity;
import com.techbase.practicespringboot.repository.StudentRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    private static final CSVFormat FORMAT = CSVFormat.newFormat('\t')
            .withHeader("Id", "Name", "Age", "Gender", "Address", "Score", "Date", "Time")
            .withRecordSeparator('\n');


    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<StudentDTO> findAllStudent(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<StudentEntity> studentEntityPage = studentRepository.findAll(pageable);
        return new StudentAssembler().from(studentEntityPage);
    }

    @Override
    public ByteArrayInputStream writeDataToCsv() {
        List<StudentEntity> listStudents = studentRepository.findAll();
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream();
             CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {
            for (StudentEntity studentEntity : listStudents) {
                List<String> data = Arrays.asList(
                        String.valueOf(studentEntity.getId()),
                        studentEntity.getName(),
                        String.valueOf(studentEntity.getAge()),
                        studentEntity.getGender(),
                        studentEntity.getAddress(),
                        String.valueOf(studentEntity.getScore()),
                        String.valueOf(studentEntity.getDate()),
                        String.valueOf(studentEntity.getTime()));
                printer.printRecord(data);
            }
            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }

    @Override
    public List<StudentEntity> searchStudent(String name) {
        return studentRepository.findByNameContaining(name);
    }

    @Override
    public StudentEntity findOneStudent(long id) {
        return studentRepository.getOne(id);
    }

    @Override
    public void saveStudent(StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudent(StudentEntity studentEntity) {
        studentRepository.delete(studentEntity);
    }
}
