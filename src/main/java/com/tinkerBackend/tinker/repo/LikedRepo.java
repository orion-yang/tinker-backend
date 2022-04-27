package com.tinkerBackend.tinker.repo;

import com.tinkerBackend.tinker.model.Liked;
import com.tinkerBackend.tinker.model.Project;
import com.tinkerBackend.tinker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedRepo extends JpaRepository<Liked, Long> {
    List<Liked> findAllByUser(User user);

    List<Liked> findAllByProject(Project project);
}
