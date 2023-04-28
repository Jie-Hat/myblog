package com.flaneur.blog.web;

import com.flaneur.blog.queryvo.RecommendBlog;
import com.flaneur.blog.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AboutController {


    @Autowired
    private BlogService blogService;

    @GetMapping("/about")
    public String about(Model model){

        List<RecommendBlog> newestBlog = blogService.getNewestBlog();

        PageInfo<RecommendBlog> pageInfo3 = new PageInfo<>(newestBlog);

        model.addAttribute("pageInfo3",pageInfo3);
        return "personal";
    }
}
