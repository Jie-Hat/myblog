package com.flaneur.blog.web.admin;

import com.flaneur.blog.entity.Blog;
import com.flaneur.blog.entity.Type;
import com.flaneur.blog.entity.User;
import com.flaneur.blog.queryvo.BlogQuery;
import com.flaneur.blog.queryvo.SearchBlog;
import com.flaneur.blog.queryvo.ShowBlog;
import com.flaneur.blog.service.BlogService;
import com.flaneur.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    //跳转博客新增页面
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("type",typeService.getAllType());
        model.addAttribute("blog",new Blog());
        return "admin/blog-input";
    }

    //查询博客列表
    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,2,orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        model.addAttribute("type",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/admin";
    }

    //博客新增
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        //新增的时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int b = blogService.saveBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //搜索博客列表
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,2);
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(blogBySearch);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeId",searchBlog.getTypeId());
        model.addAttribute("title",searchBlog.getTitle());
        return "admin/admin :: blogList";
    }

    //删除博客
    @RequestMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

    //跳转编辑博客
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog", blogById);
        model.addAttribute("type", allType);
        return "admin/blog-input";
    }

    //编辑博客
    @PostMapping("/blogs/{id}")
    public String editPost(@Validated ShowBlog showBlog, RedirectAttributes attributes) {
        int b = blogService.updateBlog(showBlog);
        if(b == 0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }
}
