package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;


public class AuthorDto {
    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "authorName='" + authorName + '\'' +
                '}';
    }
}
