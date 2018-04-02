package com.zzq.controller;

import com.zzq.domain.User;
import com.zzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzq
 * @createTime 2018/3/9
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public User get(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public User delete(@PathVariable Long id){
        return userService.delete(id);
    }

    @GetMapping("/save")
    public User save(User user){
        if (user.getUid() != null){
            return userService.update(user);
        } else {
            return userService.save(user);
        }
    }

    @GetMapping("/list")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello redis";
    }
}
