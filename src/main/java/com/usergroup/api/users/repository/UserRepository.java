package com.usergroup.api.users.repository;

import com.usergroup.api.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
