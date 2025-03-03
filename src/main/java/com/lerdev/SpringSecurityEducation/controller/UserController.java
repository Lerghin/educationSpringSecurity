package com.lerdev.SpringSecurityEducation.controller;


import com.lerdev.SpringSecurityEducation.model.Role;
import com.lerdev.SpringSecurityEducation.model.UserSec;
import com.lerdev.SpringSecurityEducation.service.IRoleService;
import com.lerdev.SpringSecurityEducation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

   @Autowired
   private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<UserSec>> getAllUsers() {
        List<UserSec> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id) {
        Optional<UserSec> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec userSec) {

        Set<Role> roleList = new HashSet<Role>();
        Role readRole;

        //we encrypt the password
        userSec.setPassword(userService.encriptPassword(userSec.getPassword()));

        // recovery the permission/s by its ID
        for (Role role : userSec.getRolesList()){
            readRole = roleService.findById(role.getId()).orElse(null);
            if (readRole != null) {
                //if found, save in the list
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()) {
            userSec.setRolesList(roleList);

            UserSec newUser = userService.save(userSec);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }


}
