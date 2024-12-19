package com.example.demo.mapper;

import com.example.demo.dto.BlogResponseDto;
import com.example.demo.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class BlogDtoMapper {

    public List<BlogResponseDto> convertToBlogResponseDto(List<Blog> blogs){
        List<BlogResponseDto> blogResponseDtoList = new ArrayList<>();
        blogs.forEach(blog-> {
                    BlogResponseDto blogResponseDto = new BlogResponseDto();
                    blogResponseDto.setBlogId(blog.getBlogId());
                    blogResponseDto.setBlogTitle(blog.getBlogTitle());
                    blogResponseDto.setAuthorId(blog.getAuthor().getAuthorId());
                    blogResponseDto.setCreationDate(blog.getCreationDate());
                    blogResponseDtoList.add(blogResponseDto);
                }
            );
        return blogResponseDtoList;
    }

}
