package com.flaneur.blog.web.admin;

import com.flaneur.blog.entity.Type;
import com.flaneur.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/type")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 5, orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/type";
    }

    @GetMapping("/type/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    @PostMapping("/type")
    public String post(@Validated Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message","不能添加重复的分类");
            return "redirect:/admin/type/input";
        }
        int t = typeService.saveType(type);
        if (t == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/type";
    }

    @GetMapping("/type/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";
    }

    @PostMapping("/type/{id}")
    public String editPost(@Validated Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/type/input";
        }
        int t = typeService.updateType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/type";
    }

    @GetMapping("/type/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/type";
    }
}
