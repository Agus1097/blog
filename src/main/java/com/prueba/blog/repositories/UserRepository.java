package com.prueba.blog.repositories;

import com.prueba.blog.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByEmail(String email);

    UserModel findByEmail(String email);
}
