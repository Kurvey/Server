package com.kurvey.u_life_kurly.user.repository;

import com.kurvey.u_life_kurly.user.dto.SimilarityCriteria;
import com.kurvey.u_life_kurly.user.entity.SelectionSet;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.entity.UserInfo;
import com.kurvey.u_life_kurly.user.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SimilaritySpecification implements Specification<UserInfo> {
    private final SimilarityCriteria similarityCriteria;
    private final SelectionSet selectionSet;
    private final User user;

    @Override
    public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.between(root.get("user").get("birthDay"),
                similarityCriteria.getAgeRangeFrom(), similarityCriteria.getAgeRangeTo()));

        if(similarityCriteria.getGender() != Gender.N)
            predicates.add(criteriaBuilder.equal(root.get("user").get("gender"), similarityCriteria.getGender()));

        predicates.add(criteriaBuilder.equal(root.get("numberOfFamily"), similarityCriteria.getNumberOfFamily()));

        if(!similarityCriteria.getHasBaby())
            predicates.add(criteriaBuilder.equal(root.get("hasBaby"), similarityCriteria.getHasBaby()));

        if(!similarityCriteria.getHasDog())
            predicates.add(criteriaBuilder.equal(root.get("hasDog"), similarityCriteria.getHasDog()));

        if(!similarityCriteria.getHasCat())
            predicates.add(criteriaBuilder.equal(root.get("hasCat"), similarityCriteria.getHasCat()));

        if(!similarityCriteria.getHasPlant())
            predicates.add(criteriaBuilder.equal(root.get("hasPlant"), similarityCriteria.getHasPlant()));

        predicates.add(criteriaBuilder.equal(root.get("selectionSet"), selectionSet));

        predicates.add(criteriaBuilder.notEqual(root.get("user"), user));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
