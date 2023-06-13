package org.zerock.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.todo.dto.PageRequestDTO;
import org.zerock.todo.dto.PageResponseDTO;
import org.zerock.todo.dto.TodoDTO;
import org.zerock.todo.mappers.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;

    @Override
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        List<TodoDTO> list = todoMapper.getList(pageRequestDTO);
        long total = todoMapper.listCount(pageRequestDTO);
        
        return PageResponseDTO.<TodoDTO>withAll()
                .list(list)
                .total(total)
                .build();
    }
    
}
