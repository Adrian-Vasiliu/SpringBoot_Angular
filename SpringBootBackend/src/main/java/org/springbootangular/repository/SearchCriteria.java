package org.springbootangular.repository;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCriteria {

    private String operation;

    private String key;

    private Object value;

}
