package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BlogDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Blog;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog/home")
public class HomeController {
    @Autowired
    private BlogService blogService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }
    @PostMapping(value = "/create")
    public ResponseEntity<String> createBlog(@RequestBody BlogDto blogDto){
        Blog responseBlog = blogService.createBlog(blogDto);
        return ResponseEntity.ok("Success");
    }
    @PostMapping(value = "/registerAuthor")
    public ResponseEntity<String> registerAuthor(@RequestBody AuthorDto authorDto){
        Author author1 = blogService.registerActor(authorDto);
        return ResponseEntity.ok("author registered");
    }
}
