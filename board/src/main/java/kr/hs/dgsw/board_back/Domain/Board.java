package kr.hs.dgsw.board_back.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_id;

    private int grade;

    private String title;

    private String content;

    @ColumnDefault("0")
    private int good;

    private String profile;

    @ColumnDefault("0")
    private int hits;

    @CreationTimestamp
    private LocalDateTime created;
}
