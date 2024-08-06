package com.example.spring_posts.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Post {
    private long id;
    private String title;
    private String content;
    private String contact;
    private LocalDateTime created_at;

    public Post() { }
    public Post(long id, String title, String content, String contact, LocalDateTime created_at) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.contact = contact;
        this.created_at = created_at;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getContact() { return contact; }
    public LocalDateTime getCreated_at() { return created_at; }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setContact(String contact) { this.contact = contact; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }


//    CREATE TABLE `postsdb`.`posts` (
//            `id` INT NOT NULL AUTO_INCREMENT,
//  `title` VARCHAR(100) NULL,
//  `content` TEXT NULL,
//  `contacts` VARCHAR(15) NULL,
//  `created_at` DATETIME NULL,
//    PRIMARY KEY (`id`));


}
