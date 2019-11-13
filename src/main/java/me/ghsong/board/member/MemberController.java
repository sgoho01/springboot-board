package me.ghsong.board.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-07
 * Copyright(©) 2019 by ATOSTUDY.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


}
