package com.muffincrunchy.challenge_wmb_sb_08.utils.specification;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(FilterCustomerRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }
            if (request.getIsMember() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isMember"), request.getIsMember()));
            }
            return query.where(criteriaBuilder.or(predicates.toArray(Predicate[]::new))).getRestriction();
        };
    }
}
