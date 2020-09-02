package com.htsc.controller;

import com.htsc.dao.UserDao;
import com.htsc.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private UserDao userDao;

    //index页面
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //登录页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/checklogin")
    public String checklogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ": " + password);
        if (userDao.findByUsername(username) != null) {
            if (userDao.findByUsernameAndPassword(username, password) != null) {
                return "index";
            } else {
                return "loginfailure";
            }
        } else {
            return "loginnouser";
        }

    }

    //登陆失败-密码出错
    @RequestMapping("/loginfailure")
    public String loginfailure() {
        return "loginfailure";
    }

    //登陆失败-账号不存在
    @RequestMapping("/loginnouser")
    public String loginnouser() {
        return "loginnouser";
    }

    //注册页面
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    //重新注册页面
    @RequestMapping("/registerfailure")
    public String registerfailure() {
        return "registerfailure";
    }

    //注册方法
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (userDao.findByUsername(username) != null) {
            return "registerfailure";
        }

        if (password.equals(password2)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userDao.save(userEntity);
            return "index";
        } else {
            return "register";
        }


    }

}
