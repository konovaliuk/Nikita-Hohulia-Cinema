package com.nikitahohulia.Cinema.services;

import com.nikitahohulia.Cinema.dao.RoleRepository;
import com.nikitahohulia.Cinema.dao.UserRepository;
import com.nikitahohulia.Cinema.dao.UserRoleRepository;
import com.nikitahohulia.Cinema.entities.Role;
import com.nikitahohulia.Cinema.entities.User;
import com.nikitahohulia.Cinema.entities.UserRole;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }


    public User signInUser(User theUser) throws ServiceException {

        User tryUser;
        tryUser = userRepository.getUserByEmail(theUser.getEmail()).orElse(null);

        if (tryUser == null)
            throw new ServiceException("Username with " + theUser.getEmail() + " doesn't exist.");

        if (!passwordEncoder.matches(theUser.getPassword(), tryUser.getPassword())) {
            throw new ServiceException("Invalid password.");
        }

        return tryUser;
    }

   public List<UserRole> getRoles(User user) throws ServiceException {
        List <UserRole> roles = userRoleRepository.findAllById(user.getId());
        return roles;
    }

    @Transactional
    public User singUpUser(User user) throws ServiceException, ServiceException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        Role role = roleRepository.findById(1).get();
        userRoleRepository.save(new UserRole(user.getId(), user, role));
        return user;
    }


}
