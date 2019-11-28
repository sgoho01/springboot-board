package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.common.ResultResponse;
import me.ghsong.board.config.CustomUserDetails;
import me.ghsong.board.dto.BoardDto;
import me.ghsong.board.entity.Board;
import me.ghsong.board.repository.BoardRepository;
import me.ghsong.board.repository.MemberRepository;
import me.ghsong.board.service.BoardService;
import me.ghsong.board.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static me.ghsong.board.Util.ResultUtil.createResultResponse;

/**
 * @author : Song.gunho
 * <p>
 * Date: 2019-11-08
 */
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @GetMapping
    public String boardPage() {
        log.debug("::: boardPage :::");
        return "board/board";
    }

    @GetMapping(value = {"/form", "/form/{boardSeq}"})
    public String boardForm(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable(required = false) Long boardSeq, Model model) {
        log.debug("::: boardForm :::");
        log.debug("boardSeq : {}", boardSeq);

        if (customUserDetails == null) {
            return "redirect:/login";
        }

        if (boardSeq != null) {
            final Board board = boardRepository.findById(boardSeq).orElse(null);
            if (board != null) {
                model.addAttribute("boardSeq", board.getBoardSeq());
                model.addAttribute("boardTitle", board.getBoardTitle());
                model.addAttribute("boardContents", board.getBoardContents());
            }
        }

        return "board/form";
    }

    @GetMapping("/lists")
    @ResponseBody
    public ResponseEntity boardLists(@PageableDefault Pageable pageable) {
        log.debug("::: boardLists :::");
        Page<Board> boardList = boardService.getBoardList(pageable);
        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                boardList.getTotalElements(), boardList.getTotalPages(), boardList.getSize(), boardList.getNumber(), boardList.getNumberOfElements());
        return ResponseEntity.ok(boardList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity boardInsert(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody BoardDto boardDto) {
        log.debug("::: boardInsert :::");
        log.info("customUserDetails : {}, boardDto : {}", customUserDetails, boardDto.toString());

        ResultResponse resultResponse;

        Board board = Board.builder()
                .boardTitle(boardDto.getBoardTitle())
                .boardContents(boardDto.getBoardContents())
                .member(memberRepository.findById(customUserDetails.getMemberSeq()).orElse(null))
                .build();

        final Board insertedBoard = boardService.insertBoard(board);

        if (insertedBoard != null) {
            resultResponse = createResultResponse(true, "성공");
        } else {
            resultResponse = createResultResponse(false, "실패");
        }

        return ResponseEntity.ok(resultResponse);
    }


    @GetMapping("/{boardSeq}")
    public String boardDetailView(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long boardSeq, Model model) {
        log.debug("::: boardDetailView :::");

        final Board board = boardRepository.findById(boardSeq).orElse(null);

        if (board == null) {
            return "layout/empty";
        }

        if (customUserDetails != null) {
            model.addAttribute("owener", board.getMember().getMemberSeq().equals(customUserDetails.getMemberSeq()));
        }
        model.addAttribute("boardSeq", board.getBoardSeq());
        model.addAttribute("boardTitle", board.getBoardTitle());
        model.addAttribute("boardContents", board.getBoardContents());
        return "board/view";
    }

    @DeleteMapping(value = "/{boardSeq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity boardDelete(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long boardSeq) {
        log.debug("::: boardDelete :::");

        final Board one = boardRepository.getOne(boardSeq);
        one.setStatus("N");

        if (!customUserDetails.getMemberSeq().equals(one.getMember().getMemberSeq())) {
            return ResponseEntity.ok(createResultResponse(false, "삭제할 수 없는 게시글입니다."));
        } else {
            // 삭제처리
            boardRepository.save(one);
            return ResponseEntity.ok(createResultResponse(true, "삭제 되었습니다."));
        }
    }

}
