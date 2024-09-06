package com.andersonlopez.futecaManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonlopez.futecaManager.models.User;
import com.andersonlopez.futecaManager.repository.UserRepository;
import com.andersonlopez.futecaManager.service.IService.IUserService;
import com.andersonlopez.futecaManager.utils.BCryptSecurity;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptSecurity bCryptSecurity;
    
    @Override 
    public List<User> listUser(){
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override 
    public User registrer(User user){
        if(user.getPassword() != null){
            user.setPassword(bCryptSecurity.encodePassword(user.getPassword()));
        }
        return  userRepository.save(user);
    }

    @Override
    public boolean login(String username, String password){
        User user = userRepository.findByUsername(username);

        if(user == null || bCryptSecurity.checkPassword(password, username)){
            return true;
        }
        return false;
    }
}
