package kr.ac.kku.cs.wp.wsd.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import kr.ac.kku.cs.wp.wsd.model.Board;
import kr.ac.kku.cs.wp.wsd.service.BoardService;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 목록 조회
    @GetMapping("board")
    public String listBoards(Model model) {
        List<Board> boards = boardService.getAllBoards(); // DB에서 모든 게시글 조회
        model.addAttribute("boards", boards);
        return "BoardList"; // BoardList.jsp로 이동
    }

    // 게시글 작성 폼 보여주기
    @GetMapping("board/new")
    public String showCreateForm() {
        return "BoardForm"; // BoardForm.jsp로 이동
    }

    // 게시글 저장
    @PostMapping("board")
    public String createBoard(@RequestParam("title") String title, @RequestParam("content") String content) {
        boardService.createBoard(title, content); // 서비스 계층에서 DB에 저장
        return "redirect:/board"; // 게시글 목록으로 리다이렉트
    }

    // 게시글 수정 폼 보여주기
    @GetMapping("/board/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        try {
            Board board = boardService.getBoardById(id);
            if (board == null) {
                throw new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id);
            }
            model.addAttribute("board", board);
            return "editForm"; // 수정 페이지 렌더링
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage"; // 오류 페이지로 이동
        }
    }
    // 게시글 수정
    @PostMapping("/board/{id}/edit")
    public String editBoard(@PathVariable("id") int id,
                            @RequestParam("title") String title,
                            @RequestParam("content") String content) {
        boardService.updateBoard(id, title, content);
        return "redirect:/board";
    }

    @PostMapping("/board/{id}/delete")
    public String deleteBoard(@PathVariable int id) {
        try {
            boardService.deleteBoard(id); // 서비스 계층에서 삭제 처리
            return "redirect:/board"; // 목록으로 리다이렉트
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage"; // 오류 발생 시 에러 페이지로 이동
        }
    }
}

