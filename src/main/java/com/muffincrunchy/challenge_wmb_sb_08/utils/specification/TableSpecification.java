package com.muffincrunchy.challenge_wmb_sb_08.utils.specification;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Menu;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Table;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TableSpecification {
    public static Specification<Table> getSpecification(FilterTableRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }
            return query.where(criteriaBuilder.or(predicates.toArray(Predicate[]::new))).getRestriction();
        };
    }
}
