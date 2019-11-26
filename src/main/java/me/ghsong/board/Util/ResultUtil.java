package me.ghsong.board.Util;

import me.ghsong.board.common.ResultResponse;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-25
 * Copyright(©) 2019 by ATOSTUDY.
 */
public class ResultUtil {

    public static ResultResponse createResultResponse(boolean result, String message) {
        return ResultResponse.builder()
                .result(result)
                .message(message)
                .build();
    }
}
