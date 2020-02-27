package com.usergroup.api.groups.service;

import com.usergroup.api.exception.ObjectNotFoundException;
import com.usergroup.api.groups.model.Group;
import com.usergroup.api.groups.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public List<Group> allGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    public Group insert(Group group) {
        Assert.isNull(group.getId(),"It's not possible to insert, the Group Id must be null");
        return groupRepository.save(group);
    }

    public Group getGroupById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isPresent()) {
            return group.get();
        } else {
            throw new ObjectNotFoundException("Group not found");
        }
    }

    public Group update(Group group, Long id) {

        Group groupUpdate = groupRepository.findById(id).get();
        if (groupUpdate != null) {
            Group db = groupUpdate;
            db.setName(group.getName());
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
