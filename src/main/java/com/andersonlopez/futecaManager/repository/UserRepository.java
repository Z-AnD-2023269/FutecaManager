package com.andersonlopez.futecaManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andersonlopez.futecaManager.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
