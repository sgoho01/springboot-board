package me.ghsong.board;

import me.ghsong.board.entity.Board;
import me.ghsong.board.entity.Comment;
import me.ghsong.board.entity.Member;
import me.ghsong.board.repository.BoardRepository;
import me.ghsong.board.repository.CommentRepository;
import me.ghsong.board.repository.MemberRepository;
import me.ghsong.board.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertTestData(MemberService memberService, BoardRepository boardRepository, CommentRepository commentRepository) {
        return args -> {
            Member admin = Member.builder()
                    .memberId("admin")
                    .memberPassword("admin")
                    .memberName("관리자")
                    .memberMobile("010-0000-0000")
                    .build();
            memberService.joinMember(admin);

            Member member = Member.builder()
                    .memberId("member")
                    .memberPassword("member")
                    .memberName("사용자")
                    .memberMobile("010-0000-0000")
                    .build();
            memberService.joinMember(member);

            IntStream.rangeClosed(1, 13).forEach(i -> {
                Member members = Member.builder()
                        .memberId("tester " + i)
                        .memberPassword("tester " + i)
                        .memberName("tester " + i)
                        .memberMobile("010-1234-" + i)
                        .build();

                memberService.joinMember(members);

                Board board = Board.builder()
                        .boardTitle("test" + i)
                        .boardContents("contents " + i)
                        .member(members)
                        .build();

                boardRepository.save(board);

                Comment comment = Comment.builder()
                        .commentContents("댓글 " + i)
                        .board(board)
                        .member(members)
                        .build();

                commentRepository.save(comment);
                commentRepository.save(comment);
            });



        };
    }

}
