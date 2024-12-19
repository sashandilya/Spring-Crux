package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BlogDto;
import com.example.demo.dto.BlogResponseDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Blog;
import com.example.demo.mapper.BlogDtoMapper;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BlogRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BlogService {

//    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Counter blogCounter=null;

    private Counter authorCounter=null;


    public BlogService(CompositeMeterRegistry compositeMeterRegistry, BlogRepository blogRepository) {
        blogCounter = compositeMeterRegistry.counter("blog.count");
        authorCounter = compositeMeterRegistry.counter("blog.author.count");
        this.blogRepository = blogRepository;
    }

    public Blog createBlog(BlogDto blogDto){
        Blog blog = new Blog();
        blog.setBlogTitle(blogDto.getBlogTitle());
        Author author = authorRepository.findById(blogDto.getAuthorId()).orElse(null);
        blog.setAuthor(author);
        blogCounter.increment();

        return blogRepository.save(blog);
    }

    public BlogResponseDto getBlogById(int blogId) {
        BlogDtoMapper blogDtoMapper = new BlogDtoMapper();
        Blog blog = blogRepository.findById(blogId).orElse(null);
        List<BlogResponseDto> blogResponseDtoList = blogDtoMapper.convertToBlogResponseDto(List.of(blog));
        return blogResponseDtoList.get(0);
    }

    public List<BlogResponseDto> getBlogs(int authorId, int pageNo, int pageSize) {
        BlogDtoMapper blogDtoMapper = new BlogDtoMapper();
        Pageable blogsRequest = PageRequest.of(pageNo, pageSize, Sort.by("blog_title").ascending());
        Page<Blog> blogs = blogRepository.findBlogsByAuthorId(authorId, blogsRequest);
        return blogDtoMapper.convertToBlogResponseDto(blogs.stream().toList());
//        List<Blog> blogs = blogRepository.findBlogsByAuthorId(authorId);
//        return blogDtoMapper.convertToBlogResponseDto(blogs);
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
