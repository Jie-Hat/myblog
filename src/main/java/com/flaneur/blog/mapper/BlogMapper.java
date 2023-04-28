package com.flaneur.blog.mapper;

import com.flaneur.blog.entity.Blog;
import com.flaneur.blog.queryvo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    //查询博客
    List<BlogQuery> getAllBlogQuery();

    //更新博客
    Blog updateBlog(Long id);

    //保存博客
    int saveBlog(Blog blog);

    //删除博客
    void deleteBlog(Long id);

    //搜索博客列表
    List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog);

    //查询需要编辑的博客
    ShowBlog getBlogById(Long id);

    //编辑博客
    int updateBlog(ShowBlog showBlog);

    //查询首页最新博客列表信息
    List<FirstPageBlog> getFirstPageBlog();

    //查询首页分类信息
    List<FirstPageType> getFirstPageType();

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

    //统计评论总数
    Integer getBlogCommentTotal();

    //根据博客id查询出评论数量
    int getCommentCountById(Long id);

    //更新浏览量
    int updateViewsCount(Long id);
}
