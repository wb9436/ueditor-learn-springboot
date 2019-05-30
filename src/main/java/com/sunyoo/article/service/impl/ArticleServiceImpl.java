package com.sunyoo.article.service.impl;

import com.sunyoo.article.dao.ArticleInfoDao;
import com.sunyoo.article.dto.ArticleInfoDto;
import com.sunyoo.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleInfoDao articleInfoDao;

    @Override
    public List<ArticleInfoDto> getArticleList() {
        return articleInfoDao.getList();
    }

    @Override
    public ArticleInfoDto getArticleDto(int id) {
        return articleInfoDao.getArticleInfoDto(id);
    }

    @Transactional
    @Override
    public void updateArticle(ArticleInfoDto articleInfoDto) {
        articleInfoDao.updateArticle(articleInfoDto);
    }

    @Transactional
    @Override
    public void insertArticle(ArticleInfoDto articleInfoDto) {
        articleInfoDao.insertArticle(articleInfoDto);
    }
}
