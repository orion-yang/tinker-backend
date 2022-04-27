package com.tinkerBackend.tinker.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Liked implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "project_id"
    )
    private Project project;

    public Liked() {
    }

    public Liked(User user, Project project) {
        this.user = user;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
