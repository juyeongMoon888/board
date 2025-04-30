package com.juyeong.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String body;
    private String userName;
    private LocalDateTime registerDate;

    public Article() {

    }

    // 생성 메서드 //
    public static Article createArticle(String title, String userName, String body) {
        Article article = new Article();
        article.setTitle(title);
        article.setUserName(userName);
        article.setBody(body);
        article.setRegisterDate(LocalDateTime.now());
        return article;
    }

}


