package cn.nju.edu.controller;

import cn.nju.edu.entity.User;
import cn.nju.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/front/*")
public class UserController {
    @Autowired
    UserService userServaiceImpl;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 去注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 执行注册 成功后登录页面 否则调回注册页面
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/doregister")
    public String register(HttpServletRequest request, User user) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password.equals(password2)) {
            if (registerUser(name)) {
                User user1 = new User();
                user1.setName(name);
                user1.setPassword(password);
                userServaiceImpl.save(user1);
                return "login";
            } else {
                return "register";
            }
        } else {
            return "register";
        }
    }

    public Boolean registerUser(String name) {
        return userServaiceImpl.FindByName(name).isEmpty();
    }

    /**
     * 去登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 执行登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/dologin")
    public String login(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = userServaiceImpl.FindNameAndPassword(name, password);
        if (user != null) {
            return "success";
        } else {
            return "login";
        }
    }
}
