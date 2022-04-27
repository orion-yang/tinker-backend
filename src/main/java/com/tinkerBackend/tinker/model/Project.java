package com.tinkerBackend.tinker.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String project_name;
    private String create_date;
    private int like_counter;
    private String description;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public Project() {
    }

    public Project(String project_name, String create_date, int like_counter, String description, User user) {
        this.project_name = project_name;
        this.create_date = create_date;
        this.like_counter = like_counter;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getLike_counter() {
        return like_counter;
    }

    public void setLike_counter(int like_counter) {
        this.like_counter = like_counter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
