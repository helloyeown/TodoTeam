package org.zerock.todo.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.todo.dto.PageRequestDTO;
import org.zerock.todo.dto.PageResponseDTO;
import org.zerock.todo.dto.TodoDTO;

@Transactional
public interface TodoService {
    
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);

}
