package com.flaneur.blog.service;

import com.flaneur.blog.entity.Blog;
import com.flaneur.blog.queryvo.*;

import java.util.List;

public interface BlogService {
    //查询博客
    List<BlogQuery> getAllBlog();

    //更新博客
//    Blog updateBlog(Long id);

    //保存博客
    int saveBlog(Blog blog);

    //删除博客
    void deleteBlog(Long id);

    //搜索博客列表
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    //查询编辑修改的文章
    ShowBlog getBlogById(Long id);

    //编辑修改文章
    int updateBlog(ShowBlog showBlog);

    //查询首页最新博客列表信息
    List<FirstPageBlog> getAllFirstPageBlog();

    //查询首页分类信息
    List<FirstPageType> getAllFirstPageType();

    //查询首页推荐博客
    List<RecommendBlog> getAllRecommendBlog();

    //搜索博客列表
    List<FirstPageBlog> getSearchBlog(String query);

    //查询博客详情
    DetailedBlog getDetailedBlog(Long id);

    //根据TypeId查询博客列表
    List<FirstPageBlog> getByTypeId(Long typeId);

    //分类页根据typeId搜索博客
    List<FirstPageBlog> getSearchBlogByTypeId(String query1, Long typeId);

    //查询首页底部最新博客
    List<RecommendBlog> getNewestBlog();

    //更新浏览量
    int updateViewsCount(Long id);
}
