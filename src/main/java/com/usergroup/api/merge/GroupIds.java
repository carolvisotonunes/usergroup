package com.usergroup.api.merge;

import java.util.List;

public class GroupIds {

    private List<Long> groups;

    public List<Long> getGroups() {
        return groups;
    }
    public GroupIds(){

    }

    public GroupIds(List<Long> groups) {
        this.groups = groups;
    }
}
