package org.zerock.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    
    private Long tno;
    private String title;
    private String writer;
    private String content;
    private boolean complete;
    private String dueDate;
    
}
