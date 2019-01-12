package cn.nju.edu.service;

import cn.nju.edu.vo.UserVo;

public interface UserService {

    //根据用户名和密码查找用户
    UserVo getUserByNameAndPassword(String name, String password);

    //查找指定名字的用户
    UserVo getUserByName(String name);

    //添加用户
    void addUser(UserVo userVo);

    //修改用户信息
    void updateUser(UserVo userVo);
}
