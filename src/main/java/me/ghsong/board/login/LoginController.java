package me.ghsong.board.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-13
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String login(){
        return "login";
    }
}
