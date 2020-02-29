package com.usergroup.api.merge;

import com.usergroup.api.groups.repository.GroupRepository;
import com.usergroup.api.users.model.User;
import com.usergroup.api.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;


    public List<User> getUsersWithGroups() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User updateUserWithGroups(GroupIds groups, Long id) {
        User db = null;
        Optional<User> userUpdate = userRepository.findById(id);
        if (userUpdate.isPresent()) {
            for (Long groupId : groups.getGroups())
                if (groupRepository.findById(groupId).isPresent()) {
                    db = userUpdate.get();
                    db.getGroups().add(groupRepository.findById(groupId).get());
                    userRepository.save(db);
                }
            return db;
        } else {
            return null;
        }

    }

    public List<User> removeAllGroupsFromUsers(GroupIds groups) {

        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            for (User user : users) {
                for (Long groupId : groups.getGroups()) {
                    if (groupRepository.findById(groupId).isPresent()) {
                        User db = user;
                        db.getGroups().remove(groupRepository.findById(groupId).get());
                    }
                }
            }
            groupRepository.deleteAll();
            return users;
        } else {
            return null;
        }

    }

    public User removeGroupFromUser(Long idUser, Long idGroup) {
         EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("UserGroups");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(" delete\n" +
                "        from Drivers\n" +
                "        where DriverID in\n" +
                "        (\n" +
                "            select d.DriverID\n" +
                "            from Drivers d\n" +
                "            inner join CarDrivers cd\n" +
                "            on d.DriverID = cd.Driver\n" +
                "            inner join Cars c\n" +
                "            on c.CarID = cd.CarID\n" +
                "            where c.CarID = 1\n" +
                "        ) ");
        query.setParameter("employeeName", "Mike");
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        em.getTransaction().commit();
        em.close();

        return userRepository.findById(idUser).get();
    }
}
