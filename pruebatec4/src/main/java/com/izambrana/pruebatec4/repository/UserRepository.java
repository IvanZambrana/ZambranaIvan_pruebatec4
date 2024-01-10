package com.izambrana.pruebatec4.repository;

import com.izambrana.pruebatec4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
