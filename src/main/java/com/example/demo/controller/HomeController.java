package com.example.demo.controller;

import com.example.demo.annotation.Authorize;
import com.example.demo.annotation.LogAspect;
import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BlogDto;
import com.example.demo.dto.BlogResponseDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Blog;
import com.example.demo.service.BlogService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/getBlog/{blogId}")
    public ResponseEntity<BlogResponseDto> getBlogDetail(@PathVariable("blogId") int blogId){
        BlogResponseDto blogResponseDto = blogService.getBlogById(blogId);
        return ResponseEntity.ok(blogResponseDto);
    }

//    @GetMapping(value = "/getBlog/{authorId}")
//    public ResponseEntity<List<Blog>> getBlogsForAuthor(@PathVariable("authorId") int authorId){
//        List<Blog> blogs = blogService.getBlogsByAuthorId(authorId);
//        return ResponseEntity.ok(blogs);
//    }
//
    @GetMapping(value = "/getBlogs")
    public ResponseEntity<List<BlogResponseDto>> getAllBlogsByAuthorId(@RequestParam("author_id") int authorId,
                                                                       @RequestParam("page_number") int pageNo, @RequestParam("page_size") int pageSize){
        List<BlogResponseDto> blogs = blogService.getBlogs(authorId, pageNo, pageSize);
        return ResponseEntity.ok(blogs);
    }

    @PostMapping(value = "/removeAuthor/{id}")
    @LogAspect
    @Authorize(roles = {"admin_role"})
    public ResponseEntity<String> removeAuthor(@PathVariable("id") int authorId){
        blogService.removeAuthor(authorId);
        return ResponseEntity.ok("Author removed.");
    }


    @PostMapping(value = "/registerAuthor")
    @LogAspect
    @Authorize(roles = {"admin_role"})
    public ResponseEntity<String> registerAuthor(@RequestBody AuthorDto authorDto){
        Author author1 = blogService.registerActor(authorDto);
        return ResponseEntity.ok("author registered");
    }
}
