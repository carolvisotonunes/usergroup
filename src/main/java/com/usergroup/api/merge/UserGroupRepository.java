package com.usergroup.api.merge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId> {

    @Transactional
    @Modifying
    @Query(value = "delete from UserGroup u where u.user_id = ?1 and u.group_id = ?2")
    void deleteGroupFromUser(Long userId, Long groupId);
}
