package com.example.demo.repository;

import com.example.demo.entity.Blog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Integer> {

    public List<Blog> findAll();

}
