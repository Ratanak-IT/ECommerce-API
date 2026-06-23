package org.example.datajpa.specification.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RequestDto {
    private List<SearchRequestDto> searchRequestDtoList;
    private GlobalOperation globalOperation;
    public static enum GlobalOperation {
        AND, OR
    }
}
