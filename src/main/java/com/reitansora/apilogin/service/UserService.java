package com.reitansora.apilogin.service;

import com.reitansora.apilogin.entity.UserEntity;
import com.reitansora.apilogin.exception.EmailUsed;
import com.reitansora.apilogin.exception.UserNotFound;
import com.reitansora.apilogin.exception.ValidationFailed;
import com.reitansora.apilogin.repository.UserRepository;
import com.reitansora.apilogin.util.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    UserValidation userValidation;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userValidation = new UserValidation();
    }

    public ArrayList<UserEntity> getAllUsers() {
        return (ArrayList<UserEntity>) userRepository.findAll();
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(UserEntity user) throws EmailUsed, ValidationFailed {
        if(userRepository.findByEmail(user.getUser_email()).isEmpty()){
            if (userValidation.EmailValidator(user.getUser_email()) && userValidation.PasswordValidator(user.getUser_password())){
                user.setUser_password(encryptPassword(user.getUser_password()));
                userRepository.save(user);
            }else {
                throw new ValidationFailed();
            }
        }
        else{
            throw new EmailUsed(user.getUser_email());
        }
    }

    public void updateUser(UserEntity userRequest, String request_id) throws EmailUsed, UserNotFound, ValidationFailed {
        if (userRepository.findById(userRepository.findBy_id(request_id).get()).isPresent()){
            UserEntity userEntity = userRepository.findById(userRepository.findBy_id(request_id).get()).get();
            if (userRepository.findByEmail(userRequest.getUser_email()).isEmpty()) {
                if (userValidation.EmailValidator(userRequest.getUser_email()) && userValidation.PasswordValidator(userRequest.getUser_password())){
                    userEntity.setUser_nickname(userRequest.getUser_nickname());
                    userEntity.setUser_email(userRequest.getUser_email());
                    userRequest.setUser_password(encryptPassword(userRequest.getUser_password()));
                    userEntity.setUser_password(userRequest.getUser_password());

                    userRepository.saveAndFlush(userEntity);
                }else {
                    throw new ValidationFailed();
                }
            } else {
                if(userRepository.findBy_id(request_id).get() == userRepository.findByEmail(userRequest.getUser_email()).get().getId()){
                    if (userValidation.EmailValidator(userRequest.getUser_email()) && userValidation.PasswordValidator(userRequest.getUser_password())){
                        userEntity.setUser_nickname(userRequest.getUser_nickname());
                        userEntity.setUser_email(userRequest.getUser_email());
                        userRequest.setUser_password(encryptPassword(userRequest.getUser_password()));
                        userEntity.setUser_password(userRequest.getUser_password());

                        userRepository.saveAndFlush(userEntity);
                    }else {
                        throw new ValidationFailed();
                    }
                } else {
                    throw new EmailUsed(userRequest.getUser_email());
                }
            }
        } else {
            throw new UserNotFound(request_id);
        }
    }

    public void deleteUser(String id) throws UserNotFound {
        if (userRepository.findById(userRepository.findBy_id(id).get()).isPresent()){
            userRepository.deleteById(userRepository.findBy_id(id).get());
        }else{
            throw new UserNotFound(id);
        }
    }

    public String encryptPassword(String password) {
        return this.passwordEncoder.encode(password);
    }
}
