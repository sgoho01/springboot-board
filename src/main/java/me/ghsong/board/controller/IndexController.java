package me.ghsong.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-18
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String login(){
        return "board/board";
    }

    @GetMapping("/denied")
    public String denied() {
        return "layout/denied";
    }

}
