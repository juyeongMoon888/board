package com.juyeong.board.service;

import com.juyeong.board.domain.Article;
import com.juyeong.board.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Long article(String title, String userName, String body) {

        //게시글 생성
        Article article = Article.createArticle(title, userName, body);

        //게시글 저장
        articleRepository.save(article);

        return article.getId();
    }

    @Transactional
    public void updateArticle(long id, String title, String userName, String body) {
        Article findArticle = articleRepository.findOne(id);
        findArticle.setTitle(title);
        findArticle.setUserName(userName);
        findArticle.setBody(body);
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public Article findOne(long id) {
        return articleRepository.findOne(id);
    }

}
