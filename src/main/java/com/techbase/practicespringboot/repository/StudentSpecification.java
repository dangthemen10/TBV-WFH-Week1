package com.techbase.practicespringboot.repository;

import com.techbase.practicespringboot.dto.SearchFormDTO;
import com.techbase.practicespringboot.entity.StudentEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentSpecification {
    @Autowired
    public static Specification<StudentEntity> toPredicate(SearchFormDTO searchFormDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNoneBlank(searchFormDTO.getFname()) && !searchFormDTO.getFname().isEmpty()) {
                searchFormDTO.setFname(StringUtils.deleteWhitespace(searchFormDTO.getFname()));
                predicates.add(criteriaBuilder.equal(root.get(StudentEntity.Fields.name), searchFormDTO.getFname()));
            }
            if (StringUtils.isNoneBlank(searchFormDTO.getFage()) && !searchFormDTO.getFage().isEmpty()) {
                searchFormDTO.setFage(StringUtils.deleteWhitespace(searchFormDTO.getFage()));
                predicates.add(criteriaBuilder.equal(root.get(StudentEntity.Fields.age), Integer.parseInt(searchFormDTO.getFage())));
            }
            if (StringUtils.isNoneBlank(searchFormDTO.getFdate()) && !searchFormDTO.getFdate().isEmpty()) {
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = originalFormat.parse(searchFormDTO.getFdate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMdd");
                searchFormDTO.setFdate(newFormat.format(date));
                predicates.add(criteriaBuilder.equal(root.get(StudentEntity.Fields.date), Integer.parseInt(searchFormDTO.getFdate())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
