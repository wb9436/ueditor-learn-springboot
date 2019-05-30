package com.sunyoo.article.controller;

import com.sunyoo.article.dto.ArticleInfoDto;
import com.sunyoo.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;


    @RequestMapping("/list")
    public String getArticleList(Model model) {
        List<ArticleInfoDto> list = articleService.getArticleList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping("/edit/{id}")
    public String editArticle(@PathVariable("id") int id, Model model) {
        ArticleInfoDto article = articleService.getArticleDto(id);
        model.addAttribute("article", article);
        return "edit";
    }

    @RequestMapping("/preview/{id}")
    public String previewArticle(@PathVariable("id") int id, Model model) {
        ArticleInfoDto article = articleService.getArticleDto(id);
        model.addAttribute("article", article);
        return "preview";
    }


    @RequestMapping("/add")
    public String addArticle() {
        return "add";
    }

    @RequestMapping("/update")
    public String updateArticle(ArticleInfoDto articleInfoDto) {
        articleService.updateArticle(articleInfoDto);
        return "redirect:/article/list";
    }

    @RequestMapping("/insert")
    public String insertArticle(ArticleInfoDto articleInfoDto) {
        articleService.insertArticle(articleInfoDto);
        return "redirect:/article/list";
    }

}
