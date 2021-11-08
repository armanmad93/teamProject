package com.example.techthink.service.role.impl;

import com.example.techthink.persistence.entity.role.Role;
import com.example.techthink.persistence.repository.role.RoleRepository;
import com.example.techthink.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

//    public Role findRoleByName(String name){
//        return roleRepository.findByName(name);
//    }

    @Override
    public Role getRoleById(Long id){
        return roleRepository.getById(id);
    }
}
