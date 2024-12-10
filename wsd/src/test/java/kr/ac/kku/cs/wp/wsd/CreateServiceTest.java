/*
 * 게시글 생성 테스트 코드입니다
 * -파일을 우클릭 후 junit test를 실행하세요
 */

package kr.ac.kku.cs.wp.wsd;

import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.ac.kku.cs.wp.wsd.dao.BoardDAO;

class CreateServiceTest {

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    private CreateService createService; // CreateService가 정확히 초기화됨

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
    }

    @Test
    void testCreateBoard() {
        createService.createBoard("Test Title", "Test Content"); // 메서드 호출
        verify(boardDAO).saveBoard(any());
    }
}
