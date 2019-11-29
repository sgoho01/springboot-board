package me.ghsong.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-18
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String getLoginView(){
        return "board/board";
    }

    @GetMapping("/denied")
    public String getDeniedView() {
        return "layout/denied";
    }

    @GetMapping("/empty")
    public String getEmptyBoardView() {
        return "layout/empty";
    }

}
