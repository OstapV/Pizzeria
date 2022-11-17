package com.company.service;

import com.company.models.Role;
import com.company.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {return roleRepository.findAll();}
    public Role getById(Long id) {return roleRepository.getById(id);}
    public Role getByName(String name) {return roleRepository.findByName(name);}
    public void save(Role role) {roleRepository.save(role);}
    public void deleteById(Long id) {roleRepository.deleteById(id);}
}
