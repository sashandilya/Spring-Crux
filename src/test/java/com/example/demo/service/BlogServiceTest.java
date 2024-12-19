package com.example.demo.service;

import com.example.demo.dto.BlogResponseDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Blog;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BlogRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @Mock
    BlogRepository blogRepository;

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    BlogService blogService;

    @Mock
    CompositeMeterRegistry compositeMeterRegistry;

    static Blog blog = null;
    @BeforeAll
    public static void setUp(){
        blog = new Blog();
        blog.setBlogId(1);
        blog.setBlogTitle("Xyz");
        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorName("Sachin");
        author.setBlogList(new ArrayList<>());
        blog.setAuthor(author);
        blog.setCreationDate("10/10/10");
    }
    @Test
    void createBlog() {
    }

    @Test
    void getBlogById() {
        Mockito.when(blogRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(blog));
//        Mockito.when(compositeMeterRegistry.counter(Mockito.anyString())).thenReturn(counter);
        BlogResponseDto blogResponseDto = blogService.getBlogById(1);
        Assert.notNull(blogResponseDto, "Not null.");
    }
}