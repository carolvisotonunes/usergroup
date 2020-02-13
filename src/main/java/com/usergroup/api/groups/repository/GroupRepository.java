package com.usergroup.api.groups.repository;

import com.usergroup.api.groups.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
