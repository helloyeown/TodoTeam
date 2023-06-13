package org.zerock.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.todo.dto.PageRequestDTO;
import org.zerock.todo.dto.PageResponseDTO;
import org.zerock.todo.dto.TodoDTO;
import org.zerock.todo.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/todo/")
public class TodoController {

    private final TodoService todoService;

    

    @GetMapping("list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("get | list");
        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.list(pageRequestDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
    }

    @GetMapping("read/{tno}")
    public String read(@PathVariable("tno") Long tno,
                    PageRequestDTO pageRequestDTO){
        log.info("read..................");
        log.info(tno);

        return "todo/read";
    }

}
