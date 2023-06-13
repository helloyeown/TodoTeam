package org.zerock.todo.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

// @AllArgsConstructor
@Data
public class PageResponseDTO<E> {

    private List<E> list;
    private long total;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> list, long total){
        this.list = list;
        this.total = total;
    }
    
}
