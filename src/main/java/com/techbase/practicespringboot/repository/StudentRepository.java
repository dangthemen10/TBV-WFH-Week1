package com.techbase.practicespringboot.repository;

import com.techbase.practicespringboot.dto.SearchFormDTO;
import com.techbase.practicespringboot.entity.StudentEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>,
        JpaSpecificationExecutor<StudentEntity>{
    List<StudentEntity> findByNameContaining(String name);
}
