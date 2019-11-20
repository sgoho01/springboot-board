package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-19
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Controller
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/admin")
    public String admin(){
        return "admin/admin";
    }

}
