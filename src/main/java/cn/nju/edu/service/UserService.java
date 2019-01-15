package cn.nju.edu.service;

import cn.nju.edu.vo.UserLoginVo;
import cn.nju.edu.vo.UserPswdVo;
import cn.nju.edu.vo.UserVo;

public interface UserService {

    //根据用户名和密码查找用户
    int getUserByNameAndPassword(UserLoginVo userLoginVo);

    //查找指定名字的用户
    UserVo getUserByName(String name);

    //查找指定id的用户
    UserVo getUserById(int id);

    //添加用户
    boolean addUser(UserVo userVo);

    //修改用户信息(nickname, email)
    boolean updateUser(UserVo userVo);

    //修改用户密码
    boolean updatePassword(UserPswdVo userPswdVo);
}
