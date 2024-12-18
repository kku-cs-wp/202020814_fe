/*
 * 게시글 수정 테스트 코드입니다
 * -파일을 우클릭 후 junit test를 실행하세요
 */

package kr.ac.kku.cs.wp.wsd;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.ac.kku.cs.wp.wsd.dao.BoardDAO;
import kr.ac.kku.cs.wp.wsd.model.Board;
import kr.ac.kku.cs.wp.wsd.service.BoardService;

class UpdateServiceTest {

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateBoard_Success() {
        // Given
        Board existingBoard = new Board(1, "Old Title", "Old Content");
        when(boardDAO.getBoardById(1)).thenReturn(existingBoard);

        // When
        boardService.updateBoard(1, "New Title", "New Content");

        // Then
        verify(boardDAO).updateBoard(existingBoard);
        assertEquals("New Title", existingBoard.getTitle());
        assertEquals("New Content", existingBoard.getContent());
    }

    @Test
    void testUpdateBoard_BoardNotFound() {
        // Given
        when(boardDAO.getBoardById(1)).thenReturn(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> boardService.updateBoard(1, "New Title", "New Content"));
        assertEquals("게시글을 찾을 수 없습니다. ID: 1", exception.getMessage());
    }

    @Test
    void testUpdateBoard_EmptyTitle() {
        // Given
        Board existingBoard = new Board(1, "Old Title", "Old Content");
        when(boardDAO.getBoardById(1)).thenReturn(existingBoard);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> boardService.updateBoard(1, "", "New Content"));
        assertEquals("제목은 비어 있을 수 없습니다.", exception.getMessage());
    }

    @Test
    void testUpdateBoard_EmptyContent() {
        // Given
        Board existingBoard = new Board(1, "Old Title", "Old Content");
        when(boardDAO.getBoardById(1)).thenReturn(existingBoard);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> boardService.updateBoard(1, "New Title", ""));
        assertEquals("내용은 비어 있을 수 없습니다.", exception.getMessage());
    }
}
