package me.ghsong.board.repository;

import me.ghsong.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-08
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByStatus(Pageable pageable, String status);

}
