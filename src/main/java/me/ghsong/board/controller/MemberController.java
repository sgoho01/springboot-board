package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.repository.MemberRepository;
import me.ghsong.board.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping("/myinfo")
    public String myinfo(Principal principal){
//        log.debug("principal : {}", principal.toString());
        return "member/myinfo";
    }

}
