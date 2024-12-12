package com.example.demo.controller;

import com.example.demo.annotation.Authorize;
import com.example.demo.annotation.LogAspect;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BlogDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Blog;
import com.example.demo.service.BlogService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog/home")
@Timed
public class HomeController {
    @Autowired
    private BlogService blogService;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }


    @PostMapping(value = "/create")
    @LogAspect
//    @Authorize(roles = {"user_role"})
    public ResponseEntity<String> createBlog(@RequestBody BlogDto blogDto){
        Blog responseBlog = blogService.createBlog(blogDto);
        return ResponseEntity.ok("Success");
    }


    @PostMapping(value = "/registerAuthor")
    @LogAspect
    @Authorize(roles = {"admin_role"})
    public ResponseEntity<String> registerAuthor(@RequestBody AuthorDto authorDto){
        Author author1 = blogService.registerActor(authorDto);
        return ResponseEntity.ok("author registered");
    }
}
