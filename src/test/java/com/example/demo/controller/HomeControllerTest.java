package com.example.demo.controller;

import com.example.demo.dto.BlogResponseDto;
import com.example.demo.service.BlogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;


@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @Mock
    BlogService blogService;

    @InjectMocks
    HomeController homeController;

    static BlogResponseDto dto = new BlogResponseDto();
    @BeforeAll
    public static void setUp(){
        dto.setAuthorId(1);
        dto.setBlogTitle("xyz");
        dto.setCreationDate("10/10");
        dto.setBlogId(2);
    }

    @Test
    public void getBlogDetail() {
        Mockito.when(blogService.getBlogById(Mockito.anyInt())).thenReturn(dto);
        ResponseEntity<BlogResponseDto>response = homeController.getBlogDetail(1);
        Assertions.assertNotNull(response);
    }
}