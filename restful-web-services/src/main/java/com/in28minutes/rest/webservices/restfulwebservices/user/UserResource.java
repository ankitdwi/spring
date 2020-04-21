package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUser() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User retrievedUser = userDaoService.findOne(id);
        if (retrievedUser == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return retrievedUser;
    }

    @PostMapping(path = "/users")
    public User createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        return savedUser;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User retrievedUser = userDaoService.deleteById(id);
        if (retrievedUser == null) {
            throw new UserNotFoundException("id-" + id);
        }
    }
}
