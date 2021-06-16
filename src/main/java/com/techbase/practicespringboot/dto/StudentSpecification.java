package com.techbase.practicespringboot.dto;


import com.techbase.practicespringboot.entity.StudentEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {
    public Specification<StudentEntity> hasName(SearchFormDTO searchFormDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNoneBlank(searchFormDTO.getFname()) && !searchFormDTO.getFname().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get(StudentEntity.Fields.name), StringUtils.deleteWhitespace(searchFormDTO.getFname())));
            }
            if (StringUtils.isNoneBlank(searchFormDTO.getFage()) && !searchFormDTO.getFage().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get(StudentEntity.Fields.age), Integer.parseInt(searchFormDTO.getFage())));
            }
            if (StringUtils.isNoneBlank(searchFormDTO.getFdate()) && !searchFormDTO.getFdate().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get(StudentEntity.Fields.gender), StringUtils.deleteWhitespace(searchFormDTO.getFdate())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
