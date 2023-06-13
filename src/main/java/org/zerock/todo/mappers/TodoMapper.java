package org.zerock.todo.mappers;

import java.util.List;

import org.zerock.todo.dto.PageRequestDTO;
import org.zerock.todo.dto.TodoDTO;

public interface TodoMapper {
    
    List<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    long listCount(PageRequestDTO pageRequestDTO);

}
