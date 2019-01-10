package cn.nju.edu.service.impl;

import cn.nju.edu.dao.UserDao;
import cn.nju.edu.entity.User;
import cn.nju.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public User FindNameAndPassword(String name, String password) {
        return userDao.findByNameAndPassword(name, password);
    }
    public void save(User user1) {
        userDao.save(user1);
    }
    public List<User> FindByName(String name) {
        return userDao.findByName(name);
    }
}
