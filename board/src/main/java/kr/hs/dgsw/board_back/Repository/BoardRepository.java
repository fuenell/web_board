package kr.hs.dgsw.board_back.Repository;

import kr.hs.dgsw.board_back.Domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
