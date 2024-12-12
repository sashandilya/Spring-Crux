package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "blog")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Blog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false, name = "blog_id")
    private Integer blogId;

    @Column(nullable = false, name = "blog_title")
    private String blogTitle;

    @Column(nullable = false, name = "creation_date")
    @CreationTimestamp
    private String creationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
