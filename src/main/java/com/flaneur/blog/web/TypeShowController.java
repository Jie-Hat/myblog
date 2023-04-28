package com.flaneur.blog.web;

import com.flaneur.blog.entity.Type;
import com.flaneur.blog.queryvo.FirstPageBlog;
import com.flaneur.blog.queryvo.FirstPageType;
import com.flaneur.blog.queryvo.RecommendBlog;
import com.flaneur.blog.service.BlogService;
import com.flaneur.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    //分页查询分类
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model){

        List<Type> types = typeService.getAllTypeAndBlog();

        //id为-1表示从首页导航栏点击进入分类页面
        if (id == -1) {
            if(!types.isEmpty()){
                id = types.get(0).getId();
            }
        }
        model.addAttribute("types", types);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);
        List<RecommendBlog> newestBlog = blogService.getNewestBlog();

        PageHelper.startPage(pageNum, 1000);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        PageInfo<RecommendBlog> pageInfo3 = new PageInfo<>(newestBlog);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        model.addAttribute("pageInfo3",pageInfo3);
        return "type";
    }

    //分类页搜索博客
    @PostMapping("/type/search/{typeId}")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @PathVariable Long typeId,
                         @RequestParam String query1) {

        PageHelper.startPage(pageNum, 1000);

        List<Type> types = typeService.getAllTypeAndBlog();
        List<FirstPageBlog> searchBlog = blogService.getSearchBlogByTypeId(query1, typeId);
        List<RecommendBlog> newestBlog = blogService.getNewestBlog();

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        PageInfo<RecommendBlog> pageInfo3 = new PageInfo<>(newestBlog);

        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query1", query1);
        model.addAttribute("activeTypeId", typeId);
        model.addAttribute("pageInfo3",pageInfo3);
        return "type";
    }
}
