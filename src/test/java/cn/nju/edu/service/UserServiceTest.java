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
    public void getUserByNameAndPassword() {
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
    public void getUserByName() {
        assertArrayEquals(
                new Object[] {
                        //用户存在
                        userService.getUserByName("张三") != null,
                        //用户不存在
                        userService.getUserByName("王二") == null
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
        userVo.setName("李四");
        userVo.setPassword("654321");
        //用户数据插入数据库，根据名字查找判断是否成功
        userService.addUser(userVo);
        System.out.println(userService.getUserByName("李四"));
        assertNotNull(userService.getUserByName("李四"));
    }

    @Test
    public void updateUser() {
        UserVo userVo = new UserVo();
        userVo.setId(1);
        userVo.setName("张三");
        userVo.setPassword("654321");
        userVo.setEmail("857672790@qq.com");
        userService.updateUser(userVo);
//        UserVo tmp = userService.getUserByName("张三");
//        System.out.println(tmp);
        assertEquals(userService.getUserByName("张三").getPassword(), "654321");
        assertEquals(userService.getUserByName("张三").getEmail(),"857672790@qq.com");
    }
}