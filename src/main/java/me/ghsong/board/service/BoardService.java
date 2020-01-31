package me.ghsong.board.service;

import lombok.RequiredArgsConstructor;
import me.ghsong.board.entity.Board;
import me.ghsong.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-08
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> getBoardList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
        pageable = PageRequest.of(page, 10, Sort.by("boardSeq").descending());
        return boardRepository.findAllByStatus(pageable, "Y");
    }

    public Board insertBoard(Board board){
        board.onCreate();
        boardRepository.save(board);
        return board;
    }

    public Board updateBoard(Board board){
        board.onUpdate();
        boardRepository.save(board);
        return board;
    }

    public Board deleteBoard(Board board){
        board.onDelete();
        return boardRepository.save(board);
    }


}
