package cn.nju.edu.serviceImpl;

import cn.nju.edu.repository.UserRepository;
import cn.nju.edu.entity.User;
import cn.nju.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public User FindNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }
    public void save(User user1) {
        userRepository.save(user1);
    }
    public List<User> FindByName(String name) {
        return userRepository.findByName(name);
    }
}
