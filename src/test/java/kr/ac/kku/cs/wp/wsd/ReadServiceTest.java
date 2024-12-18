/*
 * 게시글 조회 테스트 코드입니다
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

class ReadServiceTest {

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBoardById_Success() {
        // Given
        Board board = new Board(1, "Test Title", "Test Content");
        when(boardDAO.getBoardById(1)).thenReturn(board);

        // When
        Board result = boardService.getBoardById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Content", result.getContent());
        verify(boardDAO, times(1)).getBoardById(1);
    }

    @Test
    void testGetBoardById_NotFound() {
        // Given
        when(boardDAO.getBoardById(1)).thenReturn(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> boardService.getBoardById(1));
        assertEquals("게시글을 찾을 수 없습니다. ID: 1", exception.getMessage());
        verify(boardDAO, times(1)).getBoardById(1);
    }
}
