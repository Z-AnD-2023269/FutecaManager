package com.andersonlopez.futecaManager.service.IService;

import java.util.List;

import com.andersonlopez.futecaManager.models.User;

public interface IUserService {
    List<User> listUser();

    User getUser(Long id);

    User registrer(User user);

    boolean login(String username, String password);

}
