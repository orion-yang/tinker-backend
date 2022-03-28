package com.tinkerBackend.tinker.repo;

import com.tinkerBackend.tinker.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUserName(String userName);
    Optional<Admin> findByEmail(String email);
}
