package cn.nju.edu.serviceImpl;

import cn.nju.edu.entity.User;
import cn.nju.edu.repository.UserRepository;
import cn.nju.edu.service.UserService;
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
    public UserVo getUserByNameAndPassword(String name, String password) {
        if (name == null || password == null) {
            return null;
        } else {
            User user = userRepository.findByNameAndPassword(name, password);
            if (user == null) {
                return null;
            } else {
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setName(user.getName());
            userVo.setPassword(user.getPassword());
            userVo.setEmail(user.getEmail());
            return userVo;
            }
        }
    }

    @Override
    @Transactional
    public List<UserVo> getUserListByName(String name) {
        List<User> users = userRepository.findByName(name);
        List<UserVo> userVos = new ArrayList<UserVo>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setName(user.getName());
            userVo.setPassword(user.getPassword());
            userVo.setEmail(user.getEmail());
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    @Transactional
    public void addUser(UserVo userVo) {
        User user = new User();
        user.setId(userVo.getId());
        user.setName(userVo.getName());
        user.setPassword(userVo.getPassword());
        user.setEmail(userVo.getEmail());
        userRepository.save(user);
    }
}
