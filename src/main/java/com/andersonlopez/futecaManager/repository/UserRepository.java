package com.andersonlopez.futecaManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andersonlopez.futecaManager.models.User;

//Extender las entidades y el tipo de dato de su PK
//Evitar el uso de Querys en el código
public interface UserRepository extends JpaRepository<User, Long> {
    //Modificaciones para hacer búsquedas personalizadas
    public User findByUsername(String username);
}