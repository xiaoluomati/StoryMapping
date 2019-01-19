package cn.nju.edu.controller;

import cn.nju.edu.service.UserService;
import cn.nju.edu.vo.UserLoginVo;
import cn.nju.edu.vo.UserPswdVo;
import cn.nju.edu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Integer> login(@RequestBody UserVo userVo) {
        int id  = userService.addUser(userVo);
        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Integer> register(@RequestBody UserLoginVo userLoginVo) {
        int id = userService.getUserByNameAndPassword(userLoginVo);
        if (id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<UserVo> getUser(@PathVariable("id") int userId) {
        UserVo userVo = userService.getUserById(userId);
        if (userVo.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(userVo, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateAccount(@PathVariable("id") int userId, @RequestBody UserVo userVo) {
        userVo.setId(userId);
        boolean flag = userService.updateUser(userVo);
        if (flag) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/password/{id}", method = RequestMethod.PUT)
    public ResponseEntity updatePassword(@PathVariable("id") int userId, @RequestBody UserPswdVo userPswdVo) {
        boolean flag = userService.updatePassword(userPswdVo);
        if (flag) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
