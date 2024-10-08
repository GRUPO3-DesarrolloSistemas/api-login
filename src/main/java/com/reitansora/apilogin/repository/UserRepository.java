package com.reitansora.apilogin.repository;

import com.reitansora.apilogin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(
            value = "SELECT * FROM capiplan.select_by_email(request_email := :email) ",
            nativeQuery = true
    )
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Query(
            value = "SELECT * FROM capiplan.select_by_id(request_id := :id) ",
            nativeQuery = true
    )
    Optional<Long> findBy_id(@Param("id") String id);

}
