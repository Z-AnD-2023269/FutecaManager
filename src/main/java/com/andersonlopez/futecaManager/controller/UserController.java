package com.andersonlopez.futecaManager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.andersonlopez.futecaManager.DTO.UserLoginDTO;
import com.andersonlopez.futecaManager.DTO.UserRegisterDTO;
import com.andersonlopez.futecaManager.models.User;
import com.andersonlopez.futecaManager.service.CloudinaryService;
import com.andersonlopez.futecaManager.service.UserService;
import com.andersonlopez.futecaManager.utils.BCryptSecurity;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;


@RestController // @Controller @RequestBody
@RequestMapping("/futecaManager/v1/auth")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    BCryptSecurity bCryptSecurity;

    @GetMapping()
    public ResponseEntity<?> getMethodName() {
        Map<String, Object> res = new HashMap<>();
        try {
            return ResponseEntity.ok().body(userService.listUser());

        } catch (CannotCreateTransactionException err) {
            res.put("menssage", "Error al momento de conectarse a la DB");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        } catch (DataAccessException err) {
            res.put("message", "Error al momento de consultar a la base de datos");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        } catch (Exception err) {
            res.put("message", "Error general al obtener los datos");
            res.put("Error", err.getMessage());
            return ResponseEntity.status(503).body(res);
        }
    }
    // Data transfer object /

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestPart("profilePicture") MultipartFile profilePicture,
            @Valid @ModelAttribute UserRegisterDTO user,
            BindingResult result) {
        Map<String, Object> res = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            res.put("mesage", "Error en las validaciones por favor ingresa todos los campos");
            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }
        try {
            Map<String, Object> uploadResult = cloudinaryService.uploadProfilePicture(profilePicture, "profilesFuteca");
            String img = uploadResult.get("url").toString();
            Long id = null;
            User newUser = new User(
                    id,
                    user.getName(),
                    user.getSurname(),
                    user.getUsername(),
                    user.getEmail(),
                    bCryptSecurity.encodePassword(user.getPassword()),
                    img);
            userService.registrer(newUser);
            res.put("message", "Usuario recibido correctamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar el usuario, intente de nuevo más tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO user) {
        Map<String, Object> res = new HashMap<>();
        try{
            if(userService.login(user.getUsername(), user.getPassword())){
                res.put("message", "Usuario Logeado satisfactoriamente");
                return ResponseEntity.ok(res);
            }else{
                res.put("message", "Credenciales inavlidas");
                return ResponseEntity.status(401).body(res);
            }

        }catch(Exception err){
            res.put("message", "Error genereal al iniciars esion");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);

        }
    }
    
}
