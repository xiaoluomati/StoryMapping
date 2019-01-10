package cn.nju.edu.service;

import cn.nju.edu.StoryMapApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StoryMapApplication.class)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    UserService userService;

}