package com.reitansora.apilogin.controller;


import com.reitansora.apilogin.entity.UserEntity;
import com.reitansora.apilogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/find")
    public ArrayList<UserEntity> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping(path = "/find/id/{id}")
    public Optional<UserEntity> getUserById(@PathVariable("id") long id){
        return this.userService.findById(id);
    }

    @GetMapping(path = "/find/email/{email}")
    public Optional<UserEntity> getUserByEmail(@PathVariable("email") String email){
        return this.userService.findByEmail(email);
    }

    @PostMapping(path = "/create")
    public boolean createUser(@RequestBody UserEntity user){
        try {
            this.userService.createUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PutMapping(path = "/edit/{id}")
    public boolean updateUser(@RequestBody UserEntity user, @PathVariable("id") long id){
        try {
            this.userService.updateUser(user,id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteUser(@PathVariable("id") long id){
        try {
            this.userService.deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
