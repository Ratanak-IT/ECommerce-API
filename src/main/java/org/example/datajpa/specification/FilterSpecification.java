package org.example.datajpa.specification;

import jakarta.persistence.criteria.Predicate;
import org.example.datajpa.specification.dto.RequestDto;
import org.example.datajpa.specification.dto.SearchRequestDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterSpecification<T> {
    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtoList, RequestDto.GlobalOperation globalOperation) {
     return (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchRequestDto requestDto : searchRequestDtoList){
            switch (requestDto.getOperation()){
                case EQUAL ->{
                    Predicate equal =  criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                    predicates.add(equal);
                }
                case LIKE -> {
                    Predicate like = criteriaBuilder.like(root.get(requestDto.getColumn()), requestDto.getValue());
                    predicates.add(like);
                }
                case GREATER_THAN -> {
                    Predicate greaterThan = criteriaBuilder.greaterThan(root.get(requestDto.getColumn()), requestDto.getValue());
                    predicates.add(greaterThan);
                }
                case BETWEEN -> {
                    String[] values = requestDto.getValue().split(",");
                    Predicate between = criteriaBuilder.between(root.get(requestDto.getColumn()), values[0], values[1]);
                }
                case LESS_THAN -> {
                    Predicate lessThan =criteriaBuilder.lessThan(root.get(requestDto.getColumn()), requestDto.getValue());
                    predicates.add(lessThan);
                }
                case NOT_EQUAL -> {
                    Predicate notEqual = criteriaBuilder.notEqual(root.get(requestDto.getColumn()), requestDto.getValue());
                    predicates.add(notEqual);
                }
                case IN -> {
                    String[] values = requestDto.getValue().split(",");
                    Predicate in = root.get(requestDto.getColumn()).in(values);
                    predicates.add(in);
                }
                case IS_NULL -> {
                    Predicate isNull = criteriaBuilder.isNull(root.get(requestDto.getColumn()));
                    predicates.add(isNull);
                }
                case LESS_THAN_OR_EQUAL -> {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(requestDto.getColumn()), requestDto.getValue()));
                }
                case IS_NOT_NULL -> {
                    Predicate isNotNull = criteriaBuilder.isNotNull(root.get(requestDto.getColumn()));
                    predicates.add(isNotNull);
                }
                case GREATER_THAN_OR_EQUAL -> {
                    Predicate greaterThanOrEqualTo = criteriaBuilder.greaterThanOrEqualTo(root.get(requestDto.getColumn()), requestDto.getValue());
                    predicates.add(greaterThanOrEqualTo);
                }
                default -> {
                    throw new RuntimeException("Unexpected Value.");
                }
            }
        }
        if (globalOperation.equals(RequestDto.GlobalOperation.AND)){
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }else{
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        }
     };

    }
}
