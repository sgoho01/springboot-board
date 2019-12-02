package me.ghsong.board;

import lombok.RequiredArgsConstructor;
import me.ghsong.board.entity.Board;
import me.ghsong.board.entity.Comment;
import me.ghsong.board.entity.Member;
import me.ghsong.board.repository.BoardRepository;
import me.ghsong.board.repository.CommentRepository;
import me.ghsong.board.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-12-02
 */
@Component
@RequiredArgsConstructor
public class InitRunner implements CommandLineRunner {

    private final MemberService memberService;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public void run(String... args) {

        /**
         * 사용자 생성
         */
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

        Member user = Member.builder()
                .memberId("user")
                .memberPassword("user")
                .memberName("유저")
                .memberMobile("010-0000-0000")
                .build();
        memberService.joinMember(user);


        /**
         * 게시글 등록
         */
        Board board = Board.builder()
                .boardTitle("공지사항")
                .boardContents("github.com/sgoho01/springboot-board")
                .member(admin)
                .build();
        boardRepository.save(board);


        /**
         * 댓글 등록
         */
        Comment comment = Comment.builder()
                .commentContents(":)")
                .board(board)
                .member(member)
                .build();
        commentRepository.save(comment);

    }
}
