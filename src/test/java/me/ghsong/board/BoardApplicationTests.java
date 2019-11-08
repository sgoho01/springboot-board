package me.ghsong.board;

import me.ghsong.board.entity.Member;
import me.ghsong.board.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardApplicationTests {

    @Autowired
    MemberService memberService;

    @Test
    void contextLoads() {

        Member member = Member.builder().memberName("철수").memberMobile("010-1234-1234").build();
        member.setCurrentTime();

//        System.out.println(memberService.createMember(member).toString());

    }

}
