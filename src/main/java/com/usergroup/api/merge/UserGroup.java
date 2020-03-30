package com.usergroup.api.merge;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@IdClass(UserGroupId.class)
@Table(name = "user_group")
public class UserGroup {

    @Id
    private Long user_id;
    @Id
    private Long group_id;


}
