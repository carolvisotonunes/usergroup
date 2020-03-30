package com.usergroup.api.merge;

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

    @Autowired
    UserGroupRepository userGroupRepository;

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
            return null;
        }

    }

    public List<User> removeAllGroupsFromUsers(GroupIds groups) {

        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            for (User user : users) {
                for (Long groupId : groups.getGroups()) {
                    if (groupRepository.findById(groupId).isPresent()) {
                        User db = user;
                        db.getGroups().remove(groupRepository.findById(groupId).get());
                    }
                }
            }
            groupRepository.deleteAll();
            return users;
        } else {
            return null;
        }
    }

    public void deleteGroupFromUser(Long userId, Long groupId) {
        userGroupRepository.deleteGroupFromUser(userId, groupId);
    }

}