package me.ghsong.board.common;

import lombok.*;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-25
 * Copyright(©) 2019 by ATOSTUDY.
 */
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class ResultResponse {

    private boolean result;
    private String message;
}
