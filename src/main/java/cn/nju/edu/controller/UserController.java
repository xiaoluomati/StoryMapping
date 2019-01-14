package cn.nju.edu.controller;

import cn.nju.edu.service.UserService;
import cn.nju.edu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody UserVo userVo) {
        System.out.println(userVo);
        userService.addUser(userVo);
    }

    @RequestMapping("/{id}")
    public UserVo getUser(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateAccount(@PathVariable("id") int userId, @RequestBody UserVo userVo) {
        userVo.setId(userId);
        userService.updateUser(userVo);
    }
}
