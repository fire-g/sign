package com.sign.sign.controller;

import com.sign.sign.entity.User;
import com.sign.sign.service.UserService;
import com.sign.sign.util.MailUtil;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    //账号密码登录
    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public Map<?, ?> login(@Param("email") String email,
                           @Param("password") String password,
                           HttpSession session) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User u = service.login(user);
        Map<String, Object> objectMap = new HashMap<>();
        if (u != null) {
            objectMap.put("code", 0);
            objectMap.put("message", "登录成功");
            objectMap.put("data", u);
            session.setAttribute("login", true);
            return objectMap;
        }
        u = service.findUser(email);
        if (u!=null){
            objectMap.put("code",4000);
            objectMap.put("message","未设置密码，无法用密码登录");
            return objectMap;
        }
        objectMap.put("code", 400);
        objectMap.put("message", "登录邮箱或密码错误");
        return objectMap;
    }

    //注册
    @PostMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
    public Map<?, ?> register(@Param("email") String email,
                              @Param("password") String password,
                              @Param("username") String username) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        boolean b = service.register(user);
        Map<String, Object> objectMap = new HashMap<>();
        if (b) {
            objectMap.put("code", 0);
            objectMap.put("message", "注册成功");
            return objectMap;
        }
        objectMap.put("code", 400);
        objectMap.put("message", "邮箱已经注册,请登录或更换邮箱");
        return objectMap;
    }

    //修改密码
    @PostMapping(value = "/modify-password",produces = {"application/json;charset=UTF-8"})
    public Map<?,?> modifyPassword(@Param("code")Integer code,
                                   @Param("email")String email,
                                   @Param("password")String password,
                                   HttpSession session){
        Map<String,Object> objectMap=new HashMap<>();
        User user = service.findUser(email);
        user.setPassword(password);
        Object c = session.getAttribute("code");
        //查验是否获取过验证码，或验证码是否过期
        if(c!=null){
            int a = (int)c;
            //检查验证码是否正确
            if (a==code){
                service.modifyPassword(user);
                objectMap.put("code",0);
                objectMap.put("message","修改成功");
                return objectMap;
            }
            objectMap.put("code",4003);
            objectMap.put("message","验证码错误");
            return objectMap;
        }
        return objectMap;
    }

    //邮箱验证码登录(注册)
    @PostMapping(value = "/code-login", produces = {"application/json;charset=UTF-8"})
    public Map<?, ?> codeLogin(@Param("email")String email,
                               @Param("code")Integer code,
                               HttpSession session) {
        User u = service.findUser(email);
        //邮箱未注册则自动注册,不设置密码
        if (u == null){
            u=new User();
            u.setEmail(email);
            service.register(u);
        }
        Map<String, Object> objectMap = new HashMap<>();
        Object c = session.getAttribute("code");
        //查验是否获取过验证码，或验证码是否过期
        if(c!=null){
            int a = (int)c;
            //检查验证码是否正确
            if (a==code){
                objectMap.put("code",0);
                objectMap.put("message","登录成功");
                session.setAttribute("login",true);
                return objectMap;
            }
            objectMap.put("code",4003);
            objectMap.put("message","验证码错误");
            return objectMap;
        }
        objectMap.put("code",4004);
        objectMap.put("message","请先获取验证码");
        return objectMap;
    }

    //获取验证码
    @GetMapping(value = "/code", produces = {"application/json;charset=UTF-8"})
    public Map<?, ?> getCode(@Param("email") String email,
                             HttpSession session) {
        int code = new Random().nextInt(8999) + 1000;
        MailUtil util = new MailUtil(email, code);
        util.run();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("code", 0);
        objectMap.put("message", "验证码发送成功");
        session.setAttribute("code", code);
        return objectMap;
    }

}
