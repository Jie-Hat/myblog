package com.flaneur.blog.service;

import com.flaneur.blog.entity.Blog;
import com.flaneur.blog.mapper.BlogMapper;
import com.flaneur.blog.queryvo.*;
import com.flaneur.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogMapper.getAllBlogQuery();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogMapper.saveBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogMapper.searchByTitleAndType(searchBlog);
    }

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogMapper.updateBlog(showBlog);
    }

    //查询首页最新博客列表信息
    @Override
    // @Cacheable(value = "blogList",key = "'blog'")       // redis缓存
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogMapper.getFirstPageBlog();
    }

    @Override
    public List<FirstPageType> getAllFirstPageType() {
        return blogMapper.getFirstPageType();
    }

    @Override
    public List<RecommendBlog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> getSearchBlogByTypeId(String query1, Long typeId) {
        return blogMapper.getSearchBlogByTypeId(query1, typeId);
    }

    @Override
    public List<RecommendBlog> getNewestBlog() {
        return blogMapper.getNewestBlog();
    }

    @Override
    public int updateViewsCount(Long id) {
        return blogMapper.updateViewsCount(id);
    }
}
