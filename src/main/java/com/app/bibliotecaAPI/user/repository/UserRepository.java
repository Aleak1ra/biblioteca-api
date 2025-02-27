package com.app.bibliotecaAPI.user.repository;

import com.app.bibliotecaAPI.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
