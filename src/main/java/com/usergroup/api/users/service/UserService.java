package com.usergroup.api.users.service;

import com.usergroup.api.exception.ObjectNotFoundException;
import com.usergroup.api.users.model.User;
import com.usergroup.api.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> allUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User insert(User user) {
        Assert.isNull(user.getId(), "It's not possible to insert, the User Id must be null");
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ObjectNotFoundException("User not found");
        }
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
