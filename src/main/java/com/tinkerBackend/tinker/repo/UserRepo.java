package com.tinkerBackend.tinker.repo;

import com.tinkerBackend.tinker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    void deleteUserByFirstName(String firstName);
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}
