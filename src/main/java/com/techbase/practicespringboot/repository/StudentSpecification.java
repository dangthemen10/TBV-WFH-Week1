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

            if(searchFormDTO.getFname() != null && searchFormDTO.getFname().size() > 0){
                List<Predicate> listName = new ArrayList<>();
                searchFormDTO.getFname().forEach(element -> {
                    if (StringUtils.isNoneBlank(element) && !element.isEmpty()) {
                        listName.add(criteriaBuilder.equal(root.get(StudentEntity.Fields.name), StringUtils.deleteWhitespace(element)));
                    }
                });
                predicates.add(criteriaBuilder.or(listName.toArray(new Predicate[0])));
            }

            if(searchFormDTO.getFage() != null && searchFormDTO.getFage().size() > 0){
                List<Predicate> listAge = new ArrayList<>();
                searchFormDTO.getFage().forEach(element -> {
                    if (StringUtils.isNoneBlank(element) && !element.isEmpty()) {
                        listAge.add(criteriaBuilder.equal(root.get(StudentEntity.Fields.age), Integer.parseInt(StringUtils.deleteWhitespace(element))));
                    }
                });
                predicates.add(criteriaBuilder.or(listAge.toArray(new Predicate[0])));
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
