package com.andersonlopez.futecaManager.utils;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class BCryptSecurity {

    public String encodePassword(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
    
    public boolean checkPassword(String password, String hashePassword){
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashePassword);
        return result.verified;
    }
}
