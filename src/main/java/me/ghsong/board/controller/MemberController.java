package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.config.security.CustomUserDetails;
import me.ghsong.board.entity.Member;
import me.ghsong.board.repository.MemberRepository;
import me.ghsong.board.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getMemberInfoView(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        log.info("::: getMemberInfoView :::");

        if(customUserDetails == null){
            return "layout/denied";
        }

        final Member member = memberRepository.findById(customUserDetails.getMemberSeq()).orElse(null);

        model.addAttribute("member", member);
        return "member/myinfo";
    }

}
