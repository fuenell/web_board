package kr.hs.dgsw.board_back.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String pw;

    private String name;

    private String gender;

    private int age;

    private String profile;
}
