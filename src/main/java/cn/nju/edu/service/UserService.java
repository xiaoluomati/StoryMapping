package cn.nju.edu.service;

import cn.nju.edu.entity.User;

import java.util.List;

public interface UserService {

    User FindNameAndPassword(String name, String password);

    void save(User user1);

    List<User> FindByName(String name);
}
