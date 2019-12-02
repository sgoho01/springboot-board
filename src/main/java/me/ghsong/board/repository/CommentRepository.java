package me.ghsong.board.repository;

import me.ghsong.board.entity.Board;
import me.ghsong.board.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-29
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByBoardAndAndStatus(Pageable pageable, Board board, String status);

}
