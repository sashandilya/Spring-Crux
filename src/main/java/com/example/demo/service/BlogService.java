package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BlogDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Blog;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Blog createBlog(BlogDto blogDto){
        Blog blog = new Blog();
        blog.setBlogTitle(blogDto.getBlogTitle());
        Author author = authorRepository.findById(blogDto.getAuthorId()).orElse(null);
        blog.setAuthor(author);
        blog.setBlogId(blogDto.getAuthorId());
        return blogRepository.save(blog);
    }

    public Author registerActor(AuthorDto authorDto){
        Author authorNew = new Author();
        authorNew.setAuthorName(authorDto.getAuthorName());
        return authorRepository.save(authorNew);
    }
}
