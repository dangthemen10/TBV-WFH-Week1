package com.techbase.practicespringboot.repository;

import com.techbase.practicespringboot.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>,
        JpaSpecificationExecutor<StudentEntity> {
    List<StudentEntity> findByNameContaining(String name);

    @Query("SELECT e.name FROM StudentEntity e")
    Set<String> findAllName();
}
