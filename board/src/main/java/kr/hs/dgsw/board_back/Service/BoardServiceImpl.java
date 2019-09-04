package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.Board;
import kr.hs.dgsw.board_back.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<Board> selectBoard() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> selectBoardGrade(int grade) {
        List<Board> boards = boardRepository.findAll();
        for (int i = boards.size() - 1; i >= 0; i--) {
            if (boards.get(i).getGrade() != grade) {
                boards.remove(i);
            }
        }
        return boards;
    }

    @Override
    public Board selectBoard(Long id) {
        Optional<Board> opt = boardRepository.findById(id);
        // 조회수 증가
        if (opt.isPresent()) {
            Board board = opt.get();
            board.setHits(board.getHits() + 1);
            updateBoard(board);
            return board;
        }
        return null;
    }

    @Override
    public Board insetBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(Board board) {
        return boardRepository.findById(board.getId()).map(found -> {
            found.setGrade(Optional.ofNullable(board.getGrade()).orElse(found.getGrade()));
            found.setTitle(Optional.ofNullable(board.getTitle()).orElse(found.getTitle()));
            found.setContent(Optional.ofNullable(board.getContent()).orElse(found.getContent()));
            found.setHits(Optional.ofNullable(board.getHits()).orElse(found.getHits()));
            return boardRepository.save(found);
        }).orElse(null);
    }

    @Override
    public void upGood(Long id) {
        boardRepository.findById(id).map(found -> {
            found.setGood(found.getGood() + 1);
            return boardRepository.save(found);
        }).orElse(null);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public int getTotalGood(String user_id) {
        List<Board> boardList = boardRepository.findAll();
        int totalGood = 0;
        for (Board board : boardList) {
            if (board.getUser_id().equals(user_id))
                totalGood += board.getGood();
        }
        return totalGood;
    }
}
