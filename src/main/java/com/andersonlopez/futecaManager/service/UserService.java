package com.andersonlopez.futecaManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonlopez.futecaManager.models.User;
import com.andersonlopez.futecaManager.repository.UserRepository;
import com.andersonlopez.futecaManager.service.IService.IUserService;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;
    
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
        return  userRepository.save(user);
    }

    @Override
    public boolean login(String username, String password){
        return false;
    }
}
