package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.Board;

import java.util.List;

public interface BoardService {
    List<Board> selectBoard();

    List<Board> selectBoardGrade(int grade);

    Board selectBoard(Long id);

    Board insetBoard(Board board);

    Board updateBoard(Board board);

    void upGood(Long id);

    void deleteBoard(Long id);

    int getTotalGood(String user_id);
}
