package kr.ac.kku.cs.wp.wsd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kku.cs.wp.wsd.dao.BoardDAO;
import kr.ac.kku.cs.wp.wsd.model.Board;

@Service
@Transactional
public class CreateService {

    private final BoardDAO boardDAO;

    @Autowired
    public CreateService(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public void createBoard(String title, String content) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("내용은 비어 있을 수 없습니다.");
        }

        Board board = new Board(title, content);
        boardDAO.saveBoard(board);
    }
}
