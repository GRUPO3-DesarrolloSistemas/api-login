package com.reitansora.apilogin.repository;

import com.reitansora.apilogin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(
            value = "SELECT * FROM users WHERE users.user_email LIKE %?1%",
            nativeQuery = true
    )
    Optional<UserEntity> findByEmail(String email);

}
