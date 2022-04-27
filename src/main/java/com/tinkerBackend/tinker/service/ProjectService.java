package com.tinkerBackend.tinker.service;

import com.tinkerBackend.tinker.exception.ProjectNotFoundException;
import com.tinkerBackend.tinker.model.Project;
import com.tinkerBackend.tinker.model.User;
import com.tinkerBackend.tinker.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final UserService userService;

    @Autowired
    public ProjectService(ProjectRepo projectRepo, UserService userService) {
        this.projectRepo = projectRepo;
        this.userService = userService;
    }

    public List<Project> findAllByUser(Long user_id) {
        User user = userService.findById(user_id);
        return projectRepo.findAllByUser(user);
    }

    public Project addProject(Project project) {
        return projectRepo.save(project);
    }

    public List<Project> findAllProjects() {
        return projectRepo.findAll();
    }

    public void deleteById(Long id) {
        projectRepo.deleteById(id);
    }

    public Project findById(Long id) {
        return projectRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project by id: " + id + " was not found"));
    }

    public Project updateProject(Project project) {
        return projectRepo.save(project);
    }




}
