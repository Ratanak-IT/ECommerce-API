package org.example.datajpa.specification.dto;

import jdk.dynalink.Operation;
import lombok.Getter;

@Getter

public class SearchRequestDto {
    String column;
    String value;
    Operation operation;
    String joinTable;

    public enum Operation {
        EQUAL,
        NOT_EQUAL,
        GREATER_THAN,
        LESS_THAN,
        GREATER_THAN_OR_EQUAL,
        LESS_THAN_OR_EQUAL,
        LIKE,
        BETWEEN,
        IN,
        IS_NULL,
        IS_NOT_NULL

    }
}
