package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.User;
import kr.hs.dgsw.board_back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User selectUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User loginUser(User user) {
        return userRepository.findByIdAndPw(user.getId(), user.getPw()).orElse(null);
    }

    @Override
    public User insetUser(User user) {
        if (userRepository.findById(user.getId()).isPresent())
            return null;

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.findById(user.getId()).map(found -> {
            found.setPw(Optional.ofNullable(user.getPw()).orElse(found.getPw()));
            found.setName(Optional.ofNullable(user.getName()).orElse(found.getName()));
            found.setGender(Optional.ofNullable(user.getGender()).orElse(found.getGender()));
            found.setAge(Optional.ofNullable(user.getAge()).orElse(found.getAge()));
            found.setProfile(Optional.ofNullable(user.getProfile()).orElse(found.getProfile()));
            return userRepository.save(found);
        }).orElse(null);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
