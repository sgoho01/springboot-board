package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.entity.Board;
import me.ghsong.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-08
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boardView(@PageableDefault Pageable pageable, Model model) {

        Page<Board> boardList = boardService.getBoardList(pageable);
        model.addAttribute("boardList", boardList);

        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                boardList.getTotalElements(), boardList.getTotalPages(), boardList.getSize(), boardList.getNumber(), boardList.getNumberOfElements());

        return "board";
    }

    @GetMapping("/form")
    public String boardForm(){
        return "form";
    }
}
