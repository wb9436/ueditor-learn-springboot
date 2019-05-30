package com.sunyoo.article.dao;

import com.sunyoo.article.dto.ArticleInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleInfoDao {

    List<ArticleInfoDto> getList();

    ArticleInfoDto getArticleInfoDto(@Param("id") int id);

    int updateArticle(ArticleInfoDto articleInfoDto);

    int insertArticle(ArticleInfoDto articleInfoDto);
}
