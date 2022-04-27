package com.tinkerBackend.tinker.repo;

import com.tinkerBackend.tinker.model.Project;
import com.tinkerBackend.tinker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    List<Project> findAllByUser(User user);
}
