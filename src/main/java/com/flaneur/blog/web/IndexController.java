package com.flaneur.blog.web;

import com.flaneur.blog.queryvo.DetailedBlog;
import com.flaneur.blog.queryvo.FirstPageBlog;
import com.flaneur.blog.queryvo.FirstPageType;
import com.flaneur.blog.queryvo.RecommendBlog;
import com.flaneur.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, RedirectAttributes attributes){
        PageHelper.startPage(pageNum,5);
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        List<FirstPageType> allFirstPageType = blogService.getAllFirstPageType();
        List<RecommendBlog> recommendBlog = blogService.getAllRecommendBlog();
        List<RecommendBlog> newestBlog = blogService.getNewestBlog();

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        PageInfo<FirstPageType> pageInfo1 = new PageInfo<>(allFirstPageType);
        PageInfo<RecommendBlog> pageInfo2 = new PageInfo<>(recommendBlog);
        PageInfo<RecommendBlog> pageInfo3 = new PageInfo<>(newestBlog);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageInfo1",pageInfo1);
        model.addAttribute("pageInfo2",pageInfo2);
        model.addAttribute("pageInfo3",pageInfo3);
        return "index";
    }

    //搜索博客
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 1000);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        List<FirstPageType> allFirstPageType = blogService.getAllFirstPageType();
        List<RecommendBlog> recommendBlog = blogService.getAllRecommendBlog();
        List<RecommendBlog> newestBlog = blogService.getNewestBlog();

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        PageInfo<FirstPageType> pageInfo1 = new PageInfo<>(allFirstPageType);
        PageInfo<RecommendBlog> pageInfo2 = new PageInfo<>(recommendBlog);
        PageInfo<RecommendBlog> pageInfo3 = new PageInfo<>(newestBlog);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        model.addAttribute("pageInfo1",pageInfo1);
        model.addAttribute("pageInfo2",pageInfo2);
        model.addAttribute("pageInfo3",pageInfo3);
        return "search";
    }

    //博客详情
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        blogService.updateViewsCount(id);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        List<RecommendBlog> newestBlog = blogService.getNewestBlog();

        PageInfo<RecommendBlog> pageInfo3 = new PageInfo<>(newestBlog);

        model.addAttribute("pageInfo3",pageInfo3);
        model.addAttribute("blog",detailedBlog);
        return "blog";
    }
}
