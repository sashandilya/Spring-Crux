package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BlogDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Blog;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BlogRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Counter blogCounter=null;

    private Counter authorCounter=null;

    public BlogService(CompositeMeterRegistry compositeMeterRegistry) {
        blogCounter = compositeMeterRegistry.counter("blog.count");
        authorCounter = compositeMeterRegistry.counter("blog.author.count");
    }

    public Blog createBlog(BlogDto blogDto){
        Blog blog = new Blog();
        blog.setBlogTitle(blogDto.getBlogTitle());
        Author author = authorRepository.findById(blogDto.getAuthorId()).orElse(null);
        blog.setAuthor(author);
        blogCounter.increment();

        return blogRepository.save(blog);
    }

    public Author registerActor(AuthorDto authorDto){
        Author authorNew = new Author();
        authorNew.setAuthorName(authorDto.getAuthorName());
        authorCounter.increment();
        return authorRepository.save(authorNew);
    }

    public void removeAuthor(int authorId) {
        authorRepository.deleteById(authorId);
    }
}
