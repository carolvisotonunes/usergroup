package com.usergroup.api.users.service;

import com.usergroup.api.users.model.User;
import com.usergroup.api.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> allUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    public User update(User user, Long id) {
        User userUpdate = userRepository.findById(id).get();
        if (userUpdate != null) {
            User db = userUpdate;
            db.setEmail(user.getEmail());
            db.setUserName(user.getUserName());
            userRepository.save(db);
            return db;
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
