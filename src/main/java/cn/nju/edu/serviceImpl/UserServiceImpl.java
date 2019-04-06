package cn.nju.edu.serviceImpl;

import cn.nju.edu.entity.User;
import cn.nju.edu.repository.UserRepository;
import cn.nju.edu.service.UserService;
import cn.nju.edu.vo.SimpleUserVo;
import cn.nju.edu.vo.UserLoginVo;
import cn.nju.edu.vo.UserPswdVo;
import cn.nju.edu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public int getUserByNameAndPassword(UserLoginVo userLoginVo) {
        String name = userLoginVo.getName();
        String password = userLoginVo.getPassword();
        User user = userRepository.findByName(name);
        if (user != null) {
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                return user.getId();
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public UserVo getUserByName(String name) {
        User user = userRepository.findByName(name);
        return getUserVo(user);
    }

    @Override
    @Transactional
    public UserVo getUserById(int id) {
        User user = userRepository.findById(id).orElse(new User());
        return getUserVo(user);
    }

    private UserVo getUserVo(User user) {
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setName(user.getName());
        userVo.setPassword(user.getPassword());
        userVo.setNickname(user.getNickname());
        userVo.setEmail(user.getEmail());
        return userVo;
    }

    @Override
    @Transactional
    public int addUser(UserVo userVo) {
        User user = new User();
        if (userVo.getName().isEmpty() || userVo.getPassword().isEmpty()) {
            return 0;
        }
        user.setName(userVo.getName());
        user.setPassword(userVo.getPassword());
        user.setNickname(userVo.getNickname());
        user.setEmail(userVo.getEmail());
        userRepository.save(user);
        int id = userRepository.findByName(user.getName()).getId();
        return id;
    }

    @Override
    @Transactional
    public boolean updateUser(UserVo userVo) {
        User user = new User();
        int id = userVo.getId();
        if (id == 0 || userVo.getNickname().isEmpty()) {
            return false;
        }
        user.setId(id);
        user.setName(userRepository.findById(id).orElse(new User()).getName());
        user.setPassword(userRepository.findById(id).orElse(new User()).getPassword());
        user.setNickname(userVo.getNickname());
        user.setEmail(userVo.getEmail());
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean updatePassword(UserPswdVo userPswdVo) {
        User user = new User();
        int id = userPswdVo.getId();
        String password = userRepository.findById(id).orElse(new User()).getPassword();
        if (password.equals(userPswdVo.getOldPassword())) {
            user.setId(id);
            user.setName(userRepository.findById(id).orElse(new User()).getName());
            user.setNickname(userRepository.findById(id).orElse(new User()).getNickname());
            user.setEmail(userRepository.findById(id).orElse(new User()).getEmail());
            user.setPassword(userPswdVo.getNewPassword());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<SimpleUserVo> listUser() {
        Iterable<User> all = userRepository.findAll();
        List<SimpleUserVo> users = new ArrayList<>();
        for (User user : all) {
            SimpleUserVo simpleUserVo = new SimpleUserVo();
            simpleUserVo.setId(user.getId());
            simpleUserVo.setName(user.getNickname());
            users.add(simpleUserVo);
        }
        return users;
    }

}
