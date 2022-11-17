package com.company.service;

import com.company.models.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {return userRepository.findAll();}
    public User getByLogin(String login){ return userRepository.findByLogin(login); }
    public User getById(Long id) {return userRepository.getById(id);}
    public void save(User user) {userRepository.save(user);}
    public void deleteById(Long id) {userRepository.deleteById(id);}

}
