/*
 * 게시글 삭제 테스트 코드입니다
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

class DeleteServiceTest {

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteBoard_Success() {
        // Given
        Board existingBoard = new Board(1, "Test Title", "Test Content");
        when(boardDAO.getBoardById(1)).thenReturn(existingBoard);

        // When
        boardService.deleteBoard(1);

        // Then
        verify(boardDAO).deleteBoardById(1);
    }

    @Test
    void testDeleteBoard_BoardNotFound() {
        // Given
        when(boardDAO.getBoardById(1)).thenReturn(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> boardService.deleteBoard(1));
        assertEquals("게시글을 찾을 수 없습니다. ID: 1", exception.getMessage());
    }
}
