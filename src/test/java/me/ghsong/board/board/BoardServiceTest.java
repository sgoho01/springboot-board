package me.ghsong.board.board;

import me.ghsong.board.entity.Board;
import me.ghsong.board.service.BoardService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-13
 * Copyright(©) 2019 by ATOSTUDY.
 */
@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void testGetBoardList() {
        final Page<Board> boardList = boardService.getBoardList(PageRequest.of(1, 10));
        boardList.stream().forEach(board -> System.out.println("★ : " + board.toString()));

        assertThat(boardList.getTotalPages(), is(Matchers.greaterThan(1)));
    }

    @Test
    void testInsertBoard(){
        Board board = Board.builder().boardTitle("title").boardContents("content").member(null).build();
        final Board board1 = boardService.insertBoard(board);
        System.out.println(board1.toString());
    }
}