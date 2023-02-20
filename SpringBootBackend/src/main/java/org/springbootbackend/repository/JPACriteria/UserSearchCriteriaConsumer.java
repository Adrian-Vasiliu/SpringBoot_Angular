package org.springbootbackend.repository.JPACriteria;

import org.springbootbackend.repository.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

@Getter
@Setter
@AllArgsConstructor
public class UserSearchCriteriaConsumer implements Consumer<SearchCriteria> {

    private Predicate predicate;
    private CriteriaBuilder criteriaBuilder;
    private Root root;

    @Override
    public void accept(SearchCriteria param) {
        if (param.getOperation().equalsIgnoreCase(">")) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder
                    .greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase("<")) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(
                    root.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase(":")) {
            if (root.get(param.getKey()).getJavaType() == String.class) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                        root.get(param.getKey()), "%" + param.getValue() + "%"));
            } else {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                        root.get(param.getKey()), param.getValue()));
            }
        }
    }

}
