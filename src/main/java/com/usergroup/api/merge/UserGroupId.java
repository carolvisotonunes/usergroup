package com.usergroup.api.merge;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserGroupId implements Serializable {

    private Long user_id;
    private Long group_id;


}
