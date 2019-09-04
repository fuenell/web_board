package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.User;

public interface UserService {

    User selectUser(String id);

    User loginUser(User user);

    User insetUser(User user);

    User updateUser(User user);

    void deleteUser(String id);
}
