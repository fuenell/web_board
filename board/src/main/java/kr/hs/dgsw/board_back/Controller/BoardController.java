package kr.hs.dgsw.board_back.Controller;

import kr.hs.dgsw.board_back.Domain.Board;
import kr.hs.dgsw.board_back.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/select")
    public List<Board> selectBoard() {
        return boardService.selectBoard();
    }

    @GetMapping("/select/{grade}")
    public List<Board> selectBoardGrade(@PathVariable int grade) {
        return boardService.selectBoardGrade(grade);
    }

    @GetMapping("/get/{id}")
    public Board selectBoard(@PathVariable Long id) {
        return boardService.selectBoard(id);
    }

    @PostMapping("/insert")
    public Board insetBoard(@RequestBody Board board) {
        return boardService.insetBoard(board);
    }

    @PutMapping("/update")
    public Board updateBoard(@RequestBody Board board) {
        return boardService.updateBoard(board);
    }

    @PutMapping("/like/{id}")
    public void UpLike(@PathVariable Long id) {
        boardService.upGood(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }

    @GetMapping("/totalGood/{user_id}")
    public int selectBoard(@PathVariable String user_id) {
        return boardService.getTotalGood(user_id);
    }

}
