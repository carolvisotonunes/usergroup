package com.usergroup.api.groups.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usergroup.api.users.model.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(max = 250)
    private String name;

    @ManyToMany(mappedBy = "groups")
    @JsonBackReference
    private List<User> users = new ArrayList<>();


    @PreRemove
    private void removeGroupsFromUsers() {
        for (User u : users) {
            u.getGroups().remove(this);
        }
    }

}
