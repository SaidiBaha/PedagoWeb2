package tn.esprit.spring.services;

import tn.esprit.spring.entity.User;

import java.util.List;

public interface IUserService {

    public List<User> getAllUsers();
    public User getUserById(Long id);
    public User createUser(User user);
    public User updateUser(Long id, User user);
    public void deleteUser(Long id);
}
