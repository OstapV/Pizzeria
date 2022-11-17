package com.company.service;

import com.company.models.UserStatus;
import com.company.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusService {

    private final UserStatusRepository userStatusRepository;
    @Autowired
    public UserStatusService(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    public List<UserStatus> findAll() {return userStatusRepository.findAll();}
    public UserStatus getById(Long id) {return userStatusRepository.getById(id);}
    public UserStatus getByName(String name) {return userStatusRepository.findByName(name);}
    public void save(UserStatus userStatus) {userStatusRepository.save(userStatus);}
    public void deleteById(Long id) {userStatusRepository.deleteById(id);}
}
