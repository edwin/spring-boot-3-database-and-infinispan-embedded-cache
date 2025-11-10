package com.edw.controller;

import com.edw.bean.User;
import com.edw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 *  com.edw.controller.UserController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 10 Nov 2025 9:33
 */
@RestController
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @PostMapping(path = "/user")
    public ResponseEntity addUser(@RequestParam String name,
                                   @RequestParam Integer age,
                                   @RequestParam String address,
                                   @RequestParam String province) {
        return ResponseEntity.ok(userService.saveUser(new User(name, age, address, province)));
    }

    @PutMapping(path = "/user")
    public ResponseEntity updateUser(@RequestParam String name,
                                   @RequestParam Integer age,
                                   @RequestParam String address,
                                   @RequestParam String province) {
        return ResponseEntity.ok(userService.updateUser(new User(name, age, address, province)));
    }

    @DeleteMapping(path = "/user")
    public ResponseEntity deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
        return ResponseEntity.ok().build();
    }
}
