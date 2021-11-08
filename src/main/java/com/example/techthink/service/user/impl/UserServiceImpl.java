package com.example.techthink.service.user.impl;


import com.example.techthink.security.model.UserPrincipal;
import com.example.techthink.persistence.entity.user.User;
import com.example.techthink.persistence.repository.userSection.UserSectionRepository;
import com.example.techthink.persistence.repository.user.UserRepository;
import com.example.techthink.service.model.dto.user.UserDTO;
import com.example.techthink.service.role.RoleService;
import com.example.techthink.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserSectionRepository userSectionRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, UserSectionRepository userSectionRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userSectionRepository = userSectionRepository;
    }

    @Override
    public User addProfessor(UserDTO request) {
        User user = buildUser(request);
        User savedUser = userRepository.save(user);
        User addRolledUser = addRoleToUser(savedUser.getId(), 1L); //PROFESSOR
        return addRolledUser;
    }

    @Override
    public User update(Long id, UserDTO request) {
        User byId = userRepository.getById(id);
        User user = buildUser(byId, request);
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @Override
    public User uploadPicture(Long id, String profilePicURL) {
        User byId = userRepository.getById(id);
        byId.setProfilePictureURL(profilePicURL);
        User updated = userRepository.save(byId);
        return updated;
    }

    @Override
    public User loadByUsernameOrEmail(String term) {
        User user = userRepository.loadUserByUsernameOrEmail(term);
        return user;
    }

    @Override
    public User register(UserDTO request) {
        User user = buildUser(request);
        User savedUser = userRepository.save(user);
        User addRolledUser = addRoleToUser(savedUser.getId(), 2L);  //STUDENT
        return addRolledUser;
    }

    @Override
    public User readById(Long id) {
        User byId = userRepository.getById(id);
        return byId;
    }

    @Override
    public List<User> readAll() {
        List<User> all = userRepository.findAll();
        return all;
    }

    //first delete relationships that have user_id as a FK
    @Override
    public Boolean delete(Long id) {
        userSectionRepository.getAllUserSectionGivenUser(id).forEach(each -> userSectionRepository.deleteById(each));
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.loadUserByUsernameOrEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(userEntity);
    }

    private User addRoleToUser(Long userId, Long roleId) {
        User byId = userRepository.getById(userId);
        byId.getRoles().add(roleService.getRoleById(roleId));
        User saved = userRepository.save(byId);
        return saved;
    }

    private User buildUser(User user, UserDTO request) {
        user.setFirst_name(request.getFirstName());
        user.setLast_name(request.getLastName());
        user.setUsername(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setDescription(request.getDescription());
        user.setProfilePictureURL(request.getProfilePictureURL());
        return user;
    }

    private User buildUser(UserDTO request) {
        return buildUser(new User(), request);
    }
}
