package com.sunyoo.article.service;

import com.sunyoo.article.dto.ArticleInfoDto;

import java.util.List;

public interface IArticleService {
    List<ArticleInfoDto> getArticleList();

    ArticleInfoDto getArticleDto(int id);

    void updateArticle(ArticleInfoDto articleInfoDto);

    void insertArticle(ArticleInfoDto articleInfoDto);
}
