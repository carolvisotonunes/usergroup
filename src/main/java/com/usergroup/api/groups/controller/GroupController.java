package com.usergroup.api.groups.controller;

import com.usergroup.api.groups.model.Group;
import com.usergroup.api.groups.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/groups")
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping()
    public ResponseEntity<List<Group>> getAll() {
        List<Group> groups = groupService.allGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Group group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Group group) {

        Group createdGroup = groupService.insert(group);
        URI location = getUri(group.getId());
        return ResponseEntity.created(location).body(createdGroup);
    }


    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Group group) {

        Group groupUpdate = groupService.update(group, id);
        return groupUpdate != null ?
                ResponseEntity.ok(groupUpdate) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        groupService.delete(id);
        return  ResponseEntity.ok().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

}
