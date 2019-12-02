package me.ghsong.board.service;

import lombok.RequiredArgsConstructor;
import me.ghsong.board.entity.Comment;
import me.ghsong.board.repository.BoardRepository;
import me.ghsong.board.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-29
 */
@Service
@RequiredArgsConstructor
public class CommentService{

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Page<Comment> getComments(Pageable pageable, Long boardSeq){
        int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
        pageable = PageRequest.of(page, 10, Sort.by("commentSeq").ascending());

        return commentRepository.findAllByBoardAndAndStatus(pageable, boardRepository.findById(boardSeq).get(), "Y");
    }

    public Comment getComment(Long commentSeq){
        return commentRepository.findById(commentSeq).orElse(null);
    }

    public Comment insertComment(Comment comment){
        comment.onCreate();
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment){
        comment.onUpdate();
        return commentRepository.save(comment);
    }

    public Comment deleteComment(Comment comment){
        comment.onDelete();
        return commentRepository.save(comment);
    }

}
