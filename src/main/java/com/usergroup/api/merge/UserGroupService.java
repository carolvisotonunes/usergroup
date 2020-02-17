package com.usergroup.api.merge;

import com.usergroup.api.groups.model.Group;
import com.usergroup.api.groups.repository.GroupRepository;
import com.usergroup.api.users.model.User;
import com.usergroup.api.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGroupService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;


    public List<User> getUsersWithGroups() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User updateUserWithGroups(GroupIds groups, Long id) {
        User db = null;
        Optional<User> userUpdate = userRepository.findById(id);
        if (userUpdate.isPresent()) {
            for (Long groupId : groups.getGroups())
                if (groupRepository.findById(groupId).isPresent()) {
                    db = userUpdate.get();
                    db.getGroups().add(groupRepository.findById(groupId).get());
                    userRepository.save(db);
                }
            return db;
        } else {
            return db;
        }

    }
}
