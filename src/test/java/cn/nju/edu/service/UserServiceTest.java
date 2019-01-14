package cn.nju.edu.service;

import cn.nju.edu.StoryMapApplication;
import cn.nju.edu.vo.UserPswdVo;
import cn.nju.edu.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StoryMapApplication.class)
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void getUserByNameAndPassword() {
        UserVo testCase = new UserVo();
        testCase.setName("hujia");
        testCase.setPassword("654321");
        testCase.setNickname("小龙虾");
        userService.addUser(testCase);
        assertArrayEquals(
                new boolean[] {
                        //用户名，密码都正确
                        userService.getUserByNameAndPassword("hujia", "654321") != null,
                        //用户名正确，密码错误
                        userService.getUserByNameAndPassword("hujia","123") == null,
                        //密码为空
                        userService.getUserByNameAndPassword("hujia","") == null,
                        //用户名为空
                        userService.getUserByNameAndPassword("","") == null
                },
                new boolean[] {
                        true,
                        true,
                        true,
                        true
                }
        );
    }

    @Test
    public void getUserByName() {
        UserVo testCase = new UserVo();
        testCase.setName("hujia");
        testCase.setPassword("654321");
        testCase.setNickname("小龙虾");
        userService.addUser(testCase);
        assertArrayEquals(
                new Object[] {
                        //用户存在
                        userService.getUserByName("hujia") != null,
                        //用户不存在
                        userService.getUserByName("66") == null
                },
                new Object[] {
                        true,
                        true
                }
        );
    }

    @Test
    public void addUser() {
        UserVo userVo = new UserVo();
        userVo.setName("hujia");
        userVo.setPassword("654321");
        userVo.setNickname("小龙虾");
        userService.addUser(userVo);
        assertNotNull(userService.getUserByName("hujia"));
        assertTrue(userService.getUserByName("hujia").getId() > 0);
    }

    @Test
    public void updateUser() {
        //测试数据:{
        //      "name": "hujia",
        //      "password": "654321",
        //      "nickname": "小龙虾",
        //      "email": "null"
        //    }
        UserVo testCase = new UserVo();
        testCase.setName("hujia");
        testCase.setPassword("654321");
        testCase.setNickname("小龙虾");
        userService.addUser(testCase);

        UserVo userVo = userService.getUserByName("hujia");
        userVo.setNickname("xlx");
        userVo.setEmail("123@qq.com");
        userService.updateUser(userVo);
        assertArrayEquals(
                new Object[] {
                        userService.getUserByName("hujia").getId() > 0,
                        userService.getUserByName("hujia").getNickname(),
                        userService.getUserByName("hujia").getEmail()
                },
                new Object[] {
                        true,
                        "xlx",
                        "123@qq.com"
                });
    }

    @Test
    public void updatePassword() {
        UserVo testCase = new UserVo();
        testCase.setName("hujia");
        testCase.setPassword("old654");
        testCase.setNickname("小龙虾");
        userService.addUser(testCase);

        int id = userService.getUserByName("hujia").getId();

        //修改失败
        UserPswdVo userPswdVo1 = new UserPswdVo();
        userPswdVo1.setId(id);
        userPswdVo1.setOldPassword("old6");
        userPswdVo1.setNewPassword("new123");
        userService.updatePassword(userPswdVo1);
        assertEquals(userService.getUserByName("hujia").getPassword(), "old654");

        //修改成功
        UserPswdVo userPswdVo = new UserPswdVo();
        userPswdVo.setId(id);
        userPswdVo.setOldPassword("old654");
        userPswdVo.setNewPassword("new123");
        userService.updatePassword(userPswdVo);
        assertEquals(userService.getUserByName("hujia").getPassword(), "new123");
    }
}