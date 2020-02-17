package com.usergroup.api.groups.service;

import com.usergroup.api.groups.model.Group;
import com.usergroup.api.groups.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public List<Group> allGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    public Group insert(Group user) {
        return groupRepository.save(user);
    }

    public Group getGroupById(Long id) {
        Group user = groupRepository.findById(id).get();
        return user;
    }

    public Group update(Group user, Long id) {

        Group groupUpdate = groupRepository.findById(id).get();
        if (groupUpdate != null) {
            Group db = groupUpdate;
            db.setName(user.getName());
            groupRepository.save(db);
            return db;
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
