package com.juyeong.board.controller;

import com.juyeong.board.domain.Article;
import com.juyeong.board.domain.ArticleRepository;
import com.juyeong.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final ArticleRepository articleRepository;
    private final BoardService boardService;

    @RequestMapping("/")
    public String article(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "board";
    }

    @GetMapping("/board/add")
    public String create(Model model) {
        model.addAttribute("articles", new Article());
        return "/addForm";
    }

    @PostMapping("/add")
    public String createForm(@RequestParam("title") String title,
                             @RequestParam("userName") String userName,
                             @RequestParam("body") String body) {

        boardService.article(title, userName, body);
        return "redirect:/board";
    }

    //게시글 상세
    @GetMapping("/{articlesId}")
    public String article(@PathVariable long articlesId, Model model) {
        Article articles = articleRepository.findOne(articlesId);
        model.addAttribute("articles", articles);
        return "/articles";
    }

    //게시글 수정
    @GetMapping("/{articlesId}/edit")
    public String edit(@PathVariable long articlesId, Model model) {
        Article articles = articleRepository.findOne(articlesId);
        model.addAttribute("articles", articles);
        return "/editForm";
    }

    @PostMapping("/{articlesId}/edit")
    public String editArticle(@PathVariable long articlesId, Article article) {
        boardService.updateArticle(articlesId, article.getTitle(), article.getUserName(), article.getBody());
        return "redirect:/board/{articlesId}";
    }

    //게시글 삭제




}
