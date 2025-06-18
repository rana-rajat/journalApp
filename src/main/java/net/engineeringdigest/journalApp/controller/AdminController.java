package net.engineeringdigest.journalApp.controller;


import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        log.info("Fetching all users");
        List<User> allUser = userService.getAllUser();
        if (!allUser.isEmpty()) {
            return new ResponseEntity<>(allUser, HttpStatus.OK);
        } else {
            log.warn("No users found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
        }

    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<String> createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin user created successfully");
    }

}
