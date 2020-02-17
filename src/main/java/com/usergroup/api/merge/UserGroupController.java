package com.usergroup.api.merge;

import com.usergroup.api.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/usersgroups")
public class UserGroupController {

    @Autowired
    UserGroupService userGroupService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userGroupService.getUsersWithGroups();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUserWithGroups(@PathVariable("id") Long id, @RequestBody GroupIds groups) {
        User user = userGroupService.updateUserWithGroups(groups, id);
        return user != null ?
                ResponseEntity.ok(user) :
                ResponseEntity.notFound().build();
    }


}
