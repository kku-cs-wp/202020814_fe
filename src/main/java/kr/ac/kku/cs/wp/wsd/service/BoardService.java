package kr.ac.kku.cs.wp.wsd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kku.cs.wp.wsd.dao.BoardDAO;
import kr.ac.kku.cs.wp.wsd.model.Board;

@Service
public class BoardService {

    private final BoardDAO boardDAO;

    // 의존성 주입 (생성자 기반)
    @Autowired
    public BoardService(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    // 게시글 생성
    @Transactional
    public void createBoard(String title, String content) {
        validateInput(title, content); // 입력값 검증
        Board board = new Board(title, content);
        boardDAO.saveBoard(board); // DAO에서 데이터 저장
    }

    // 모든 게시글 조회
    @Transactional(readOnly = true)
    public List<Board> getAllBoards() {
        return boardDAO.getAllBoards(); // DAO에서 모든 게시글 조회
    }

    // ID로 게시글 조회
    @Transactional(readOnly = true)
    public Board getBoardById(int id) {
        Board board = boardDAO.getBoardById(id);
        if (board == null) {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id);
        }
        return board;
    }

    // 게시글 업데이트
    @Transactional
    public void updateBoard(int id, String title, String content) {
        validateInput(title, content); // 입력값 검증
        Board board = boardDAO.getBoardById(id);
        if (board == null) {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id);
        }
        board.setTitle(title);
        board.setContent(content);
        boardDAO.updateBoard(board); // DAO에서 데이터 업데이트
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(int id) {
        Board board = boardDAO.getBoardById(id);
        if (board == null) {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id);
        }
        boardDAO.deleteBoardById(id); // DAO 계층 호출
    }

    // 입력값 검증 메서드
    private void validateInput(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("내용은 비어 있을 수 없습니다.");
        }
    }
}

