package me.ghsong.board.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-08
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> getBoardList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
        pageable = PageRequest.of(page, 10, Sort.by("boardSeq").descending());
        return boardRepository.findAll(pageable);
    }

    public Board insertBoard(Board board){
        board.setCurrentTime();
        boardRepository.save(board);
        return board;
    }
}
