package com.tinkerBackend.tinker.controller;

import com.tinkerBackend.tinker.model.Liked;
import com.tinkerBackend.tinker.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/liked")
public class LikedController {
    private final LikedService likedService;

    @Autowired
    public LikedController(LikedService likedService) {
        this.likedService = likedService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Liked>> getAllCards() {
        List<Liked> liked = likedService.findAllLiked();
        return new ResponseEntity<>(liked, HttpStatus.OK);
    }

    @GetMapping("/find/{user_id}")
    public ResponseEntity<List<Liked>> findAllByUserId(@PathVariable("user_id") Long id) {
        List<Liked> liked = likedService.findAllByUser(id);
        return new ResponseEntity<>(liked, HttpStatus.OK);
    }

    @GetMapping("/find/{project_id}")
    public ResponseEntity<List<Liked>> findAllByProjectId(@PathVariable("project_id") Long id) {
        List<Liked> liked = likedService.findAllByProject(id);
        return new ResponseEntity<>(liked, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        likedService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Liked> addCard(@RequestBody Liked liked) {
        Liked newLiked = likedService.addLiked(liked);
        return new ResponseEntity<>(newLiked, HttpStatus.CREATED);
    }


}
