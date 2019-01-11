package cn.nju.edu.service;

import cn.nju.edu.vo.UserVo;

import java.util.List;

public interface UserService {

    //根据用户名和密码查找用户
    UserVo getUserByNameAndPassword(String name, String password);

    //查找指定名字的用户
    List<UserVo> getUserListByName(String name);

    //添加用户
    void addUser(UserVo userVo);

}
