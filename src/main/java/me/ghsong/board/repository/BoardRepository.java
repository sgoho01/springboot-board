package me.ghsong.board.repository;

import me.ghsong.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-08
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
