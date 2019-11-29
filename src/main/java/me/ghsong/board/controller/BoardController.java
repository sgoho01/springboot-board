package me.ghsong.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ghsong.board.common.ResultResponse;
import me.ghsong.board.config.CustomUserDetails;
import me.ghsong.board.dto.BoardDto;
import me.ghsong.board.entity.Board;
import me.ghsong.board.entity.Comment;
import me.ghsong.board.repository.BoardRepository;
import me.ghsong.board.repository.MemberRepository;
import me.ghsong.board.service.BoardService;
import me.ghsong.board.service.CommentService;
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

import java.util.Map;

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
    private final CommentService commentService;

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @GetMapping
    public String getBoardView() {
        log.info("::: getBoardView :::");
        return "board/board";
    }

    @GetMapping(value = {"/form", "/form/{boardSeq}"})
    public String getBoardFormView(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable(required = false) Long boardSeq, Model model) {
        log.info("::: getBoardFormView :::");
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
    public ResponseEntity getBoards(@PageableDefault Pageable pageable) {
        log.info("::: getBoards :::");

        Page<Board> boardList = boardService.getBoardList(pageable);
        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                boardList.getTotalElements(), boardList.getTotalPages(), boardList.getSize(), boardList.getNumber(), boardList.getNumberOfElements());
        return ResponseEntity.ok(boardList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity insertBoard(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody BoardDto boardDto) {
        log.info("::: insertBoard :::");
        log.debug("customUserDetails : {}, boardDto : {}", customUserDetails, boardDto.toString());

        ResultResponse resultResponse;

        Board board = Board.builder()
                .boardTitle(boardDto.getBoardTitle())
                .boardContents(boardDto.getBoardContents())
                .member(memberRepository.findById(customUserDetails.getMemberSeq()).orElse(null))
                .build();

        final Board insertBoard = boardService.insertBoard(board);

        if (insertBoard != null) {
            resultResponse = createResultResponse(true, "성공");
        } else {
            resultResponse = createResultResponse(false, "실패");
        }

        return ResponseEntity.ok(resultResponse);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateBoard(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody BoardDto boardDto) {
        log.info("::: updateBoard :::");
        log.debug("customUserDetails : {}, boardDto : {}", customUserDetails, boardDto.toString());

        ResultResponse resultResponse;

        final Board board = boardRepository.findById(boardDto.getBoardSeq()).orElse(null);
        if (board == null) {
            resultResponse = createResultResponse(false, "존재하지 않는 게시글");
        } else {
            board.setBoardTitle(boardDto.getBoardTitle());
            board.setBoardContents(boardDto.getBoardContents());

            final Board updateBoard = boardService.updateBoard(board);

            if (updateBoard != null) {
                resultResponse = createResultResponse(true, "성공");
            } else {
                resultResponse = createResultResponse(false, "실패");
            }
        }

        return ResponseEntity.ok(resultResponse);
    }

    @GetMapping("/{boardSeq}")
    public String getBoardDetailView(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                     @PathVariable Long boardSeq, Model model,
                                     @PageableDefault Pageable pageable) {
        log.info("::: getBoardDetailView :::");

        final Board board = boardRepository.findById(boardSeq).orElse(null);

        if (board == null) {
            return "redirect:/empty";
        }

        final Page<Comment> comments = commentService.getComments(pageable, boardSeq);
        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                comments.getTotalElements(), comments.getTotalPages(), comments.getSize(), comments.getNumber(), comments.getNumberOfElements());

        if (customUserDetails != null) {
            model.addAttribute("owener", board.getMember().getMemberSeq().equals(customUserDetails.getMemberSeq()));
        }
        model.addAttribute("boardSeq", board.getBoardSeq());
        model.addAttribute("boardTitle", board.getBoardTitle());
        model.addAttribute("boardContents", board.getBoardContents());
        model.addAttribute("boardComments", comments);
        return "board/view";
    }

    @DeleteMapping(value = "/{boardSeq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBoard(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long boardSeq) {
        log.info("::: deleteBoard :::");

        final Board one = boardRepository.getOne(boardSeq);

        if (!customUserDetails.getMemberSeq().equals(one.getMember().getMemberSeq())) {
            return ResponseEntity.ok(createResultResponse(false, "삭제할 수 없는 게시글입니다."));
        } else {
            // 삭제처리
            boardRepository.save(one);
            return ResponseEntity.ok(createResultResponse(true, "삭제 되었습니다."));
        }
    }



    /* ************************
                댓글
     ************************** */


    @PostMapping("/{boardSeq}/comments")
    public ResponseEntity insertBoardComment(@PathVariable Long boardSeq,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                             @RequestBody Map params) {
        log.info("::: insertBoardComment :::");
        log.debug("params : {}", params);

        if (customUserDetails == null) {
            return ResponseEntity.ok(createResultResponse(false, "회원만 가능"));
        }

        Comment comment = Comment.builder()
                .commentContents(params.get("commentContents").toString())
                .board(boardRepository.getOne(boardSeq))
                .member(memberRepository.getOne(customUserDetails.getMemberSeq()))
                .build();

        final Comment insertComment = commentService.insertComment(comment);

        if(insertComment != null){
            return ResponseEntity.ok(createResultResponse(true, "성공"));
        }else{
            return ResponseEntity.ok(createResultResponse(false, "실패"));
        }

    }

    @PutMapping("/{boardSeq}/comments/{commentSeq}")
    public ResponseEntity updateBoardComment(@PathVariable Long boardSeq, @PathVariable Long commentSeq) {
        log.info("::: updateBoardComment :::");


        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{boardSeq}/comments/{commentSeq}")
    public ResponseEntity deleteBoardComment(@PathVariable Long boardSeq, @PathVariable Long commentSeq) {
        log.info("::: deleteBoardComment :::");


        return ResponseEntity.ok(null);
    }
}
