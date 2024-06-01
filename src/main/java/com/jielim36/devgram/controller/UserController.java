package com.jielim36.devgram.controller;

import com.jielim36.devgram.entity.User;
import com.jielim36.devgram.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User selectUserById(@PathVariable Long id) {
        // get user
        System.out.println("HOEDFHOSDHF");
        System.out.println("ID:" + id);
        User result = userService.selectUserById(id);
        System.out.println(result);
        return result;
    }

}
