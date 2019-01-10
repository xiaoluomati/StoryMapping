package cn.nju.edu.service;

import cn.nju.edu.StoryMapApplication;
import cn.nju.edu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StoryMapApplication.class)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void findNameAndPassword() {
        assertArrayEquals(
                new boolean[] {
                        //用户名，密码都正确
                        userService.FindNameAndPassword("张三", "123456") != null,
                        //用户名正确，密码错误
                        userService.FindNameAndPassword("张三","123") == null,
                        //密码为空
                        userService.FindNameAndPassword("张三","") == null,
                        //用户名为空
                        userService.FindNameAndPassword("","") == null
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
    @Transactional
    public void save() {
        User user = new User("李四","654321");
        //用户数据插入数据库，查看id判断是否成功
        userService.save(user);
        System.out.println("UserID" + user.getId());
        assertTrue(user.getId() > 0);
    }

    @Test
    public void findByName() {
        assertArrayEquals(
                new Object[] {
                        //用户存在
                        userService.FindByName("张三").isEmpty(),
                        //用户不存在
                        userService.FindByName("王二").isEmpty()
                },
                new Object[] {
                        false,
                        true
                }
        );
    }
}