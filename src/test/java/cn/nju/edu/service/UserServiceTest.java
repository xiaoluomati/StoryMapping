package cn.nju.edu.service;

import cn.nju.edu.StoryMapApplication;
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
    public void getUserByNameAndPasswordTest() {
        assertArrayEquals(
                new boolean[] {
                        //用户名，密码都正确
                        userService.getUserByNameAndPassword("张三", "123456") != null,
                        //用户名正确，密码错误
                        userService.getUserByNameAndPassword("张三","123") == null,
                        //密码为空
                        userService.getUserByNameAndPassword("张三","") == null,
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
    public void getUserListByNameTest() {
        assertArrayEquals(
                new Object[] {
                        //用户存在
                        userService.getUserListByName("张三").isEmpty(),
                        //用户不存在
                        userService.getUserListByName("王二").isEmpty()
                },
                new Object[] {
                        false,
                        true
                }
        );
    }

    @Test
    public void addUserTest() {
        UserVo userVo = new UserVo();
        userVo.setName("李四");
        userVo.setPassword("654321");
        //用户数据插入数据库，根据名字查找判断是否成功
        userService.addUser(userVo);
        assertNotNull(userService.getUserListByName("李四"));
    }
}