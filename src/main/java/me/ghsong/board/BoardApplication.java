package me.ghsong.board;

import me.ghsong.board.entity.Board;
import me.ghsong.board.entity.Member;
import me.ghsong.board.repository.BoardRepository;
import me.ghsong.board.repository.MemberRepository;
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
    public CommandLineRunner insertTestData(MemberRepository memberRepository, BoardRepository boardRepository) {
        return args -> {
            IntStream.rangeClosed(1, 154).forEach(i -> {
                Member member = Member.builder()
                        .memberName("tester " + i)
                        .memberMobile("010-1234-" + i)
                        .build();

                memberRepository.save(member);

                Board board = Board.builder()
                        .boardTitle("test" + i)
                        .boardContents("contents " + i)
                        .member(member)
                        .build();

                boardRepository.save(board);
            });
        };
    }

}
