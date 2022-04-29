package com.tinkerBackend.tinker.service;


import com.tinkerBackend.tinker.model.Liked;
import com.tinkerBackend.tinker.model.Project;
import com.tinkerBackend.tinker.model.User;
import com.tinkerBackend.tinker.repo.LikedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LikedService {
    private final LikedRepo likedRepo;
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public LikedService(LikedRepo likedRepo, ProjectService projectService, UserService userService) {
        this.likedRepo = likedRepo;
        this.projectService = projectService;
        this.userService = userService;
    }

    public List<Liked> findAllByUser(Long user_id) {
        User user = userService.findById(user_id);
        return likedRepo.findAllByUser(user);
    }

    public List<Liked> findAllByProject(Long project_id) {
        Project project = projectService.findById(project_id);
        return likedRepo.findAllByProject(project);
    }

    public Liked addLiked(Liked liked) {
        return likedRepo.save(liked);
    }

    public List<Liked> findAllLiked() {
        return likedRepo.findAll();
    }


    public ResponseEntity<?> deleteById(Long id) {
        likedRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAllByProject(Long project_id) {
        Project project = projectService.findById(project_id);
        likedRepo.deleteAllByProject(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
