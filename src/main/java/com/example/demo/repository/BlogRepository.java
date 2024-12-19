package com.example.demo.repository;

import com.example.demo.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Integer>, PagingAndSortingRepository<Blog, Integer> {

    @Query(value = "select * from blog where author_id=?1", nativeQuery = true)
    Page<Blog> findBlogsByAuthorId(int authorId, Pageable pageable);
}
